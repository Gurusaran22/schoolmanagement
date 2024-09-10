package com.guru.depend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.entity.Students;
import com.guru.depend.service.StudentAnswerService;
import com.guru.depend.service.StudentsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/studentanswers")
public class Studentanswercontroller {

	@Autowired
	private StudentAnswerService studentanswerservice;
    
	//to save the studentanswer
	@PostMapping("/")
	public StudentAnswer addrecord(@RequestBody StudentAnswer studentanswer) {
		return studentanswerservice.addRecord(studentanswer);
	}
	
	//to get all studentsanswers
	@GetMapping("/")
	public List<StudentAnswer> getalldata(StudentAnswer studentanswer) {
		return studentanswerservice.allData();
	}
	
	//to update the studentanwer based on studentanswerid
	@PutMapping("/update/{id}")
	public StudentAnswer  updateanswer(@PathVariable Long id, @RequestBody StudentAnswer studentanswer) {
		return studentanswerservice.updateAnswer(id, studentanswer);
	}
	
	//to delete the stuedentanswer based on studentanswerid	
	@DeleteMapping("/delete/{id}")
	public Map<String,Object> deleteById(@PathVariable Long id){
		return studentanswerservice.deleteById(id);
	}
	
	//to view the particular student details by the help of studentid
		@GetMapping("{id}")
		public StudentAnswer getStudentDetails(@PathVariable Long  id) {
			return studentanswerservice.getStudentDetails(id);
		}
	
}
		