package com.osi.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.bean.LoginBean;
import com.osi.pojo.UserLogin;
import org.springframework.jdbc.core.JdbcTemplate;
@Component
public class UserLoginDaoImpl implements UserLoginDaoInterface{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserLogin validateUser(LoginBean loginBean){
		Session session=sessionFactory.openSession();
		String sql=" from UserLogin u where u.userName=:name and u.password=:pass";
		Query query = session.createQuery(sql);
		query.setParameter("name", loginBean.getUsername());
		query.setParameter("pass", loginBean.getPassword());
		UserLogin  userLogin=(UserLogin) query.uniqueResult();
		session.close();
		return userLogin;
	}
	
	
	public String saveUser(LoginBean loginBean) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("dao username "+loginBean.getUsername());
		System.out.println("dao password "+loginBean.getPassword());
		System.out.println("session "+session);
		UserLogin userLogin=new UserLogin();
		userLogin.setUserName(loginBean.getUsername());
		userLogin.setPassword(loginBean.getPassword());
		session.save(userLogin);
		session.getTransaction().commit();
		session.close();
		return "success";
	}

}
