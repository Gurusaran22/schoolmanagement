package com.guru.depend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.guru.depend.entity.Test;
import com.guru.depend.service.Testservice;

@RequestMapping("/api/Test")
public class Testcontroller {
	
	@Autowired 
  private Testservice testservice;
	
    @PostMapping("/")
	public Test createRecord(@RequestBody Test test) {
		return testservice.createRecord(test);
	}
    
    @GetMapping("/")
    public List<Test> allData(){
    	return testservice.allData();
    }

}
