package com.osi.dao;

import java.util.List;

import com.osi.bean.LoginBean;
import com.osi.pojo.UserLogin;

public interface UserLoginDaoInterface {
	public String saveUser(LoginBean loginBean);
	public UserLogin validateUser(LoginBean loginBean);
}
