package com.guru.depend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guru.depend.entity.Students;

import java.util.Optional;

public interface StudentsRepository  extends JpaRepository<Students, Long>{
//
     	Long findAllBySchoolId(Long id);
	
	 	@Query("SELECT COUNT(s) FROM Students s WHERE s.id= :id")
		
	    Long countBySchoolId(@Param("id") Long id);


//	Students findByEmail(String username);
} 
