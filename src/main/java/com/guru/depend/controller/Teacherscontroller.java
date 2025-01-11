package com.guru.depend.controller;

import java.util.List;

import com.guru.depend.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
	private TeachersService teachersservice;

	//to store the teacher
	@PostMapping("/insert")
	public ResponseDTO createRecord(@RequestBody Teachers teachers) {
		return this.teachersservice.createRecord(teachers);
	}

	//to view the teachers
	@GetMapping("/retrieve")
	public ResponseDTO allData(){
		return this.teachersservice.allData();
	}

	//to view the particular teacher details by the help of studentid
	@GetMapping("{id}")
	public ResponseDTO getteacherDetails( @PathVariable Long id) {
		return this.teachersservice.getTeachersDetails(id);
	}

	//to update the teacher by teacherid
	@PutMapping("/update/{id}")
	public ResponseDTO updateQuestions(@PathVariable Long id,@RequestBody  Teachers teachers ) {return this.teachersservice.updateTeacher(id, teachers);}

	//to delete the teacher by teacherid
	@DeleteMapping("/delete/{id}")
	public ResponseDTO deleteByIdRecord(@PathVariable Long id){
		return this.teachersservice.deleteById(id);
	}
}
