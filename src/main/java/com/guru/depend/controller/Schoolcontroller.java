package com.guru.depend.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Test;
import com.guru.depend.service.Schoolservice;
import com.guru.depend.service.Studentsservice;
@RestController
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
	
	//to get all students count by school id
	@GetMapping("/student_count/{id}")
	public Map<String,Long> getStudentsCount(@PathVariable int id){
		return schoolservice.getStudentCountById(id);
	}
	
	//to get all teachers count by school id
	@GetMapping("/teacher_count/{id}")
	public Map<String,Long> getTeachersCount(@PathVariable int id){
		return schoolservice.getTeachersById(id);
	}
	
	//to update the school details by school id
	@PutMapping("/update/{id}")
	public Object updateSchool(@PathVariable int id,@RequestBody School school) {
		return schoolservice.updateSchool(id, school);
	}
	
	//to delete the school
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable int id)
	{
		return schoolservice.deleteById(id);
	}
}
