package com.guru.depend.controller;

import java.util.List;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> addrecord(@RequestBody Subject subject) {
		ResponseDTO response = new ResponseDTO(
				Constants.CREATED,
				HttpStatus.CREATED.value(),
				subjectservice.createRecord(subject));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		//return this.subjectservice.createRecord(subject);
	}

	@GetMapping("/retrieve")
	public ResponseEntity<ResponseDTO> allData(){

		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.FOUND.value(),
				subjectservice.allData());
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateRecord(@PathVariable Long id,@RequestBody Subject subject) {
		ResponseDTO response = new ResponseDTO(
				Constants.MODIFIED,
				HttpStatus.ACCEPTED.value(),
				subjectservice.updateSubject(id, subject));
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteQuiz(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO(
				Constants.REMOVED,
				HttpStatus.OK.value(),
				subjectservice.deleteById(id));
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
