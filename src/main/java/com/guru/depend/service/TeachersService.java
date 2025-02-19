package com.guru.depend.service;

import java.util.List;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
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
	public Teachers createRecord(Teachers teachers) {
		return teachersrepository.save(teachers);
    }
	
	//to view all the data
    public List<Teachers> allData() {
		return teachersrepository.findAll();
    }
    
	//to view the particular teacher details by the help of teacherid
    public Teachers getTeachersDetails(Long id) {
	Teachers	teacher=teachersrepository.findById(id).orElseThrow(()->new UserIdNotFoundException("teacher not found by this id"));
		return teacher;
    }
	
	//to update the school details by the help of teacherid
	public Teachers  updateTeacher(Long id,Teachers teachers)
	 {
  	Teachers teacher=teachersrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
  	    teacher.setId(id);
		 return teachersrepository.save(teacher);
	 }
   
	//to delete the teacher with the help of teacherid  
	public String  deleteById(Long id)
	 {
 	Teachers teacher=teachersrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
 	    teachersrepository.delete(teacher);
		 return "****";
	 }
 }


