package com.osi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.bean.LoginBean;
import com.osi.dao.UserLoginDaoInterface;
import com.osi.pojo.UserLogin;

@Component
public class UserLoginServiceImpl implements UserLoginServiceInterface{
	
	@Autowired
	private UserLoginDaoInterface userLoginDaoInterface;
	public String saveUser(LoginBean loginBean){
		return userLoginDaoInterface.saveUser(loginBean);
	}
	
	public UserLogin validateUser(LoginBean loginBean){
		
		return userLoginDaoInterface.validateUser(loginBean);
	}

}
