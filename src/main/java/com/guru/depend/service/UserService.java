package com.guru.depend.service;


import com.guru.depend.entity.Students;
import com.guru.depend.entity.Teachers;
import com.guru.depend.entity.User;
import com.guru.depend.repository.StudentsRepository;
import com.guru.depend.repository.TeachersRepository;
import com.guru.depend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found !!!"));
    }

}
