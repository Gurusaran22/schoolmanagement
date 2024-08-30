package com.guru.depend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guru.depend.entity.Teachers;
import com.guru.depend.service.Teachersservice;

@RequestMapping("/api/Teachers")
public class Teacherscontroller {
	
	@Autowired 
	private Teachersservice teachersservice;
	
	@PostMapping("/")
	public Teachers createRecord(@RequestBody Teachers teachers) {
		return teachersservice.createRecord(teachers);
	}
	
	@GetMapping("/")
	public List<Teachers> allData(){
		return teachersservice.allData();
	}

}
