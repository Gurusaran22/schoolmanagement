package com.guru.depend.exception;


public class UserIdNotFoundException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public UserIdNotFoundException(String m) {
		super(m);
	}
}
