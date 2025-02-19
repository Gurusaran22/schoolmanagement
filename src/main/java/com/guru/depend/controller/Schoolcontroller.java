package com.guru.depend.controller;

import java.util.List;
import java.util.Map;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.guru.depend.entity.School;
import com.guru.depend.service.SchoolService;

@RestController
@RequestMapping("api/school")
public class SchoolController {
	@Autowired
	private SchoolService schoolservice;

	//to create the school
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> createschool(@RequestBody School school) {
		ResponseDTO response = new ResponseDTO(
				Constants.CREATED,
				HttpStatus.CREATED.value(), "school created successfully",
				schoolservice.createRecord(school));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// to get all the schools
	@GetMapping("/retrieve")
	public ResponseEntity<ResponseDTO> allData() {
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.FOUND.value(), "school retrieved successfully",
				schoolservice.allData());
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	//to view the particular student details by the help of studentid
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO> getSchoolDetails(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.FOUND.value(), "school retrieved successfully",
				schoolservice.getSchoolDetailsById(id));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
//
//	//to get the teacher and student for an school
//	@GetMapping("all/{id}")
//	public ResponseEntity<ResponseDTO> getallstudentandteacherbyschoolname(@PathVariable Long id) {
//		ResponseDTO response = new ResponseDTO(
//				Constants.RETRIEVED,
//				HttpStatus.FOUND.value(), "school retrieved successfully",
//				schoolservice.getallstudentandteacherbyschoolname(id));
//		return new ResponseEntity<>(response, HttpStatus.FOUND);
//	}

	//to get all students count by school id
	@GetMapping("/student_count/{id}")
	public ResponseEntity<ResponseDTO> getStudentCountById(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.FOUND.value(), "student counted successfully",
				schoolservice.getStudentCountById(id));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	//to get all teachers count by school id
	@GetMapping("/teacher_count/{id}")
	public ResponseEntity<ResponseDTO> getTeachersById(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.FOUND.value(), "teacher counted successfully",
				schoolservice.getTeachersById(id));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	//to update the school details by school id
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateSchool(@PathVariable Long id, @RequestBody School school) {
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.OK.value(), "school updated successfully",
				schoolservice.updateSchool(id, school));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	//to delete the school
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteByIdRecord(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO(
				Constants.REMOVED,
				HttpStatus.ACCEPTED.value(), "school deleted successfully",
				schoolservice.DeleteById(id));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	//sorting the page 
	@GetMapping("/page")
	public ResponseEntity<ResponseDTO> getSchoolByPage(@RequestParam int pageIndex, @RequestParam int pageSize, @RequestParam String field) {
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.FOUND.value(), "pagenation done successfully",
				schoolservice.getSchoolByPage(pageIndex, pageSize, field));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
}
