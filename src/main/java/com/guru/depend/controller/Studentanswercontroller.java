package com.guru.depend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guru.depend.entity.Studentanswer;
import com.guru.depend.service.Studentanswerservice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/studentanswers")
public class Studentanswercontroller {
	@Autowired
	private Studentanswerservice studentanswerservice;
	//to save the studentanswer
	@PostMapping("/")
	public Studentanswer addrecord(@RequestBody Studentanswer studentanswer) {
		return studentanswerservice.addRecord(studentanswer);
	}
	//to get all studentsanswers
	@GetMapping("/")
	public List<Studentanswer> getalldata(Studentanswer studentanswer) {
		return studentanswerservice.allData();
	}
	//to view the particular student details by the help of studentid
		@GetMapping("{id}")
		public Studentanswer getStudentDetails(@PathVariable Long  id) {
			return studentanswerservice.getStudentDetails(id);
		}
	
}
		