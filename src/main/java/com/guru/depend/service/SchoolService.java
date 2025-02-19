package com.guru.depend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.guru.depend.entity.School;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.SchoolRepository;
import com.guru.depend.repository.StudentsRepository;
import com.guru.depend.repository.TeachersRepository;
@Builder
@Service
public class SchoolService { 

	@Autowired 
	private SchoolRepository schoolrepository;
	@Autowired 
	private StudentsRepository studentrepository;
	@Autowired 
	private TeachersRepository teachersrepository;
	
	//to save the school
	public School createRecord( School school) {return schoolrepository.save(school);}

	//to view all the data
	public List<School> allData(){return schoolrepository.findAll();}
	
	//to get all school and teacher by  one particular school based on schoolid 
	public List<Object> getallstudentandteacherbyschoolname(@PathVariable Long id){
		List<Object> schools=new LinkedList<>();
		schools.add(studentrepository.findAllBySchoolId(id));
		schools.add(teachersrepository.findAllBySchoolId(id));
		return schools;
	}
	
		public School getSchoolDetailsById(Long id) {
	School schl=schoolrepository.findById(id).orElseThrow(()->new UserIdNotFoundException("school not found by this id"));
			return schl;
	}
	
	//to  see the student count in particular school by school id
	public Map<String, Long> getStudentCountById(Long id){
		Long count=studentrepository.countBySchoolId(id);
		Map<String,Long> studentCount=new HashMap<>();
	    studentCount.put("total students count",count);
		return studentCount;
	}
//	public Map<String,Long> getStudentCountById(Long id){
//		Long count=studentrepository.countBySchoolId(id);
//		Map<String,Long> studentCount=new HashMap<>();
//		studentCount.put("total students count",count);
//		return studentCount;
//	}
	
    //to see the teacher count in particular school by schoolid
	public Map<String, Long> getTeachersById(Long id){
		Long count=teachersrepository.teacherscountBySchoolId(id);
		Map<String,Long> teachercount=new HashMap<>();
		teachercount.put("total teachers count",count);
		return teachercount;
	}
	
	//to update the school details by the help of schoolid
	 public School  updateSchool(Long id,School school)
	 {
    	School schl=schoolrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
    	    schl.setId(id);
			schl.setName(schl.getName());
			schl.setEmailId(schl.getEmailId());
			schl.setLocation(schl.getLocation());
		 return schoolrepository.save(schl);
	 }
	 
	 //to delete the school with the help of schoolid  
	 public String  DeleteById(Long id)
	 {
    	School schl=schoolrepository.findById(id).orElseThrow(()-> new UserIdNotFoundException("id not found"));
    	    schoolrepository.delete(schl);
		 return "**";
	 }

	 public Page<School> getSchoolByPage(int pageIndex, int pageSize, String field){
		     Sort sort=Sort.by(Sort.Direction.ASC,field);
	        Pageable pageReq=PageRequest.of(pageIndex, pageSize, sort);
		 return schoolrepository.findAll(pageReq);
		 //sorting the page
	 }
	 
}
