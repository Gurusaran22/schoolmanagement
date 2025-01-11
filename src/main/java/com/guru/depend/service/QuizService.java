package com.guru.depend.service;

import java.util.List;
import java.util.stream.Collectors;

import com.guru.depend.dto.*;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.Quiz;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.entity.Students;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.QuestionsRepository;
import com.guru.depend.repository.QuizRepository;
import com.guru.depend.repository.StudentAnswerRespository;
import com.guru.depend.repository.StudentsRepository;
@Service
public class QuizService {

	@Autowired
	private QuizRepository  quizrepository;
	@Autowired
	 private QuestionsRepository questionsrepository;
	@Autowired
	private StudentsRepository studentsrepository;
	@Autowired
	private StudentAnswerRespository studentanswerrepository;
	
	public Quiz createRecord(Quiz quiz ) {
		return quizrepository.save(quiz);}
    //to view all the data
//	public List<Quiz> allData()
//	{
//		return quizrepository.findAll();
//	}
//	//to get the question and choices
//    public List<QuestionDTO>  queastion() {
//  	List<Questions> ques=questionsrepository.findAll();
//  	List<QuestionDTO> questionDTOs = ques.stream()
//              .map(this::convert)
//              .collect(Collectors.toList());
//  	return questionDTOs;
//    }
//    public QuestionDTO convert(Questions question) {
// 	return QuestionDTO.builder().id(question.getId()).question(question.getQuestion()).option1(question.getOption1()).option2(question.getOption2()).option3(question.getOption3()).build();
//    }
//    // to submit the student answer
//    public MessageResponse submitQuiz(QuizSubmissionDTO submissionDTO) {
//   	Students student=studentsrepository.findById(submissionDTO.getStudentId()).orElseThrow();
//   	Quiz quiz =quizrepository.findById(submissionDTO.getQuizId()).orElseThrow();
//   	for(AnswerDTO answerDTo :submissionDTO.getSanswer()) {
//   	Questions question=questionsrepository.findById(answerDTo.getQuestionId()).orElseThrow();
//   	  StudentAnswer answer=new StudentAnswer();
//    	answer.setStudents(student);
//   		answer.setQuiz(quiz);
//   		answer.setQuestions(question);
//   		answer.setSanswer(answerDTo.getSanswer());
//   	    studentanswerrepository.save(answer);
//    	}
//	    return  MessageResponse.builder().message("answer submitted sucessfully").statusCode(200).build();
//        }
    public String  deleteById(Long id) {
		Questions question = questionsrepository.findById(id).orElseThrow(() -> new UserIdNotFoundException("id not found"));
		questionsrepository.delete(question);
		return "**";
	}
}
