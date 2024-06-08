package com.crystalskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crystalskin.domain.SkinCat;

public interface SkinCatRepository extends JpaRepository<SkinCat, String>{
	  
	public SkinCat findBySkinTypeContainsIgnoreCase(String mbti);
}
