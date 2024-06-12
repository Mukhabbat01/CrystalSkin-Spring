package com.crystalskin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crystalskin.domain.Phone;
import com.crystalskin.domain.Product;
import com.crystalskin.domain.User;
import com.crystalskin.domain.Wish;
import com.crystalskin.dto.NurigoSmsDto;
import com.crystalskin.dto.request.ReqUserDto;
import com.crystalskin.dto.response.CreateAccessTokenResponse;
import com.crystalskin.dto.response.ProductResDto;
import com.crystalskin.dto.response.ResUserDto;
import com.crystalskin.repository.PhoneRepository;
import com.crystalskin.repository.ProductRepository;
import com.crystalskin.repository.UserRepository;
import com.crystalskin.repository.WishRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PhoneRepository phoneRepository;
	private final JWTokenProviderService tokenProviderService;
	private final WishRepository wishRepository;
	private final ProductRepository productRepository;

	public boolean userSave(ReqUserDto userDto) {
		System.out.println("usrId " + userDto.getUsrId());
		User user = userDto.userDtoToEntity();

		if (userRepository.existsById(userDto.getUsrId()))
			return false;
		if (userRepository.existsByPhone(userDto.getPhone()))
			return false;
		else
			userRepository.save(user);
		return true;
	}

	public List<ResUserDto> resUserDto(String usrId) {
		return userRepository.selectUser(usrId);
	}

	public boolean checkCode(NurigoSmsDto nurigoSmsDto) {
		Phone phone = nurigoSmsDto.smsDtoToEntity();
		String inputCode = nurigoSmsDto.smsDtoToEntity().getCode();

		if (phone.getPhone() == null || inputCode == null) {
			return false;
		}

		if (phoneRepository.existsById(phone.getPhone())) {
			Optional<Phone> phoneOptional = phoneRepository.findById(phone.getPhone());

			if (phoneOptional.isPresent()) {
				Phone phoneRecord = phoneOptional.get();

				if (phoneRecord.getExpiresAt().isBefore(LocalDateTime.now())) {
					return false;
				}

				return phoneRecord.getCode().equals(inputCode);
			}
		}

		return true;
	}

	public String userLogin(String usrId, String pass) {
		return userRepository.existsByUsrIdAndPass(usrId, pass);
	}

	public boolean checkUser(NurigoSmsDto nurigoSmsDto) {
		return userRepository.existsByPhone(nurigoSmsDto.getPhone());
	}

	public boolean phoneSave(NurigoSmsDto nurigoSmsDto) {
		Phone phone = nurigoSmsDto.smsDtoToEntity();
		return phoneRepository.save(phone) != null;
	}

	public User findById(String usrId) {
		return userRepository.findById(usrId).get();
	}

	public String getUserIdFromToken(CreateAccessTokenResponse accessTokenResponse) {
		String accessToken = accessTokenResponse.getAccessToken();
		return getUserIdFromToken(accessToken);
	}

	public String getUserIdFromToken(String accessToken) {
		try {
			if(accessToken != null) {
				return tokenProviderService.getUsrId(accessToken);
			}
		}catch(Exception e) {
			return "토큰 없음"+ e;
		}
		return "ss";
	
	}

	public Wish addToWishList(String usrId, int id) {

		Optional<User> userOptional = userRepository.findById(usrId);
		User user = userOptional.get();

		Optional<Product> productOptional = productRepository.findById(id);
		Product product = productOptional.get();

		Wish.WishListsId wishListsId = new Wish.WishListsId(user, product);

		Wish wish = new Wish();
		wish.setWishListsId(wishListsId);

		return wishRepository.save(wish);
	}
	
	
	
	
	public List<ProductResDto> usrViewWishList(String usrId) {
		return userRepository.selectWishList(usrId);
	}

	@Transactional
	public void deleteWish(String usrId, int id) {
		 wishRepository.deleteByUserUsrIdAndProductPrdId(usrId, id);
	}
	
	
	
	@Transactional
	public void deleteUser(String usrId) {
		 wishRepository.deleteByUserUsrId(usrId);
		 userRepository.deleteById(usrId);
	}
}
