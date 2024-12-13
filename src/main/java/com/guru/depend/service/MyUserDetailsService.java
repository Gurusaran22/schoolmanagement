package com.guru.depend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.guru.depend.entity.Myuser;
import com.guru.depend.entity.User;
import com.guru.depend.repository.Userrepository;

@Service
public class Myuserdetailsservice implements UserDetailsService{

	@Autowired
	private Userrepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      User users=userrepository.findByUsername(username);
		if(users==null) {
			System.out.println("user not found");
		}
		return new Myuser(users);
	
	}

}
