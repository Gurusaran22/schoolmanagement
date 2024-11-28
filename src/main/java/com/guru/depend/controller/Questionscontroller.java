package com.guru.depend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/questions")
public class Questionscontroller {	
	@Autowired 
	private QuestionsService questionsservice;
	//to store an question
	@PostMapping("/")
	 public Questions createrecord(@RequestBody Questions questions) {
		 return questionsservice.createRecord(questions);
	 }
	//to view all the questions
	@GetMapping("/")
	public List<Questions> allData(){
		return questionsservice.allData();
	}
	//to view thw question and choices with the help of question id
    @GetMapping("/question/{id}")
    public List<QuestionDTO> getQuestion(@PathVariable Long id){
    return questionsservice.getQuestion(id);
    }
    //to get the question and choices based on the subject id
    public List<QuestionDTO> getQuestionBySubject(@PathVariable Long id){
    	return questionsservice.getQuestionBySubject(id);
    }
	//to update the question by id
	@PutMapping("/update/{id}")
	public String updateQuestions(@PathVariable Long id,@RequestBody Questions questions) {
		return questionsservice.updateQuestion(id, questions);
	}
	//to delete the question
	@DeleteMapping("/delete/{id}")
	public String deleteByIdRecord(@PathVariable Long id){
		return questionsservice.deleteById(id);
	}
}
