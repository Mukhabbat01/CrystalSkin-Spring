package com.crystalskin.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.crystalskin.domain.Question;
import com.crystalskin.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }
}
