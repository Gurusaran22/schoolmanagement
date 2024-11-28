package com.guru.depend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guru.depend.dto.QuestionDTO;
import com.guru.depend.entity.Questions;
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long>  {
 
	List<QuestionDTO> findBysubject_id(Long id);
}
