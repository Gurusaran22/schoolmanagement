package com.guru.depend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.Teachers;

public interface Teachersrepository extends JpaRepository<Teachers, Integer> {

	List<Object> findbyschoolid(int id);
}
