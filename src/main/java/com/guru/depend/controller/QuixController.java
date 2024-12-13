package com.guru.depend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guru.depend.dto.Messageresponse;
import com.guru.depend.dto.Quizsubmissiondto;
import com.guru.depend.entity.Quiz;
import com.guru.depend.service.Quizservice;

@RestController
@RequestMapping("/api/quiz")
public class Quixcontroller {
	@Autowired
	private Quizservice quizservice;
	@PostMapping("/")
	public Quiz addrecord(@RequestBody Quiz quiz) {
		return quizservice.createRecord(quiz);
	}
	 @PostMapping("/")
	    public Messageresponse submitQuiz(@RequestBody Quizsubmissiondto submissionDTO) {
	         return quizservice.submitQuiz(submissionDTO);
	    }
	@DeleteMapping("/delete/{id}")
	public String deleteQuiz(@PathVariable Long id) {
		return quizservice.deleteById(id);
	}
}
