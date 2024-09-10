package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.Quiz;
import com.guru.depend.repository.QuizRepository;
@Service
public class QuizService {

	@Autowired
	private QuizRepository  quizrepository;
	
	public Quiz createRecord(Quiz quiz )
	{
		return quizrepository.save(quiz);
	}

    //to view all the data
	public List<Quiz> allData()
	{
		return quizrepository.findAll();
	}
	
	//to update the question details by the help of questionid
    public Quiz updateQuiz(Long id,Quiz quiz) 
	 {
		 if(quizrepository.existsById(id))
		 {
			 quiz.setId(id);
			 return quizrepository.save(quiz);
		 }
		 else 
		 {
	    	 throw new RuntimeException("question id not found by id"+id);	
		 }
	 }
	
    //to delete the question with the help of questionid  
    public Map<String,Object> deleteById(Long id)
    {
		Map<String,Object> response=new HashMap<>();
		boolean ifidExit=quizrepository.existsById(id);		
		  if(ifidExit)
		  {
			quizrepository.deleteById(id);
			response.put("Id deleted sucessfully", id);
			return response;
		  }
		else 
		{
			response.put("Id not found",id);
			return response;
		}
	}

}
