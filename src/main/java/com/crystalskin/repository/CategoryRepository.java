package com.crystalskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crystalskin.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
