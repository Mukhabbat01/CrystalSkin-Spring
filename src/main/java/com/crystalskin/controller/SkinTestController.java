package com.crystalskin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crystalskin.domain.Question;
import com.crystalskin.dto.response.ResultResDto;
import com.crystalskin.service.QuestionService;
import com.crystalskin.service.ResultService;
import com.crystalskin.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SkinTestController {
	private final QuestionService questionService;
	private final UserService userService;
	private final ResultService resultService;

	@GetMapping("/questions")
	public List<Question> getQuestions(@RequestParam(name = "category") String category) {
		return questionService.getQuestionsByCategory(category);
	}

	@PostMapping("/mbti-result")
	public void submitAnswers(@RequestHeader("Authorization") String accessToken, @RequestParam("result") String result) {
		accessToken = accessToken.substring(7);
		System.out.println(result);
		String usrId = userService.getUserIdFromToken(accessToken);
		resultService.saveResult(result, usrId);
		
	}

	@GetMapping("/result")
	public ResponseEntity<List<ResultResDto>> showResult(@RequestHeader("Authorization") String accessToken) {
		accessToken = accessToken.substring(7);
		String usrId = userService.getUserIdFromToken(accessToken);
		return ResponseEntity.status(200).body(resultService.showResult(usrId));
	}
	
	
}
