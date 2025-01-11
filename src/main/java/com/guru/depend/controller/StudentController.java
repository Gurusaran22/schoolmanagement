package com.guru.depend.controller;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.entity.Students;
import com.guru.depend.service.StudentsService;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/students")
@RestController
public class StudentController {

    @Autowired
    private StudentsService studentsService;
    //to store student
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> createRecord(@RequestBody Students students) {
        ResponseDTO response = new ResponseDTO(
                Constants.CREATED,
                HttpStatus.CREATED.value(),
                studentsService.createRecord(students));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //to view all the students
    @GetMapping("/retrieve")
    public ResponseEntity<ResponseDTO> allData(){
        ResponseDTO response = new ResponseDTO(
                Constants.RETRIEVED,
                HttpStatus.FOUND.value(),
                studentsService.allData());
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //the view the student mark for one question specific student
    @GetMapping("/studentmark/{id}")
    public ResponseEntity<ResponseDTO> studentScore(@PathVariable Long id) {
        ResponseDTO response = new ResponseDTO(
                Constants.RETRIEVED,
                HttpStatus.FOUND.value(),
                studentsService.evaluateStudentMark(id));
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //to get the student marks based on studentanswerid
    @GetMapping("/mark/{id}")
    public int allMarks(@PathVariable Long id){
        return  this.studentsService.allMarks(id);
    }

    //to view the particular student details by the help of studentid
    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getStudentDetails(@PathVariable Long  id) {
        ResponseDTO response = new ResponseDTO(
                Constants.RETRIEVED,
                HttpStatus.FOUND.value(),
                studentsService.getStudentDetails(id));
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //to get the all the students marks those who are attended the test they get mark else they get o
    @GetMapping("/allmarks")
    public ResponseEntity<ResponseDTO> allstudentsmarks(){
    ResponseDTO response = new ResponseDTO(
            Constants.RETRIEVED,
            HttpStatus.FOUND.value(),
            studentsService.allstudentsMark());
		return new ResponseEntity<>(response,HttpStatus.FOUND);
}

//to update student record with the help of studentid
   @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateStudent(@PathVariable Long id,@RequestBody Students students){
       ResponseDTO response = new ResponseDTO(
               Constants.MODIFIED,
               HttpStatus.ACCEPTED.value(),
               studentsService.updateStudent(id, students));
       return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
   }

   //to delete the student with the help of an studentid
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteStudent(@PathVariable Long id){
        ResponseDTO response = new ResponseDTO(
                Constants.REMOVED,
                HttpStatus.OK.value(),
                studentsService.deleteById(id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
