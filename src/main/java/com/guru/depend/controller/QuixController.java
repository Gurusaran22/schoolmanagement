package com.guru.depend.controller;


import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guru.depend.dto.MessageResponse;
import com.guru.depend.dto.QuizSubmissionDTO;
import com.guru.depend.entity.Quiz;
import com.guru.depend.service.QuizService;

@RestController
@RequestMapping("api/quiz")
public class QuixController {
	@Autowired
	private QuizService quizservice;
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> addrecord(@RequestBody Quiz quiz) {
		ResponseDTO response = new ResponseDTO(
				Constants.CREATED,
				HttpStatus.CREATED.value(),
				"quiz created successfully",
				quizservice.createRecord(quiz));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
//	 @PostMapping("/submit")
//	    public MessageResponse submitQuiz(@RequestBody QuizSubmissionDTO submissionDTO) {
//	         return quizservice.submitQuiz(submissionDTO);
//	    }
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<ResponseDTO> deleteQuiz(@PathVariable Long id) {
//		ResponseDTO response = new ResponseDTO(
//				Constants.REMOVED,
//				HttpStatus.OK.value(),"quiz deleted successfully",
//				quizservice.deleteById(id));
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
}
