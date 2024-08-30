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

import com.guru.depend.entity.Students;
import com.guru.depend.service.Studentsservice;
@RestController

@RequestMapping("/api/Students")
public class Studentscontroller {

	@Autowired 
	
	private Studentsservice studentsservice;
	
     //to store student
	@PostMapping("/")
	public Students createRecord(@RequestBody Students students) {
		return studentsservice.createRecord(students); 
	}
	
	//to view all the students
	@GetMapping("/")
	public List<Students> allData(){
		return studentsservice.allData();
	}
	
	//to update the student
	@PutMapping("/update/{id}")
	public Students updateStudents(@PathVariable int id,@RequestBody Students students) {
		return studentsservice.updateStudents(id, students);
	}

	//to delete the student
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable int id){
		return studentsservice.deleteById(id);
	}
	
}
