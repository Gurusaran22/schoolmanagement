package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Teachers;
import com.guru.depend.repository.Teachersrepository;
@Service
public class Teachersservice {

	@Autowired 
	private Teachersrepository teachersrepository;
	

	//to save the teacher
	public Teachers createRecord(Teachers teachers) 
	{
		return teachersrepository.save(teachers);
    }
	

	//to view all the data
    public List<Teachers> allData()
    {
    	return teachersrepository.findAll();
    }
    
   
	//to update the school details by the help of teacherid
	 public Teachers updateTeachers(int id,Teachers teachers) 
	 {
		 if(teachersrepository.existsById(id))
		 {
			 teachers.setId(id);
			 return teachersrepository.save(teachers);
		 }
		 else 
		 {
	    	 throw new RuntimeException("school id not found"+id);	
		 }
	 }
	 

	 //to delete the teacher with the help of teacherid  
    public Map<String,Object> deleteById(int id)
{
		Map<String,Object> response=new HashMap<>();
		boolean ifidExit=teachersrepository.existsById(id);	
		if(ifidExit)
		{
			teachersrepository.deleteById(id);
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


