package com.guru.depend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.MyUser;
import com.guru.depend.entity.User;
import com.guru.depend.repository.userRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private userRepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      User users=userrepository.findByUsername(username);
		if(users==null) {
			System.out.println("user not found");
		}
		return new MyUser(users);
	
	}

}
