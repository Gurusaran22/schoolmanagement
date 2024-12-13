package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.Studentanswer;
import com.guru.depend.entity.Students;
import com.guru.depend.exception.Useridnotfoundexception;
import com.guru.depend.repository.Questionsrepository;
import com.guru.depend.repository.Studentanswerespository;
import com.guru.depend.repository.Studentsrepository;
@Service
public class Studentsservice {

	@Autowired 
	private Studentsrepository studentsrepository;
	@Autowired
	private Studentanswerespository studentanswerepository;
    @Autowired
    private Questionsrepository questionrepository;
    
	//to save the students
	public Students createRecord( Students students)
	{
		return 	studentsrepository.save(students);	
    }
	
    //to see all the data
	public List<Students> allData1() {
		// TODO Auto-generated method stub
		return studentsrepository.findAll();
	}
	
 	//to view the particular student details by the help of studentid
	public Students getStudentDetails(Long id) {
		return this.studentsrepository.findById(id).orElseThrow(()->new Useridnotfoundexception("student not found by this id"));	 
	}
 	
    public int evaluateStudentMark(Long id) {
          List<Studentanswer> answers = studentanswerepository.findBySchoolId(id);
                int score = 0;
             for (Studentanswer studentAnswer : answers) {
            Questions question = studentAnswer.getQuestions();
         if (question != null && question.getCorrectAnswer() != null && studentAnswer.getSanswer() != null) {
                	if(studentAnswer.getSanswer().contains(question.getCorrectAnswer())) {
                    score++;
                    }
           }
       }
        return score;
    }
    
    //to get  student marks
    public int allMarks(Long StudentId){	
    	 List<Studentanswer> answer= studentanswerepository.findAllByStudentsId(StudentId);
    	 int score=0;
    	 
    	 for(Studentanswer studentanswer:answer) {
    		 Questions questions=studentanswer.getQuestions();
    		 
          if(questions!=null && questions.getCorrectAnswer()!= null && studentanswer.getSanswer()!=null) {
    			 if(studentanswer.getSanswer().contains(questions.getCorrectAnswer())) {
    				 score++;
    			 }
          }
    	 }
    	 return score;
    }
    
    //to get the all students marks 
    public Map<Long,Integer> allstudentsMark(){
    	List<Students> students=studentsrepository.findAll();
    	Map<Long,Integer> studentmarks=new HashMap<>();
    	for(Students student:students) {
    		Long studentId=student.getId();
    		int mark=allMarks(studentId);
    		studentmarks.put(studentId,mark);
    		}
    	return studentmarks;
    	}
   
	//to update the studentdetails by the help of studentid
    public String  updateStudent(Long id,Students students) 
	 {
   	Students std=studentsrepository.findById(id).orElseThrow(()-> new Useridnotfoundexception("id not found"));
   	    std.setId(id);
   	    studentsrepository.save(std);
   	    return "student updated sucessfully";
	 }
 
    //to delete the student with the help of studentid  
    public String  deleteById(Long id) 
	 {
  	Students std=studentsrepository.findById(id).orElseThrow(()-> new Useridnotfoundexception("id not found"));
  	    studentsrepository.delete(std);
  	    return "student deleted sucessfully";
	 }

}
