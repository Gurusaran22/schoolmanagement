package com.guru.depend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guru.depend.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    //Page<School> getSchoolByField(int pageIndex,int pageSize,String field);
}
	
