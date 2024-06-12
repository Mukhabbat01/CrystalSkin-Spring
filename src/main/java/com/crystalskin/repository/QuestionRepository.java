package com.crystalskin.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crystalskin.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);
}
