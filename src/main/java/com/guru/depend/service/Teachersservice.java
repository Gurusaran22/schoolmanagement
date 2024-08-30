package com.guru.depend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guru.depend.entity.Teachers;
import com.guru.depend.repository.Teachersrepository;

public class Teachersservice {

	@Autowired 
	private Teachersrepository teachersrepository;
	
	public Teachers createRecord(Teachers teachers) {
		return teachersrepository.save(teachers);
  }
	
    public List<Teachers> allData(){
    	return teachersrepository.findAll();
    }
 }


