package com.guru.depend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guru.depend.entity.Quiz;
import com.guru.depend.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuixController {
 
	@Autowired
	private QuizService quizservice;
	@PostMapping("/")
	public Quiz addrecord(@RequestBody Quiz quiz) {
		return quizservice.createRecord(quiz);
	}
	@GetMapping("/")
	public List<Quiz> allData(){
		return quizservice.allData();
	}
	
	@PutMapping("/update/{id}")
	public Quiz updateRecord(@PathVariable Long id,@RequestBody Quiz quiz) {
		return quizservice.updateQuiz(id, quiz);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteQuiz(@PathVariable Long id) {
		return quizservice.deleteById(id);
	}
	
}
