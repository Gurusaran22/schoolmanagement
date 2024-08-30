package com.guru.depend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.guru.depend.entity.Choices;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.School;
import com.guru.depend.repository.Choicesrepository;
import com.guru.depend.repository.Questionsrepository;
@Service
public class Questionsservice {
	
	@Autowired 
	private Questionsrepository questionsrepository;
	@Autowired
	private Choicesrepository choicesrepository;
     

	//to save the question
	public Questions createRecord(Questions questions )
	{
		return questionsrepository.save(questions);
	}


	//to view all the data
	public List<Questions> allData()
	{
		return questionsrepository.findAll();
	}
	
  //
//	public List<QuestionDto> getallchoicesbyid(@PathVariable int id)
//	{
//		List<QuestionDto> question=new LinkedList<>();
//	    question.addAll(choicesrepository.Choicebyid(id));
//		return question;		
//	}
	
	//to update the question details by the help of questionid
	 public Questions updateSchool(int id,Questions questions) 
	 {
		 if(questionsrepository.existsById(id))
		 {
			 questions.setId(id);
			 return questionsrepository.save(questions);
		 }
		 else 
		 {
	    	 throw new RuntimeException("question id not found by id"+id);	
		 }
	 }
	 

	 //to delete the question with the help of questionid  
    public Map<String,Object> deleteById(int id)
    {
		Map<String,Object> response=new HashMap<>();
		boolean ifidExit=questionsrepository.existsById(id);		
		if(ifidExit)
		{
			questionsrepository.deleteById(id);
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
