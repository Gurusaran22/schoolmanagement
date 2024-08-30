package com.guru.depend.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Test;
import com.guru.depend.repository.Schoolrepository;
import com.guru.depend.repository.Studentsrepository;
import com.guru.depend.repository.Teachersrepository;

import jakarta.persistence.Id;

@Service
public class Schoolservice {

	@Autowired 
	private Schoolrepository schoolrepository;
	
	@Autowired 
	private Studentsrepository studentrepository;
	
	@Autowired 
	private Teachersrepository teachersrepository;
	
	//to save the school
	public School createRecord( School school) {
		return schoolrepository.save(school);
	}
	
	//to view all the data
	public List<School> allData(){
		return schoolrepository.findAll();
	}	
	
	//to get all school and teacher by  one particular school based on schoolid 
	public List<Object> getallstudentandteacherbyschoolname(@PathVariable int id){
		List<Object> schools=new LinkedList<>();
		schools.add(studentrepository.findAllBySchoolId(id));
		schools.add(teachersrepository.findAllBySchoolId(id));
		return schools;	
	}
   
	//to  see the student count in particular school by school id
	public Map<String,Long> getStudentCountById(int id){
		Map<String,Long> studentCount=new LinkedHashMap<>();
		studentCount.put("total students count",studentrepository.findAllStudentCountBySchoolId(id));
		return studentCount;
	}
	
    //to see the teacher count in particular school by schoolid
	public Map<String,Long> getTeachersById(int id){
		Map<String,Long> teachercount=new LinkedHashMap<>();
		teachercount.put("total teachers count",teachersrepository.findAllTeacherCountBySchoolId(id));
		return teachercount;
	}
	
	//to update the school details by the help of schoolid
	
	 public  School updateSchool(int id,School school) {
	 
		 if(schoolrepository.existsById(id))
	 {
			 school.setId(id);
			 return schoolrepository.save(school);
		 }
		 else 
		 {
	    	 throw new RuntimeException("school id not found"+id);	
		 }
	 }
	 
	 //to delete the school with the help of schoolid  
	public Map<String,Object> deleteById(int id){
		Map<String, Object> response=new HashMap<>();
		boolean ifidExit=schoolrepository.existsById(id);
		if(ifidExit) 
		{
			schoolrepository.deleteById(id);
			response.put("Id deleted sucessfully", id);
			return response;
		}
		else 
		{
			response.put("id not found", id);
		    return response;
        }
	}
	
}
