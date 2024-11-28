package com.guru.depend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.Teachers;
import com.guru.depend.exception.UserIdNotFoundException;
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
    	return teachersrepository.findById(id).orElseThrow(()->new UserIdNotFoundException("teacher not found by this id"));
    }
	
	//to update the school details by the help of teacherid
	public String  updateTeacher(Long id,Teachers teachers) 
	 {
  	Teachers teacher=teachersrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
  	    teacher.setId(id);
  	    teachersrepository.save(teacher);
  	    return "teacher details updated sucessfully";
	 }
   
	//to delete the teacher with the help of teacherid  
	public String  deleteById(Long id) 
	 {
 	Teachers teacher=teachersrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
 	    teachersrepository.delete(teacher);
 	    return "teacher deleted sucessfully";
	 }
 }


