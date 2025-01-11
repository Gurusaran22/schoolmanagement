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
	public ResponseDTO createRecord(Teachers teachers) {
		return ResponseDTO.builder().status(Constants.CREATED).statusCode(200).data(teachersrepository.save(teachers)).build();
    }
	
	//to view all the data
    public ResponseDTO allData() {
		return ResponseDTO.builder().status(Constants.RETRIEVED).statusCode(200).data(teachersrepository.findAll()).build();
    }
    
	//to view the particular teacher details by the help of teacherid
    public ResponseDTO getTeachersDetails(Long id) {
	Teachers	teacher=teachersrepository.findById(id).orElseThrow(()->new UserIdNotFoundException("teacher not found by this id"));
		return ResponseDTO.builder().status(Constants.FOUND).statusCode(200).data(teacher).build();
    }
	
	//to update the school details by the help of teacherid
	public ResponseDTO  updateTeacher(Long id,Teachers teachers)
	 {
  	Teachers teacher=teachersrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
  	    teacher.setId(id);
		 return ResponseDTO.builder().status(Constants.MODIFIED).statusCode(200).data( teachersrepository.save(teacher)).build();
	 }
   
	//to delete the teacher with the help of teacherid  
	public ResponseDTO  deleteById(Long id)
	 {
 	Teachers teacher=teachersrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
 	    teachersrepository.delete(teacher);
		 return ResponseDTO.builder().status(Constants.REMOVED).statusCode(200).data("****").build();
	 }
 }


