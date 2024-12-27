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
import com.guru.depend.entity.Students;
import com.guru.depend.service.StudentsService;
@RestController

@RequestMapping("api/students")
public class StudentsController {
	@Autowired 
	private StudentsService studentsservice;
    //to store student
	@PostMapping("/")
	public Students createRecord(@RequestBody Students students) {
		return studentsservice.createRecord(students); 
	}
	//to view all the students
	@GetMapping("/")
	public List<Students> allData(){
		return studentsservice.allData1();
	}
    //the view the student mark for one question specific student
	@GetMapping("/studentmark/{id}")
	public int studentScore(@PathVariable Long id) {
		return studentsservice.evaluateStudentMark(id);
	}
	//to get the student marks based on studentanswerid
	@GetMapping("/mark/{id}")
	public int allMarks(@PathVariable Long id){
		return  studentsservice.allMarks(id);
	}
	//to view the particular student details by the help of studentid
	@GetMapping("{id}")
	public Students getStudentDetails(@PathVariable Long  id) {
		return studentsservice.getStudentDetails(id);
	}
	//to get the all the students marks those who are attended the test they get mark else they get o
	@GetMapping("/allmarks")
	public Map<Long,Integer> allstudentsmarks(){
		return studentsservice.allstudentsMark();
	}
	//to update the student
	@PutMapping("/update/{id}")
	public String updateStudents(@PathVariable Long id,@RequestBody Students students) {
		return studentsservice.updateStudent(id, students);
	}
	//to delete the student
	@DeleteMapping("/delete/{id}")
	public String deleteByIdRecord(@PathVariable Long id){
		return studentsservice.deleteById(id);
	}	
}
