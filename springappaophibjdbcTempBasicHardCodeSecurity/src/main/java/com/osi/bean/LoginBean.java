package com.osi.bean;

import org.springframework.beans.factory.annotation.Required;

public class LoginBean {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	@Required
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void raiseException(){
		
		System.out.println("Exception raised");
	       throw new IllegalArgumentException();
	}
}
