package com.guru.depend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.depend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByEmail(String email);


}
