package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.Choices;
import com.guru.depend.repository.Choicesrepository;

@Service
public class Choicesservice {

	
	@Autowired 
	private Choicesrepository choicesrepository;

	//to save the choice
	public Choices createRecord(Choices choices) 
	{
		return choicesrepository.save(choices);
	}
	

	//to view all the data
	public List<Choices> allData()
	{
		return choicesrepository.findAll();
	}
	

	//to update the choices by the help of choiceid
	public Choices updateChoices(int id ,Choices choices){
		if(choicesrepository.existsById (id))
		{
			choices.setId(id);
			 return choicesrepository.save(choices);
     	 } 
		else {
			throw new RuntimeException("choices not foud by id"+id);
		}
	}
	

	 //to delete the choices with the help of choiceid  
	public Map<String,Object> deleteById(int id)
	{
		Map<String,Object> response=new HashMap<>();
		boolean ifidExit=choicesrepository.existsById(id);
		if(ifidExit)
		{
			choicesrepository.deleteById(id);
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
