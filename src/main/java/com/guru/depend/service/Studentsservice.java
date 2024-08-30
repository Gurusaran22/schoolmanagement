package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Students;
import com.guru.depend.repository.Studentsrepository;
@Service
public class Studentsservice {

	@Autowired 
	private Studentsrepository studentsrepository;
	

	//to save the students
	public Students createRecord( Students students)
	{
		return 	studentsrepository.save(students);	
    }
	
	//to view all the data
     public List<Students> allData()
     {	
    	 return studentsrepository.findAll();
     }
   

 	//to update the studentdetails by the help of studentid
     public Students updateStudents(int id,Students students) 
     {
    	 if(studentsrepository.existsById(id)) 
    	 {
    		 students.setId(id);
    		 studentsrepository.save(students);
    	 }
    	 throw new RuntimeException("student not foud by id "+id);
     }


	 //to delete the student with the help of studentid  
     public Map<String,Object> deleteById(int id)
     {
 		Map<String,Object> response=new HashMap<>();
 		boolean ifidExit=studentsrepository.existsById(id);
 		if(ifidExit)
 		{
 			studentsrepository.deleteById(id);
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

