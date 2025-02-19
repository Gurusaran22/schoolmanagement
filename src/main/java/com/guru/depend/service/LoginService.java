package com.guru.depend.service;

import com.guru.depend.dto.*;
import com.guru.depend.entity.User;
import com.guru.depend.exception.InvalidJwtException;
import com.guru.depend.repository.UserRepository;
import com.guru.depend.enums.Role;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;


@Service

public class LoginService {

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
        return userRepository.save(student);
    }

    public User adminSignUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
    public User teacherSignUp(SignUpRequest signUpRequest) {
        User teacher = new User();
        teacher.setEmail(signUpRequest.getEmail());
        teacher.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        teacher.setRole(Role.TUTOR);
        return userRepository.save(teacher);
    }
    public  JwtAuthenticationResponse signIn(SignInRequest signInRequest) throws InvalidJwtException {
        if (!StringUtils.hasText(signInRequest.getEmail())) {
            throw new InvalidJwtException("email not found");
        }
        if (!StringUtils.hasText(signInRequest.getPassword())) {
            throw new InvalidJwtException("password not found");
        }

        User user = this.userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Incorrect email "));

        boolean isPasswordValid = passwordEncoder.matches(signInRequest.getPassword(), user.getPassword());
        if (!isPasswordValid) {
            throw new BadCredentialsException("Incorrect password");
        }
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
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
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}

// boolean isValid = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())) != null;
//
//        if (!isValid) {
//            throw new BadCredentialsException("Incorrect email or password");
//        }
//     try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Incorrect email or password");
//        }