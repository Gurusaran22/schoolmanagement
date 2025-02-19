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
import com.guru.depend.entity.Teachers;
import com.guru.depend.service.TeachersService;

@RestController

@RequestMapping("api/teachers")
public class TeachersController {
	@Autowired 
	private TeachersService teachersService;

	//to store the teacher
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> createRecord(@RequestBody Teachers teachers) {
		ResponseDTO response = new ResponseDTO(
				Constants.CREATED,
				HttpStatus.CREATED.value(),
				"****teacher Inserted successfully****",teachersService.createRecord(teachers));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	//to view the teachers
	@GetMapping("/retrieve")
	public ResponseEntity<ResponseDTO> allData(){
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.OK.value(),
				"**** teachers retrieved successfully****",teachersService.allData());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	//to view the particular teacher details by the help of studentid
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO> getteacherDetails( @PathVariable Long id) {
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.OK.value(),
				"****teacher retrieved successfully****",teachersService.getTeachersDetails(id));
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	//to update the teacher by teacherid
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateQuestions(@PathVariable Long id,@RequestBody  Teachers teachers ) {
		ResponseDTO response = new ResponseDTO(
				Constants.MODIFIED,
				HttpStatus.OK.value(),
				"****teacher updated successfully****", teachersService.updateTeacher(id, teachers));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	//to delete the teacher by teacherid
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteByIdRecord(@PathVariable Long id){
			ResponseDTO response = new ResponseDTO(
					Constants.REMOVED,
					HttpStatus.OK.value(),
					"****teacher deleted successfully****",teachersService.deleteById(id));
			return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
