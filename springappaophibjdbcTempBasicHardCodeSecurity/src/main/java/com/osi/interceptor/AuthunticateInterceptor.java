package com.osi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.osi.pojo.UserLogin;

public class AuthunticateInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion ");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle ");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		if(!request.getRequestURI().equals("/springapp/login") && !request.getRequestURI().equals("/springapp/login/")){
		UserLogin userLogin=(UserLogin) request.getSession().getAttribute("LOGGEDIN_USER");
		if(userLogin==null){
			System.out.println("session is not available");
			response.sendRedirect("/springapp/");
			
	    return false;
		}
		}
	    System.out.println("session is available ");
		return true;
	}

}
