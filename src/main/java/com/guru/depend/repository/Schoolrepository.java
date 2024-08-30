package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.School;

public interface Schoolrepository extends JpaRepository<School, Integer> {

}
