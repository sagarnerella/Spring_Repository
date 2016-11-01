package com.osi.service;

import java.util.List;

import com.osi.bean.LoginBean;
import com.osi.pojo.UserLogin;

public interface UserLoginServiceInterface {

	public String saveUser(LoginBean loginBean);
	public UserLogin validateUser(LoginBean loginBean);
}
