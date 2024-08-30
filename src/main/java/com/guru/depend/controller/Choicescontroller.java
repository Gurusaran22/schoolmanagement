package com.guru.depend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guru.depend.entity.Choices;
import com.guru.depend.service.Choicesservice;

@RequestMapping("/api/Choices")
public class Choicescontroller {
	
	@Autowired 
	private Choicesservice choicesservice;
	
	@PostMapping("/")
	public Choices  createRecord(@RequestBody Choices choices) {
		return  choicesservice.createRecord(choices);
	}
	
	@GetMapping("/")
	public List<Choices> allData(){
		return choicesservice.allData();
	}

}
