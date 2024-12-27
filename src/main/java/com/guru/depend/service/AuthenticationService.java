package com.guru.depend.service;

import com.guru.depend.dto.*;
import com.guru.depend.entity.User;
import com.guru.depend.repository.UserRepository;
import com.guru.depend.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service

public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;



    public User studentSignUp(SignUpRequest signUpRequest) {

        User student = new User();
        student.setEmail(signUpRequest.getEmail());
        student.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        student.setRole(Role.STUDENT);
        return this.userRepository.save(student);

    }

    public User adminSignUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ADMIN);
        return this.userRepository.save(user);
    }
    public User teacherSignUp(SignUpRequest signUpRequest) {
        User teacher = new User();
        teacher.setEmail(signUpRequest.getEmail());
        teacher.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        teacher.setRole(Role.TUTOR);
        return this.userRepository.save(teacher);
    }
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Incorrect email or password");
        }

        User user = this.userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + signInRequest.getEmail()));

        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setStatus("***Token created successfully***");
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = this.jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setStatus("***RefreshToken created successfully***");
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }
}

