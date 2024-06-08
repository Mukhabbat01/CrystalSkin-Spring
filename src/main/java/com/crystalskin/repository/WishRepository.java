package com.crystalskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crystalskin.domain.Wish;
import com.crystalskin.domain.Wish.WishListsId;

import jakarta.transaction.Transactional;

public interface WishRepository extends JpaRepository<Wish, WishListsId> {

	@Transactional
	@Modifying
	@Query(value = "delete from wish where usr_id = :usrId and prd_id = :prdId ", nativeQuery = true)
	public void deleteByUserUsrIdAndProductPrdId(@Param("usrId") String usrId, @Param("prdId") int id);

	
	@Transactional
	@Modifying
	@Query(value = "delete from wish where usr_id = :usrId", nativeQuery = true)
	public void deleteByUserUsrId(@Param("usrId") String usrId);

}
