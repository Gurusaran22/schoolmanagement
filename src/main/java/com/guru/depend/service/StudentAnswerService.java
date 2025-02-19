package com.guru.depend.service;

import java.util.List;
import java.util.Optional;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guru.depend.entity.StudentAnswer;
import com.guru.depend.exception.UserIdNotFoundException;
import com.guru.depend.repository.StudentAnswerRespository;

@Service
public class StudentAnswerService {

	@Autowired
	private StudentAnswerRespository studentanswerrepository;

	//to save the studentanswer
	public StudentAnswer addRecord(StudentAnswer studentanswer) {
		return studentanswerrepository.save(studentanswer);
	}

	//to view all the data
	public List<StudentAnswer> allData() {
		return studentanswerrepository.findAll();
	}

	//to view the particular student details by the help of studentid
	public StudentAnswer getStudentDetails(Long id) {
		StudentAnswer Studentans = studentanswerrepository.findById(id).orElseThrow(() -> new UserIdNotFoundException("id not found by this id" + id));
		return Studentans;
	}
}
