package com.guru.depend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guru.depend.entity.Students;
import com.guru.depend.service.Studentsservice;

@RequestMapping("/api/Students")
public class Studentscontroller {

	@Autowired 
	
	private Studentsservice studentsservice;
	

	@PostMapping("/")
	public Students createRecord(@RequestBody Students students) {
		return studentsservice.createRecord(students); 
	}
	
	@GetMapping("/")
	public List<Students> allData(){
		return studentsservice.allData();
	}
	
}
