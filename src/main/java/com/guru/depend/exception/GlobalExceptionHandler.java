package com.guru.depend.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.guru.depend.dto.Messageresponse;

@ControllerAdvice
	public class Globalexceptionhandler {
	    @ExceptionHandler({Useridnotfoundexception.class})
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<Messageresponse> handleStudentNotFoundException(Useridnotfoundexception exception,WebRequest request) {
	    	Messageresponse messageresponse = new Messageresponse(
	    			HttpStatus.BAD_REQUEST.value(),
	    			new Date(),
	    			exception.getMessage(),
	    			request.getDescription(false));
	    	return new ResponseEntity<>(messageresponse,HttpStatus.BAD_REQUEST);  
	    }
	}


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 