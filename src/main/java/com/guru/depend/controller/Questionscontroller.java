package com.guru.depend.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.query.sqm.tree.select.SqmQuerySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.guru.depend.entity.Questions;
import com.guru.depend.service.Questionsservice;

@RestController

@RequestMapping("/api/Questions")
public class Questionscontroller {
	
	@Autowired 
	private Questionsservice questionsservice;
	
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
	
//	//total choices for  particular question
//	@GetMapping("/choice_count/{id}")
//	public Map<String,Long> getChoicesById(@PathVariable int id)
//	{
//		return questionsservice.getChoicesbyQuestionId(id);
//	}
//	
	//to update the question by id
	public Questions updateQuestions(@PathVariable int id,@RequestBody Questions questions) {
		return questionsservice.updateSchool(id, questions);
	}
	//to delete the question
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable int id){
		return questionsservice.deleteById(id);
	}
	
//	@GetMapping("/{id}")
//	public List<QuestionDto> getquestions(@PathVariable int id){
//	  return questionsservice.getallchoicesbyid(id);	
//	}
	

}
