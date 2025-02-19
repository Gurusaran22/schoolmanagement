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
//
//    public String  deleteById(Long id) {
//		Questions question = questionsrepository.findById(id).orElseThrow(() -> new UserIdNotFoundException("id not found"));
//		questionsrepository.delete(question);
//		return "***";
//	}

}
