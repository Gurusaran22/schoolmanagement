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

import com.guru.depend.entity.Choices;
import com.guru.depend.service.Choicesservice;
@RestController
@RequestMapping("/api/Choices")
public class Choicescontroller {
	
	@Autowired 
	private Choicesservice choicesservice;
	
	//store an choice
	@PostMapping("/")
	public Choices  createRecord(@RequestBody Choices choices) {
		return  choicesservice.createRecord(choices);
	}
	
	//get all choices
	@GetMapping("/")
	public List<Choices> allData(){
		return choicesservice.allData();
	}

	//to update the choice
	@PutMapping("/update/{id}")
	public Choices updateChoices(@PathVariable int id,@RequestBody Choices choices) {
		return choicesservice.updateChoices(id, choices);
	}
	//to delete the choice
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable int id){
		return choicesservice.deleteById(id);
	}

}
