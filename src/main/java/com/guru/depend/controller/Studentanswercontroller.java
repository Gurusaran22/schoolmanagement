package com.guru.depend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.service.StudentAnswerService;
import org.springframework.web.bind.annotation.GetMapping;
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
	//to view the particular student details by the help of studentid
		@GetMapping("{id}")
		public StudentAnswer getStudentDetails(@PathVariable Long  id) {
			return studentanswerservice.getStudentDetails(id);
		}
	
}
		