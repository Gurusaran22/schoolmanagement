package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.Test;

public interface Testrepository extends JpaRepository<Test, Integer>{

}
