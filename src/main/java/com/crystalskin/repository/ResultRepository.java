package com.crystalskin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crystalskin.domain.Result;
import com.crystalskin.domain.User;
import com.crystalskin.dto.response.ResultResDto;

public interface ResultRepository extends JpaRepository<Result, Integer> {


	@Query(value = "select s.skin_type, s.skin_file, s.skin_id "
			+ "from result r "
			+ "left join skin_cat s on s.skin_id = r.skin_id "
			+ "left join user u on u.usr_id = r.usr_id "
			+ "where r.usr_id = :usrId ", nativeQuery = true)
	public List<ResultResDto> selectById(@Param("usrId") String usrId);

	public Result findByUser(User user);

	
	
}
