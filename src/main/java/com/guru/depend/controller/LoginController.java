package com.guru.depend.controller;

import com.guru.depend.dto.*;
import com.guru.depend.entity.User;
import com.guru.depend.exception.InvalidJwtException;
import com.guru.depend.service.LoginService;
import com.guru.depend.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")

public class LoginController {

    @Autowired
    LoginService authenticationService;

    @PostMapping("/admin-signup")
    public ResponseEntity<ResponseDTO> adminSignUp(@RequestBody final SignUpRequest signUpRequest) {
        ResponseDTO response = new ResponseDTO(
                Constants.CREATED,
                HttpStatus.CREATED.value(),"admin created successfully",
                authenticationService.adminSignUp(signUpRequest));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PostMapping("/student-signup")
    public ResponseEntity<ResponseDTO> studentSignUp(@RequestBody final SignUpRequest signUpRequest) {
        ResponseDTO response = new ResponseDTO(
                Constants.CREATED,
                HttpStatus.CREATED.value(),"student created successfully",
                authenticationService.studentSignUp(signUpRequest));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PostMapping("/teacher-signup")
    public ResponseEntity<ResponseDTO> teacherSignUp(@RequestBody final SignUpRequest signUpRequest) {
        ResponseDTO response = new ResponseDTO(
                Constants.CREATED,
                HttpStatus.CREATED.value(),"teacher created successfully",
                authenticationService.teacherSignUp(signUpRequest));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

 @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody final SignInRequest signInRequest) throws InvalidJwtException {
        JwtAuthenticationResponse jwtResponse = authenticationService. signIn(signInRequest);
        ResponseDTO response = new ResponseDTO(
                Constants.CREATED,
                HttpStatus.CREATED.value(),"token generated successfully",
                jwtResponse);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //        if (!StringUtils.hasText(signInRequest.getEmail())) {
//            ResponseDTO response = new ResponseDTO(
//                    Constants.NOT_FOUND,
//                    HttpStatus.NOT_FOUND.value(),
//                    "Enter email Id");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//        if (!StringUtils.hasText(signInRequest.getPassword())) {
//            ResponseDTO response = new ResponseDTO(
//                    Constants.NOT_FOUND,
//                    HttpStatus.NOT_FOUND.value(),
//                    "Enter Password");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
    @PostMapping("/refresh")
    public ResponseEntity<ResponseDTO> refresh(@RequestBody final RefreshTokenRequest refreshTokenRequest) {
        ResponseDTO response = new ResponseDTO(
                Constants.ACTIVATED,
                HttpStatus.CREATED.value(),"refresh token generated successfully",
                authenticationService.refreshToken(refreshTokenRequest));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}