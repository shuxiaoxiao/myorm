package com.shuframework.myorm.exception;


public class MyException extends RuntimeException {

	private static final long serialVersionUID = 8001176636768388683L;

	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(Throwable cause) {
		super(cause);
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	/**
	 * 原方法是 同步方法，性能低
	 * 	public synchronized Throwable fillInStackTrace()
	 * 
	 */
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
	
}
