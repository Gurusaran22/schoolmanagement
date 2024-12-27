package com.guru.depend.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.guru.depend.dto.MessageResponse;

@ControllerAdvice
	public class GlobalExceptionHandler {
	    @ExceptionHandler({UserIdNotFoundException.class})
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<MessageResponse> handleStudentNotFoundException(UserIdNotFoundException exception,WebRequest request) {
	    	MessageResponse messageresponse = new MessageResponse(
	    			HttpStatus.BAD_REQUEST.value(),
	    			new Date(),
	    			exception.getMessage(),
	    			request.getDescription(false));
	    	return new ResponseEntity<>(messageresponse,HttpStatus.BAD_REQUEST);  
	    }
	@ExceptionHandler(InvalidJwtException.class)
	public ResponseEntity<MessageResponse> handleInvalidJwtException(InvalidJwtException exception,WebRequest request) {
		MessageResponse MessageResponse = new MessageResponse(
				HttpStatus.FORBIDDEN.value(),
				new Date(),
				exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(MessageResponse, HttpStatus.FORBIDDEN);
	}
	}


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 