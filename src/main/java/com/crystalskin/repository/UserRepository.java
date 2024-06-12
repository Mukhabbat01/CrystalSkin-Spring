package com.crystalskin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crystalskin.domain.User;
import com.crystalskin.dto.response.ProductResDto;
import com.crystalskin.dto.response.ResUserDto;

public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "select u.usr_id, u.pass, u.gender, u.email, u.phone, u.usr_birth, u.usr_name, s.skin_type from user u "
			+ "left join result r on r.usr_id = u.usr_id "
			+ "left join skin_cat s on r.skin_id = s.skin_id "
			+ "where u.usr_id = :usrId ", nativeQuery = true)
	public List<ResUserDto> selectUser(@Param("usrId") String usrId);

	boolean existsByPhone(String phone);

	String existsByUsrIdAndPass(String usrId, String pass);

	public Optional<User> findById(String usrId);

	
	@Query(value="select p.prd_id, p.title, p.price, f.file_id, p.cat_id "
			+ "from product p "
			+ "left join wish w on w.prd_id = p.prd_id "
			+ "left join user u on u.usr_id = w.usr_id "
			+ "left join file f on f.prd_id = p.prd_id "
			+ "where w.usr_id = :usrId ", nativeQuery = true)
	public List<ProductResDto> selectWishList(@Param("usrId") String usrId);

}
