package com.guru.depend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guru.depend.entity.School;
import com.guru.depend.entity.Students;
import com.guru.depend.repository.Studentsrepository;

public class Studentsservice {

	@Autowired 
	private Studentsrepository studentsrepository;
	
	public Students createRecord( Students students) {
		return 	studentsrepository.save(students);	
}
     public List<Students> allData(){	
    	 return studentsrepository.findAll();
     }
	
}

