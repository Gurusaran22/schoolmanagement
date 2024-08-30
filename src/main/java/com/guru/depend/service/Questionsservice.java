package com.guru.depend.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.guru.depend.entity.Choices;
import com.guru.depend.entity.Questions;
import com.guru.depend.repository.Choicesrepository;
import com.guru.depend.repository.Questionsrepository;

public class Questionsservice {
	
	@Autowired 
	private Questionsrepository questionsrepository;
	@Autowired
	private Choicesrepository choicesrepository;
  
	public Questions createRecord(Questions questions ) {
		return questionsrepository.save(questions);
	}

	public List<Questions> allData(){
		return questionsrepository.findAll();
	}
	
	public List<Choices> getallchoicesbyid(@PathVariable int id){
		
		List<Choices> choice=new LinkedList<>();
		
		choice.add((Choices) choicesrepository.Choicebyid(id));
		
		return choice;
		
	}
}
