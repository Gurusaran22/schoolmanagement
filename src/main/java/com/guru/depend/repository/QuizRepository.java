package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.Quiz;

public interface Quizrepository  extends JpaRepository<Quiz,Long>{

}
