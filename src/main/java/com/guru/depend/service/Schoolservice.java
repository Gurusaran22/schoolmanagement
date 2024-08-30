package com.guru.depend.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Test;
import com.guru.depend.repository.Schoolrepository;
import com.guru.depend.repository.Studentsrepository;
import com.guru.depend.repository.Teachersrepository;

@Service
public class Schoolservice {

	@Autowired 
	private Schoolrepository schoolrepository;
	
	@Autowired 
	private Studentsrepository studentrepository;
	
	@Autowired 
	private Teachersrepository teachersrepository;
	
	public School createRecord( School school) {
		return schoolrepository.save(school);
	}
	
	public List<School> allData(){
		return schoolrepository.findAll();
	}
	
	public List<Object> getallstudentandteacherbyschoolname(@PathVariable int id){
		 
		List<Object> schools=new LinkedList<>();
		
		schools.add(studentrepository.findbyschoolid(id));
		schools.add(teachersrepository.findbyschoolid(id));
		
		return schools;
		
	}
}
