package com.guru.depend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guru.depend.entity.Choices;
import com.guru.depend.repository.Choicesrepository;

public class Choicesservice {

	
	@Autowired 
	private Choicesrepository choicesrepository;
	
	public Choices createRecord(Choices choices) {
		return choicesrepository.save(choices);
	}
	
	public List<Choices> allData(){
		return choicesrepository.findAll();
	}

}
