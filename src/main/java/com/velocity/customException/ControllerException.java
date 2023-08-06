package com.velocity.customException;

import org.springframework.stereotype.Component;

@Component
public class ControllerException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private int errorStatus;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(int errorStatus) {
		this.errorStatus = errorStatus;
	}
	
	public ControllerException(String errorMessage, int errorStatus) {
		super();
		this.errorMessage = errorMessage;
		this.errorStatus = errorStatus;
	}
	
	public ControllerException() {
		
	}
	
	
	

	
	
}
