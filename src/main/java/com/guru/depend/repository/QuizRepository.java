package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.Quiz;

public interface QuizRepository  extends JpaRepository<Quiz,Long>{

}
