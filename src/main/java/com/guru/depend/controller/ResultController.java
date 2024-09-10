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

import com.guru.depend.entity.Quiz;
import com.guru.depend.entity.Result;
import com.guru.depend.service.ResultService;

@RestController
@RequestMapping("/api/result")
public class ResultController {

	
	@Autowired
	 private ResultService resultservice;
	
	@PostMapping("/")
	public Result addrecord( @RequestBody Result result) {
		return resultservice.createRecord(result);
	}
	@GetMapping("/")
	public List<Result> allData(){
		return resultservice.allData();
	}
	
	@PutMapping("/update/{id}")
	public Result updateRecord(@PathVariable Long id,@RequestBody Result result) {
		return resultservice.updateSchool(id, result);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Object> deleteQuiz(@PathVariable Long id) {
		return resultservice.deleteById(id);
	}
	
}
