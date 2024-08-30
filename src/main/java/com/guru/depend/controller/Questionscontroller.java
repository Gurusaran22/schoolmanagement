package com.guru.depend.controller;

import java.util.List;

import org.hibernate.query.sqm.tree.select.SqmQuerySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guru.depend.entity.Questions;
import com.guru.depend.service.Questionsservice;


@RequestMapping("/api/Questions")
public class Questionscontroller {
	
	@Autowired 
	private Questionsservice questionsservice;
	
	@PostMapping("/")
	 public Questions createrecord(@RequestBody Questions questions) {
		 return questionsservice.createRecord(questions);
	 }
	
	@GetMapping("/")
	public List<Questions> allData(){
		return questionsservice.allData();
	}
	
	

}
