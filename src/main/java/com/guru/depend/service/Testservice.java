package com.guru.depend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guru.depend.entity.Test;
import com.guru.depend.repository.Testrepository;

public class Testservice {
	
	@Autowired 
	private Testrepository testrepository;
	
	public Test createRecord( Test test) {
		return testrepository.save(test);
	}
	
	public List<Test> allData(){
		return testrepository.findAll();
	}


}
