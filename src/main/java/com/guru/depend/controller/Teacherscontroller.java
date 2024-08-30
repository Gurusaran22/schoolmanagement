package com.guru.depend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guru.depend.entity.Questions;
import com.guru.depend.entity.Teachers;
import com.guru.depend.service.Teachersservice;

@RestController

@RequestMapping("/api/Teachers")
public class Teacherscontroller {
	
	@Autowired 
	private Teachersservice teachersservice;
	
	//to store the teacher
	@PostMapping("/")
	public Teachers createRecord(@RequestBody Teachers teachers) {
		return teachersservice.createRecord(teachers);
	}
	
	//to view the teachers
	@GetMapping("/")
	public List<Teachers> allData(){
		return teachersservice.allData();
	}
	
	//to update the teacher by teacherid
		public Teachers updateQuestions(@PathVariable int id,@RequestBody  Teachers teachers ) {
			return teachersservice.updateTeachers(id, teachers);
		}
		
	//to delete the teacher by teacherid
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable int id){
		return teachersservice.deleteById(id);
	}

}
