package com.guru.depend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.Subject;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.SubjectRepository;

@Service
public class SubjectService {

	 @Autowired
	 private SubjectRepository subjectrepository;
	 
	public Subject createRecord(Subject subject )
	{
		return subjectrepository.save(subject);
	}

    //to view all the data
	public List<Subject> allData()
	{
		return subjectrepository.findAll();
	}
	
	//to update the question details by the help of questionid
	 public String  updateSubject(Long id,Subject subject) 
	 {
   	Subject std=subjectrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
   	    std.setId(id);
   	    subjectrepository.save(std);
   	    return "question updated sucessfully";
	 }
 
	 //to delete the question with the help of questionid  
	 public String  deleteById(Long id) 
	 {
   	Subject std=subjectrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
   	    subjectrepository.delete(std);
   	    return "subject updated sucessfully";
	 }
}
