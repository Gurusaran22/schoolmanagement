package com.guru.depend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guru.depend.entity.Test;
import com.guru.depend.service.Testservice;

@RestController

@RequestMapping("/api/Test")
public class Testcontroller {
	 
	@Autowired 
  private Testservice testservice;
	
	//to store an test
    @PostMapping("/")
	public Test createRecord(@RequestBody Test test) {
		return testservice.createRecord(test);
	}
    
    //to view the list
    @GetMapping("/")
    public List<Test> allData(){
    	return testservice.allData();
    }
    
    //to update the test by testid
    @PutMapping("/update/{id}")
    public Test updateTest(@PathVariable int id,@RequestBody Test test) {
    	return testservice.updateTest(id, test);
    }
    //to delete the test by testid
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteByIdRecord(@PathVariable int id){
		return testservice.deleteById(id);
	}
}
