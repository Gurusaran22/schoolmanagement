package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.Result;
import com.guru.depend.repository.ResultRepository;

@Service
public class ResultService {

	@Autowired 
	private ResultRepository resultrepository;
	
	public Result createRecord(Result result )
	{
		return resultrepository.save(result);
	}

    //to view all the data
	public List<Result> allData()
	{
		return resultrepository.findAll();
	}
	
	//to update the question details by the help of questionid
    public Result updateSchool(Long id,Result result) 
	 {
		 if(resultrepository.existsById(id))
		 {
			 result.setId(id);
			 return resultrepository.save(result);
		 }
		 else 
		 {
	    	 throw new RuntimeException("question id not found by id"+id);	
		 }
	 }
	
    //to delete the question with the help of questionid  
    public Map<String,Object> deleteById(Long id)
    {
		Map<String,Object> response=new HashMap<>();
		boolean ifidExit=resultrepository.existsById(id);		
		  if(ifidExit)
		  {
			resultrepository.deleteById(id);
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
