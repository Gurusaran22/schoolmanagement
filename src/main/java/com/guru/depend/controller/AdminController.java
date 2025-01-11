package com.guru.depend.controller;

import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<ResponseDTO> adminLogin() {
        ResponseDTO response = new ResponseDTO(
                Constants.ACTIVATED,
                HttpStatus.ACCEPTED.value(),
                "****Admin logged In successfully****");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
       }
}