package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.Questions;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.repository.QuestionsRepository;
import com.guru.depend.repository.StudentAnswerRespository;

@Service
public class StudentAnswerService {

	 @Autowired
	 private StudentAnswerRespository studentanswerrepository;
	
	//to save the studentanswer
	public StudentAnswer addRecord(StudentAnswer studentanswer) {
		return studentanswerrepository.save(studentanswer);
	}
	
	//to view all the data
    public List<StudentAnswer> allData()
    {	
   	 return studentanswerrepository.findAll();
    }
    
    //to view the particular student details by the help of studentid
	public StudentAnswer getStudentDetails(Long id) {
		Optional<StudentAnswer> student= studentanswerrepository.findById(id);
		if(student.isPresent()) {
			return student.get();
		}
		else {
		  throw new RuntimeException();
		}
	}	
	
	//to update the studentdetails by the help of studentid
    public StudentAnswer updateAnswer(Long id,StudentAnswer studentanswer)
    {
   	    if(studentanswerrepository.existsById(id)) 
   	    {
   		 studentanswer.setId(id);
   		 return  studentanswerrepository.save(studentanswer);
   	    }
   	     throw new RuntimeException("student not foud by id "+id);
    }
    
    //to delete the student with the help of studentid  
    public Map<String,Object> deleteById(Long id)
    {
		Map<String,Object> response=new HashMap<>();
		boolean ifidExit=studentanswerrepository.existsById(id);
		if(ifidExit)
		{
			studentanswerrepository.deleteById(id);
			response.put("Id deleted sucessfully", id);
			return response;
		}
		else
		{
			response.put("Id not found",id);
			return response;
		}
	}
//	
//    //evaluate the student mark based on the student id we will compare the crt question and student answer in loop
//     public int evalualteStudentMark(Long id) {
//    	 
//    	 List<StudentAnswer> answer=studentanswerrepository.findByStudentId(id);
//    	 
//    	 int score=0;
//    	 for(StudentAnswer answers:answer) {
//    		 Questions question=answers.getQuestions();
//    		 if(question.getAnswer().equals(answers.getSanswer())) {
//    			 score++;
//    		 }
//    	 }
//    	 return score;
//     }
	
    

}
