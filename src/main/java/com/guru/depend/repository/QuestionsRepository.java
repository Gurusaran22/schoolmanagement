package com.guru.depend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guru.depend.dto.Questiondto;
import com.guru.depend.entity.Questions;
@Repository
public interface Questionsrepository extends JpaRepository<Questions, Long>  {
 
	List<Questiondto> findBysubject_id(Long id);
}
