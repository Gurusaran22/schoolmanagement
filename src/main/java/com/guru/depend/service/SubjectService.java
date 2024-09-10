package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.Subject;
import com.guru.depend.repository.SubjectRepository;

@Service
public class SubjectService {

	 @Autowired
	 private SubjectRepository subjectrepository;
	 
	public Subject createRecord(Subject subject )
	{
		return subjectrepository.save(subject);
	}

    //to view all the data
	public List<Subject> allData()
	{
		return subjectrepository.findAll();
	}
	
	//to update the question details by the help of questionid
    public Subject updateSchool(Long id,Subject subject) 
	 {
		 if(subjectrepository.existsById(id))
		 {
			 subject.setId(id);
			 return subjectrepository.save(subject);
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
		boolean ifidExit=subjectrepository.existsById(id);		
		  if(ifidExit)
		  {
			subjectrepository.deleteById(id);
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
