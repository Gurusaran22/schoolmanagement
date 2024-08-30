package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.Test;
import com.guru.depend.repository.Testrepository;
@Service
public class Testservice {
	
	@Autowired 
	private Testrepository testrepository;
	

	//to save the test
	public Test createRecord( Test test)
	{
		return testrepository.save(test);
	}
	

	//to view all the data
	public List<Test> allData()
	{
		return testrepository.findAll();
	}
	
	//to update the  test by the help of testid
	public Test updateTest(int id,Test test) {
		if(testrepository.existsById(id)) {
			test.setId(id);
			 return testrepository.save(test);
		}
		else
		{
			throw new RuntimeException("test not found by testid"+id);
		}
	}

	 //to delete the test with the help of testid  
    public Map<String,Object> deleteById(int id)
   {
		Map<String,Object> response=new HashMap<>();
		boolean ifidExit=testrepository.existsById(id);
		if(ifidExit)
		{
			testrepository.deleteById(id);
			response.put("Id deleted sucessfully", id);
			return response;
		}
		else
		{
			response.put("Id not found",id);
			return response;
		}
	}

 
}
