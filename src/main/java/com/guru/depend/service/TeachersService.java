package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Students;
import com.guru.depend.entity.Teachers;
import com.guru.depend.repository.TeachersRepository;
@Service
public class TeachersService {

	@Autowired 
	private TeachersRepository teachersrepository;
	 
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
    
	//to view the particular teacher details by the help of teacherid
	public Teachers getTeachersDetails(Long id) {
		 Optional<Teachers> teacher= teachersrepository.findById(id);
	 		if(teacher.isPresent()) {
	 			return teacher.get();
	 		}
	 		else {
	 			throw new RuntimeException();
	 		}
	 	}
	
	//to update the school details by the help of teacherid
    public Teachers updateTeachers(Long id,Teachers teachers) 
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
    public Map<String,Object> deleteById(Long id)
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


