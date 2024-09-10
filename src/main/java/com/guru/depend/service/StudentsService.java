package com.guru.depend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.Questions;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.entity.Students;
import com.guru.depend.repository.QuestionsRepository;
import com.guru.depend.repository.StudentAnswerRespository;
import com.guru.depend.repository.StudentsRepository;
@Service
public class StudentsService {

	@Autowired 
	private StudentsRepository studentsrepository;
	@Autowired
	private StudentAnswerRespository studentanswerepository;
    @Autowired
    private QuestionsRepository questionrepository;
    
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
 		Optional<Students> student= studentsrepository.findById(id);
 		if(student.isPresent()) {
 			return student.get();
 		}
 		else {
 		  throw new RuntimeException("student not found by this id");
 		}
 	}
 
//    public int evaluateStudentMark(Long id) {
//          List<StudentAnswer> answers = studentanswerepository.findBySchoolId(id);
//
//                int score = 0;
//
//             for (StudentAnswer studentAnswer : answers) {
//            Questions question = studentAnswer.getQuestions();
//
//            if (question != null && question.getAnswer() != null && studentAnswer.getSanswer() != null) {
//            
//                if (question.getAnswer().equals(studentAnswer.getSanswer())) {
//                    score++;
//                }
//            }
//        }
//        return score;
//    }
    
    //to get  student marks
//    public int allMarks(Long StudentId){	
//    	 List<StudentAnswer> answer= studentanswerepository.findAllByStudentsId(StudentId);
//    	 int score=0;
//    	 
//    	 for(StudentAnswer studentanswer:answer) {
//    		 Questions questions=studentanswer.getQuestions();
//    		 
//    		 if(questions!=null && questions.getAnswer()!= null && studentanswer.getSanswer()!=null) {
//    			 
//    			 if(questions.getAnswer().equals(studentanswer.getSanswer())) {
//    				 score++;
//    			 }
//    		 }
//    	 }
//    	 return score;
//    }
    
//    //to get the all students marks 
//    public Map<Long,Integer> allstudentsMark(){
//    	List<Students> students=studentsrepository.findAll();
//    	Map<Long,Integer> studentmarks=new HashMap<>();
//    	for(Students student:students) {
//    		Long studentId=student.getId();
//    		int mark=allMarks(studentId);
//    		studentmarks.put(studentId,mark);
//    		}
//    	return studentmarks;
//    	}
//   
//    public Map<String,String> allchoices(Long id){
//    
//    	Questions question=questionrepository.FindById(id);
//    	List<Choices> choices=choicerepository.findByQuestionsId(id);
//        Map<String,String> options=new HashMap<>();
//         
//        String q =question.getAnswer();
//        a=choices.;
//        
//        String e=question.
//        String a=choices.getChoice1();
//        options.put(e, a);
//    
//    }
    

   
//    //to get the question and choices based on questionid
//    public Map<String, String> getAllChoices(Long id) {
//        Map<String, String> options = new HashMap<>();
//       
//        Questions question = questionrepository.findById(id).orElse(null);
//         String q=question.getQuestion();
//
//        List<Choices> choices = choicerepository.findByQuestionsId(id);
//        if (choices != null) {
//            for (Choices choice : choices) {
//                options.put( choice.getChoice1(),q);
//                options.put( choice.getChoice2(),q);
//                options.put( choice.getChoice3(),q);
//            }
//        }
//        return options;
//    }

	//to update the studentdetails by the help of studentid
    public Students updateStudents(Long id,Students students)
     {
    	 if(studentsrepository.existsById(id)) 
    	 {
    		 students.setId(id);
    		 studentsrepository.save(students);
    	 }
    	 throw new RuntimeException("student not foud by id "+id);
     }

    //to delete the student with the help of studentid  
    public Map<String,Object> deleteById(Long id)
     {
 		Map<String,Object> response=new HashMap<>();
 		boolean ifidExit=studentsrepository.existsById(id);
 		if(ifidExit)
 		{
 			studentsrepository.deleteById(id);
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

