package com.guru.depend.controller;

import java.util.Date;
import java.util.List;

import com.guru.depend.dto.MessageResponse;
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

import com.guru.depend.dto.QuestionDTO;
import com.guru.depend.entity.Questions;
import com.guru.depend.service.QuestionsService;

@RestController
@RequestMapping("api/questions")
public class QuestionsController {
	@Autowired 
	private QuestionsService questionsservice;
	//to store an question
	@PostMapping("/insert")
	 public ResponseEntity<ResponseDTO>  createrecord(@RequestBody Questions questions) {
		ResponseDTO response = new ResponseDTO(
				Constants.CREATED,
				HttpStatus.CREATED.value(),"question created successfully",
				questionsservice.createRecord(questions));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	//to view all the questions
	@GetMapping("/retrieve")
	public ResponseEntity<ResponseDTO> allData(){
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.OK.value(),"question retrieved successfully",
				questionsservice.allData());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	//to view thw question and choices with the help of question id
    @GetMapping("/question/{id}")
    public ResponseEntity<ResponseDTO> getQuestion(@PathVariable Long id){
		ResponseDTO response = new ResponseDTO(
				Constants.RETRIEVED,
				HttpStatus.OK.value(),"question retrieved successfully",
				questionsservice.getQuestion(id));
		return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    //to get the question and choices based on the subject id
//	@GetMapping("/questions/{id}")
//    public ResponseEntity<ResponseDTO> getQuestionBySubject(@PathVariable Long id) {
//		ResponseDTO response = new ResponseDTO(
//				Constants.RETRIEVED,
//				HttpStatus.OK.value(),"question retrieved successfully",
//				questionsservice.getQuestionBySubject(id));
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
	//to update the question by id
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateQuestions(@PathVariable Long id,@RequestBody Questions questions) {
		ResponseDTO response = new ResponseDTO(
				Constants.MODIFIED,
				HttpStatus.ACCEPTED.value(),"question updated successfully",
				questionsservice.updateQuestion(id, questions));
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	//to delete the question
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteByIdRecord(@PathVariable Long id){
		ResponseDTO response = new ResponseDTO(
				Constants.REMOVED,
				HttpStatus.ACCEPTED.value(),"question deleted successfully",
				questionsservice.deleteById(id));
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
}
