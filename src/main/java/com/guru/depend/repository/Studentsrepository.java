package com.guru.depend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.Students;

public interface Studentsrepository  extends JpaRepository<Students, Integer>{

	List<Object> findbyschoolid(int id);

}
