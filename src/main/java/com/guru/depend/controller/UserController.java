package com.guru.depend.controller;


import com.guru.depend.dto.ResponseDTO;
import com.guru.depend.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/teacher")
public class UserController {

    @GetMapping
    public ResponseEntity<ResponseDTO> userLogin(){
        ResponseDTO response = new ResponseDTO(
                Constants.ACTIVATED,
                HttpStatus.ACCEPTED.value(),
                "****Teacher logged In successfully****",null);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

}
