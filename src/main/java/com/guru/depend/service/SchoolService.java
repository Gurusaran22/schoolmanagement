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
import com.guru.depend.entity.Students;
import com.guru.depend.repository.SchoolRepository;
import com.guru.depend.repository.StudentsRepository;
import com.guru.depend.repository.TeachersRepository;

import jakarta.persistence.Id;

@Service
public class SchoolService { 

	@Autowired 
	private SchoolRepository schoolrepository;
	@Autowired 
	private StudentsRepository studentrepository;
	@Autowired 
	private TeachersRepository teachersrepository;
	
	//to save the school
	public School createRecord( School school) {
		return schoolrepository.save(school);
	}
	
	//to view all the data
	public List<School> allData(){
		return schoolrepository.findAll();
	}	//C:\Users\Fyndus Tech Soln\Documents\workspace-spring-tool-suite-4-4.24.0.RELEASE\qschoo
	
	//to get all school and teacher by  one particular school based on schoolid 
	public List<Object> getallstudentandteacherbyschoolname(@PathVariable Long id){
		List<Object> schools=new LinkedList<>();
		schools.add(studentrepository.findAllBySchoolId(id));
		schools.add(teachersrepository.findAllBySchoolId(id));
		return schools;	
	}
	
	public School getSchoolDetailsById(Long id) {
		Optional<School> school= schoolrepository.findById(id);
 		if(school.isPresent()) {
 			return school.get();
 		}
 		else {
 			throw new RuntimeException();
 		}
 	}
 
	//to  see the student count in particular school by school id
	public Map<String,Long> getStudentCountById(Long id){
		Long count=studentrepository.countBySchoolId(id);
		Map<String,Long> studentCount=new HashMap<>();
		studentCount.put("total students count",count);
		return studentCount;
	}
	
    //to see the teacher count in particular school by schoolid
	public Map<String,Long> getTeachersById(Long id){
		Long count=teachersrepository.teacherscountBySchoolId(id);
		Map<String,Long> teachercount=new HashMap<>();
		teachercount.put("total teachers count",count);
		return teachercount;
	}
	
	//to update the school details by the help of schoolid
	 public  School updateSchool(Long id,School school) {
	 
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
	public Map<String,Object> deleteById(Long id){
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
