package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.Questions;

public interface Questionsrepository extends JpaRepository<Questions, Integer>  {

}
