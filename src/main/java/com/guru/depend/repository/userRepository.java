package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.User;

public interface userRepository extends JpaRepository<User,Long>{
      
	   User findByUsername(String username);
}
