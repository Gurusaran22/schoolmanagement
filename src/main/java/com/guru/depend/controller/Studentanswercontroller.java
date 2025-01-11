package com.guru.depend.controller;

import java.util.List;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.service.StudentAnswerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/studentanswers")
public class StudentAnswerController {
	@Autowired
	private StudentAnswerService studentanswerservice;

	//to save the studentanswer
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> addrecord(@RequestBody StudentAnswer studentanswer) {
		ResponseDTO response = new ResponseDTO(
				Constants.CREATED,
				HttpStatus.CREATED.value(),
				studentanswerservice.addRecord(studentanswer));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	//to get all studentsanswers
	@GetMapping("/retrieve")
	public ResponseEntity<ResponseDTO> getalldata(StudentAnswer studentanswer) {
			ResponseDTO response = new ResponseDTO(
					Constants.RETRIEVED,
					HttpStatus.FOUND.value(),
					studentanswerservice.allData());
			return new ResponseEntity<>(response,HttpStatus.FOUND);
	}

	//to view the particular student details by the help of studentid
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getStudentDetails(@PathVariable Long  id) {
			ResponseDTO response = new ResponseDTO(
					Constants.RETRIEVED,
					HttpStatus.FOUND.value(),
					studentanswerservice.getStudentDetails(id));
			return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
}
		