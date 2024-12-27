package com.guru.depend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guru.depend.entity.Teachers;
@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {

	List<Object> findAllBySchoolId(Long id);
	
	@Query("SELECT COUNT(t) FROM Teachers t WHERE t.id= :id")
	
	   Long teacherscountBySchoolId(@Param("id") Long id);


	//Teachers findByEmail(String username);
}
