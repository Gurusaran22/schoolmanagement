package com.guru.depend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Test;
import com.guru.depend.service.Schoolservice;

@RequestMapping("/api/school")
public class Schoolcontroller {

	@Autowired 
	private Schoolservice schoolservice;
	
	//to create the school
	@PostMapping("/")
	public School createschool(@RequestBody School school) {
		return schoolservice.createRecord(school);
	}
	
	// to get all the schools
	@GetMapping("/")
	 public List<School> allData(){ 
		 return schoolservice.allData();
	 }
	
	//to get the teacher and student for an school
	@GetMapping("/{id}")
	public List<Object> getallstudentandteacherbyschoolname(@PathVariable int id){
		return schoolservice.getallstudentandteacherbyschoolname(id);
	}
	
	
}
