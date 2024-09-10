package com.guru.depend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.guru.depend.dto.QuestionDTO;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.School;
import com.guru.depend.repository.QuestionsRepository;
@Service
public class QuestionsService {
	
	@Autowired 
	private QuestionsRepository questionsrepository;
	 
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
	
	//to update the question details by the help of questionid
    public Questions updateSchool(Long id,Questions questions) 
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
    
    //to get the question with choice with the help of the question id
    
    public Optional<Questions> getQuestionWithChoices(Long id) {
    	if(id!=null) {
    	return questionsrepository.findById(id);
    } 
    	else {
    		throw new RuntimeException("id not found"+id);
    	}
    }
    
   //to get the question and choices with the help of the question id
    public List<String> getQuestionChoices(Long id){
    	
    	List<String> all=new ArrayList<>();
    	Questions allchoices=questionsrepository.findAllById(id);
    	 String a=allchoices.getQuestion();
    	 String c=allchoices.getOption1();
    	 String c1=allchoices.getOption2();
    	 String c2=allchoices.getOption3();
    	  all.add(a);
    	  all.add(c);
    	  all.add(c1);
    	  all.add(c2);
    	  return all;
    }

	
	
    //to delete the question with the help of questionid  
    public Map<String,Object> deleteById(Long id)
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
