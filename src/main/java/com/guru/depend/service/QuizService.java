package com.guru.depend.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.dto.Answerdto;
import com.guru.depend.dto.Messageresponse;
import com.guru.depend.dto.Questiondto;
import com.guru.depend.dto.Quizsubmissiondto;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.Quiz;
import com.guru.depend.entity.Studentanswer;
import com.guru.depend.entity.Students;
import com.guru.depend.exception.Useridnotfoundexception;
import com.guru.depend.repository.Questionsrepository;
import com.guru.depend.repository.Quizrepository;
import com.guru.depend.repository.Studentanswerespository;
import com.guru.depend.repository.Studentsrepository;
@Service
public class Quizservice {

	@Autowired
	private Quizrepository  quizrepository;
	@Autowired
	 private Questionsrepository questionsrepository;
	@Autowired
	private Studentsrepository studentsrepository;
	@Autowired
	private Studentanswerespository studentanswerrepository;
	
	public Quiz createRecord(Quiz quiz )
	{
		return quizrepository.save(quiz);
	}
    //to view all the data
	public List<Quiz> allData()
	{
		return quizrepository.findAll();
	}
	//to get the question and choices 
    public List<Questiondto>  queastion() {
  	List<Questions> ques=questionsrepository.findAll();
  	List<Questiondto> questionDTOs = ques.stream()
              .map(this::convert)
              .collect(Collectors.toList());
  	return questionDTOs;
    } 
    public Questiondto convert(Questions question) {
 	return Questiondto.builder().id(question.getId()).question(question.getQuestion()).option1(question.getOption1()).option2(question.getOption2()).option3(question.getOption3()).build();
    }
    // to submit the student answer
    public Messageresponse submitQuiz(Quizsubmissiondto submissionDTO) {
   	Students student=studentsrepository.findById(submissionDTO.getStudentId()).orElseThrow();
   	Quiz quiz =quizrepository.findById(submissionDTO.getQuizId()).orElseThrow();
   	for(Answerdto answerDTo :submissionDTO.getSanswer()) {
   	Questions question=questionsrepository.findById(answerDTo.getQuestionId()).orElseThrow();
   	  Studentanswer answer=new Studentanswer();
    	answer.setStudents(student);
   		answer.setQuiz(quiz);
   		answer.setQuestions(question);
   		answer.setSanswer(answerDTo.getSanswer());	
   	    studentanswerrepository.save(answer);
    	}
	    return  Messageresponse.builder().message("answer submitted sucessfully").statusCode(200).build();
        }
    public String  deleteById(Long id) 
	 {
   	Questions question=questionsrepository.findById(id).orElseThrow(()-> new Useridnotfoundexception("id not found"));
   	   questionsrepository.delete(question);
   	    return "question updated sucessfully";
	 }
}
