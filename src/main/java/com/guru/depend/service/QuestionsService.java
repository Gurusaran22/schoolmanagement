package com.guru.depend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.dto.Questiondto;
import com.guru.depend.entity.Questions;
import com.guru.depend.exception.Useridnotfoundexception;
import com.guru.depend.repository.Questionsrepository;
@Service
public class Questionsservice {
	
	@Autowired 
	private Questionsrepository questionsrepository;
    //to save the question
	public Questions createRecord(Questions questions )
	{
		return questionsrepository.save(questions);
	}
    //to view all the data
	public List<Questions> allData()
	{
		return questionsrepository.findAll();
	}
	//to update the question details by the help of questionid
    public String  updateQuestion(Long id,Questions questions) 
	 {
    	Questions question=questionsrepository.findById(id).orElseThrow(()-> new Useridnotfoundexception("id not found"));
    	    question.setId(id);
    	    questionsrepository.save(question);
    	    return "question updated sucessfully";
	 }
    // to get the particular question with the help of question id
    public List<Questiondto>  getQuestion(Long id) {
    	Optional<Questions> ques=questionsrepository.findById(id);
    	List<Questiondto> questionDTOs = ques.stream()
                .map(this::all)
                .collect(Collectors.toList());
    	return questionDTOs;
    }
    public Questiondto all(Questions question) {
    	return Questiondto.builder().id(question.getId()).question(question.getQuestion()).option1(question.getOption1()).option2(question.getOption2()).option3(question.getOption3()).build();
    }
   //tp view the question by the subject with the help of subject id 
    public List<Questiondto> getQuestionBySubject(Long id){
    	return questionsrepository.findBysubject_id(id);
    }
    //to delete the question with the help of questionid  
    public String deleteById(Long id) {
    	Questions questions=questionsrepository.findById(id).orElseThrow(()-> new Useridnotfoundexception("id not found"+id)) ;
    			questionsrepository.delete(questions);
    	return "question deleted sucessfully"+id;
    }
    
}
