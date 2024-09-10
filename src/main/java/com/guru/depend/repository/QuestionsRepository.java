package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guru.depend.entity.Questions;
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long>  {
 
  // Questions FindById(Long id);
}
