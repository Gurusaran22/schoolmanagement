package com.guru.depend.exception;


import java.io.Serial;

public class UserIdNotFoundException  extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;
	public UserIdNotFoundException(String m) {
		super(m);
	}
}
