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
import com.guru.depend.entity.Subject;
import com.guru.depend.service.SubjectService;

@RestController
@RequestMapping("api/subject")
public class SubjectContoller {

	@Autowired
	private SubjectService subjectservice;
	
	@PostMapping("/")
	public Subject addrecord( @RequestBody Subject subject) {
		return subjectservice.createRecord(subject);
	}
	@GetMapping("/")
	public List<Subject> allData(){
		return subjectservice.allData();
	}
	
	@PutMapping("/update/{id}")
	public Subject updateRecord(@PathVariable Long id,@RequestBody Subject subject) {
		return subjectservice.updateSchool(id, subject);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteQuiz(@PathVariable Long id) {
		return subjectservice.deleteById(id);
	}
	
}
