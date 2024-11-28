package com.guru.depend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guru.depend.entity.User;
import com.guru.depend.service.UserService;

@RestController
@RequestMapping("/api/register")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
	  return userservice.register(user);
	}
	
	@PostMapping("/login")
	public String checkUser(@RequestBody User user) {
		return userservice.verify(user);
	}
	
	@GetMapping("/all")
	public List<User> getMethodName() {
		return userservice.allData();
	}
	 
}
