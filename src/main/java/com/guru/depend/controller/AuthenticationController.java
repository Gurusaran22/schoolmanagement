package com.guru.depend.controller;

import com.guru.depend.dto.JwtAuthenticationResponse;
import com.guru.depend.dto.RefreshTokenRequest;
import com.guru.depend.dto.SignInRequest;
import com.guru.depend.dto.SignUpRequest;
import com.guru.depend.entity.User;
import com.guru.depend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")

public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

//    @PostMapping("/signup")
//    public ResponseEntity<User> signUp(@RequestBody final SignUpRequest signUpRequest) {
//        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
//    }

    @PostMapping("/admin-signup")
    public ResponseEntity<User> adminSignUp(@RequestBody final SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.adminSignUp(signUpRequest));
    }
    @PostMapping("/student-signup")
    public ResponseEntity<User> studentSignUp(@RequestBody final SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.studentSignUp(signUpRequest));
    }
    @PostMapping("/teacher-signup")
    public ResponseEntity<User> teacherSignUp(@RequestBody final SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.teacherSignUp(signUpRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final SignInRequest signInRequest) {
        if (!StringUtils.hasText(signInRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Enter email id");
        }
        if (!StringUtils.hasText(signInRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Enter Password");
        }
        try {
            return ResponseEntity.ok(authenticationService.signIn(signInRequest));
        } catch (IllegalArgumentException | UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody final RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @ExceptionHandler({ IllegalArgumentException.class, UsernameNotFoundException.class })
    public ResponseEntity<String> handleAuthenticationException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}