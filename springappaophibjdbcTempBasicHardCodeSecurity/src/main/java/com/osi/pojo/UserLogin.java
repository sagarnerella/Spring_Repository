package com.osi.pojo;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;

@SuppressWarnings("serial")
public class UserLogin implements Serializable{
	public UserLogin(){}
	private Integer userId;
	private String userName;
	private String password;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
