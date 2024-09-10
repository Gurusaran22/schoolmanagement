package com.guru.depend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guru.depend.entity.StudentAnswer;
@Repository
public interface StudentAnswerRespository extends JpaRepository<StudentAnswer,Long> {
    //to get the particular student answer
	
	//@Query("select s.id from StudentAnswer sa join students s where sa.id=:s.id ")
	

	 @Query("SELECT s FROM StudentAnswer s WHERE s.id= :id")
	 List<StudentAnswer> findBySchoolId(@Param("id")Long id);

	List<StudentAnswer> findAllByStudentsId(Long studentId);

}