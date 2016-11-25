package com.osi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.osi.bean.LoginBean;
import com.osi.bean.Person;
import com.osi.bean.QualifierBean;
import com.osi.pojo.UserLogin;
import com.osi.service.UserLoginServiceInterface;
@Controller

public class TestController {
	
	
	@Autowired
	@Qualifier("qualifier2")
	private QualifierBean qualifier;
	
	
	// i was not define this bean in spring configuration file still it is autowiring 
	//for this i have configured <contex:component-scan base-package="com.osi.bean"/> 
	//and Difined @Component in Person class
	@Autowired
	private Person person;
	
	
	@Autowired
	private UserLoginServiceInterface userLoginServiceInterface;
	
	
	
	
	@RequestMapping("/hello")
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");

	      return "hello";
	   }
	// Request method 'POST' not supported (in case if form method is POST controller method is GET )
	@RequestMapping(value="/loginJson",method=RequestMethod.POST)
	@ResponseBody
	   public String loginJson(HttpServletRequest request,
	            HttpServletResponse response,ModelMap model,@ModelAttribute("loginBeansfds") LoginBean loginBean) {
		String response1=null;
		
		System.out.println("username "+request.getParameter("username"));
		System.out.println("password "+request.getParameter("password"));
		System.out.println("loginBean username "+loginBean.getUsername());
		System.out.println("loginBean password "+loginBean.getPassword());
	      model.addAttribute("message", "User LogedIn successfully..");
	      JSONObject jsonObject=null;
			//loginBean.raiseException();
			System.out.println("qualifier name "+qualifier.getQualifierName());
			//System.out.println("person name "+person.getPersoneName());
			
			UserLogin userLogin=userLoginServiceInterface.validateUser(loginBean);
			if(userLogin!=null){
				 jsonObject=new JSONObject();
				Map<Integer,String> modules=new HashMap<Integer,String>();
				List<String> list=new ArrayList<String>();
				list.add("list");
				list.add("alist");
				JSONArray jsonArray= new JSONArray(list);
				modules.put(1,"map");
				modules.put(2,"set");
					jsonObject.put("task", "success");
					jsonObject.put("modules", modules);
					jsonObject.put("list", jsonArray);
					jsonObject.put("message", "Column Mappings deleted successfully");
					response1=jsonObject.toString();
			}else{
				jsonObject=new JSONObject();
				jsonObject.put("task", "failure");
				response1=jsonObject.toString();
			}
				
	      return response1;
	   }
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	   public String login(HttpServletRequest request,
	            HttpServletResponse response,ModelMap model) {
		String response1=null;
		LoginBean loginBean=new LoginBean();
		loginBean.setUsername(request.getParameter("username"));
		loginBean.setPassword(request.getParameter("password"));
		System.out.println("username "+request.getParameter("username"));
		System.out.println("password "+request.getParameter("password"));
	      model.addAttribute("message", "User LogedIn successfully..");
			//loginBean.raiseException();
			//System.out.println("person name "+person.getPersoneName());
			
			UserLogin userLogin=userLoginServiceInterface.validateUser(loginBean);
			if(userLogin!=null){
				
				request.getSession().setAttribute("LOGGEDIN_USER", userLogin);
			}else{
				
			}
				
	      return "welcome";
	   }
	
	
	
	
	@RequestMapping(value="/loginDet",method=RequestMethod.POST)
	   public String loginDet(HttpSession  session,
	            HttpServletResponse response,ModelMap model) {
		UserLogin userLogin=(UserLogin) session.getAttribute("LOGGEDIN_USER");
		model.addAttribute("message","User Name : "+userLogin.getUserName());
		return "loginDet";
	}
	
	
	
	@RequestMapping("/regform")
	public String showRegForm(){
		
		return "registration";
	}
	
	
	@RequestMapping("/registerUser")
	public String registerUser(@ModelAttribute("loginBeansfds") LoginBean loginBean){
		String result=userLoginServiceInterface.saveUser(loginBean);
		System.out.println("result "+result);
		return null;
	}
}
