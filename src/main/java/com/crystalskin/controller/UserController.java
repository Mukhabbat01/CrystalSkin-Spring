package com.crystalskin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crystalskin.domain.Product;
import com.crystalskin.domain.Wish;
import com.crystalskin.dto.NurigoSmsDto;
import com.crystalskin.dto.request.CreateAccessTokenRequest;
import com.crystalskin.dto.request.ReqUserDto;
import com.crystalskin.dto.response.CreateAccessTokenResponse;
import com.crystalskin.dto.response.ProductResDto;
import com.crystalskin.dto.response.ResUserDto;
import com.crystalskin.service.NurigoSmsService;
import com.crystalskin.service.TokenService;
import com.crystalskin.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final NurigoSmsService smsService;
	private final TokenService tokenService;

	@PostMapping("/api/userSave")
	public ResponseEntity<Boolean> userSave(@RequestBody ReqUserDto reqUserDto) {
		return ResponseEntity.status(200).body(userService.userSave(reqUserDto));
	}

	@GetMapping("/api/userView")
	public ResponseEntity<List<ResUserDto>> userView(ResUserDto userDto, @RequestHeader("Authorization") String accessToken) {
		accessToken = accessToken.substring(7);
		String usrId = userService.getUserIdFromToken(accessToken);
		return ResponseEntity.status(200).body(userService.resUserDto(usrId));

	}

	@PostMapping("/send/sms")
	public boolean sendSms(NurigoSmsDto nurigoSmsDto) {
		if (userService.checkUser(nurigoSmsDto)) {
			return false;
		}
		if (smsService.sendOne(nurigoSmsDto)) {
			userService.phoneSave(nurigoSmsDto);
			return true;
		}
		return false;
	}

	@PostMapping("/verify/code")
	public ResponseEntity<Boolean> verifyCode(NurigoSmsDto nurigoSmsDto) {
		return ResponseEntity.status(200).body(userService.checkCode(nurigoSmsDto));
	}

	 @PostMapping("/login")
	    public ResponseEntity<CreateAccessTokenResponse> login(@RequestParam("usrId") String usrId, @RequestParam("pass") String pass) {
	        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
	        request.setUsrId(usrId);
	        request.setPass(pass);
	        try {
	            CreateAccessTokenResponse response = tokenService.createAccessToken(request);
	       
	            if (response != null) {
	                return ResponseEntity.ok(response);
	            } else {
	                return ResponseEntity.status(401).body(null); // 인증 실패
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body(null); // 서버 오류
	        }
	    }

	@PostMapping("/token")
	public ResponseEntity<CreateAccessTokenResponse> postToken(@RequestBody CreateAccessTokenRequest request) {
		try {
			CreateAccessTokenResponse response = tokenService.createAccessToken(request);
			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(403).body(null);
		}
	}

	
	
	@PostMapping("/wishSave")
	public ResponseEntity<Wish> saveWish(@RequestHeader("Authorization") String accessToken, @RequestBody Product product) {
	    accessToken = accessToken.substring(7);
	    String usrId = userService.getUserIdFromToken(accessToken);
	    Wish savedWish = userService.addToWishList(usrId, product.getPrdId());
	    return ResponseEntity.ok(savedWish);
	}

	@GetMapping("/viewWishList")
	public ResponseEntity<List<ProductResDto>> viewWishList(@RequestHeader("Authorization") String accessToken) {
	    accessToken = accessToken.substring(7);
	    String usrId = userService.getUserIdFromToken(accessToken);
	    return ResponseEntity.status(200).body(userService.usrViewWishList(usrId));
	}
	
	
	@PostMapping("/wishRemove")
	public ResponseEntity<Void> deleteWish(@RequestHeader("Authorization") String accessToken, @RequestBody Product product) {
	    accessToken = accessToken.substring(7);
	    String usrId = userService.getUserIdFromToken(accessToken);
	   userService.deleteWish(usrId, product.getPrdId());
	   return ResponseEntity.ok().build();
	}
	
	
	
	
	@PostMapping("/signOut")
	public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String accessToken) {
	    accessToken = accessToken.substring(7);
	    String usrId = userService.getUserIdFromToken(accessToken);
	    System.out.println(usrId);
	    userService.deleteUser(usrId);
	    return ResponseEntity.ok().build();
	}
}
