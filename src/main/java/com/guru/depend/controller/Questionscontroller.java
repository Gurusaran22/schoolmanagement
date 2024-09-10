package com.guru.depend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.guru.depend.entity.Questions;
import com.guru.depend.service.QuestionsService;

@RestController

@RequestMapping("/api/questions")
public class Questionscontroller {
	
	@Autowired 
	private QuestionsService questionsservice;
	
	//to store an question
	@PostMapping("/")
	 public Questions createrecord(@RequestBody Questions questions) {
		 return questionsservice.createRecord(questions);
	 }
	
	//to view all the questions
	@GetMapping("/")
	public List<Questions> allData(){
		return questionsservice.allData();
	}
	
//	// to get the question with the help of the question id
//	@GetMapping("/question/{id}")
//	public Optional<Questions> getQuestionWithChoices(@PathVariable Long id){
//		return questionsservice.getQuestionWithChoices(id);
//	}
  // get the question and choices with the help of question id
	@GetMapping("/question/{id}")
	public List<String> getall(@PathVariable Long id){
		return questionsservice.getQuestionChoices(id);
	}
	//to update the question by id
	@PutMapping("/update/{id}")
	public Questions updateQuestions(@PathVariable Long id,@RequestBody Questions questions) {
		return questionsservice.updateSchool(id, questions);
	}
	//to delete the question
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable Long id){
		return questionsservice.deleteById(id);
	}
	

}
