package com.guru.depend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.guru.depend.entity.Choices;

@Repository 
public interface Choicesrepository extends JpaRepository<Choices,Integer> {

	List<Choices> Choicebyid(int id);
}
