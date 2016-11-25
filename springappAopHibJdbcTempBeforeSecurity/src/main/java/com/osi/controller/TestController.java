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
import org.springframework.context.annotation.Lazy;
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
	public TestController(){
		System.out.println("test controller ");
	}
	
	   /*1) The @Component annotation marks a java class as a bean so the component-scanning 
	    
	    mechanism of spring can pick it up and pull it into the application context. To use this annotation, apply it over class as below:

		2) Although above use of @Component is good enough but you can use more suitable annotation 
		that provides additional benefits specifically for DAOs 
		i.e. @Repository annotation. The @Repository annotation is a specialization of the @Component annotation
		 with similar use and functionality. In addition to importing the DAOs into the DI container, 
		 it also makes the unchecked exceptions (thrown from DAO methods) 
		 eligible for translation into Spring DataAccessException.

		3) The @Service annotation is also a specialization of the component annotation. 
		It doesn’t currently provide any additional behavior over the @Component annotation,
		 but it’s a good idea to use @Service over @Component in service-layer classes 
		 because it specifies intent better. Additionally, tool support and additional behavior might 
		 rely on it in the future.

		4) @Controller annotation marks a class as a Spring Web MVC controller. It too is a @Component 
		specialization, so beans marked with it are automatically imported into the DI container.
		 When you add the @Controller annotation to a class, you can use another annotation i.e. 
		 @RequestMapping; to map URLs to instance methods of a class.
		 
		 
		 
		 
		 <c:forEach var="country" items="${capitalList}">
    Country: ${country.key}  - Capital: ${country.value}
</c:forEach>
	
	*/
	
	
	@Autowired
	@Qualifier("qualifier2")
	private QualifierBean qualifier;
	
	
	// i was not define this bean in spring configuration file still it is autowiring 
	//for this i have configured <contex:component-scan base-package="com.osi.bean"/> 
	//and Difined @Component in Person class
	@Autowired@Lazy // using Lazy initialization annotation Person bean will be initialized while we use Person bean
	private Person person;
	
	
	@Autowired	//@Lazy  here am not using Lazy annotation so UserLoginServiceInterface will initialized at the time deployment means when spring configuration file is loaded
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
			//userLoginServiceInterface.validateUser(loginBean);
			if(userLogin!=null){
				
				request.getSession().setAttribute("LOGGEDIN_USER", userLogin);
			}else{
				
			}
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
	@Autowired//@Lazy
	private UserLoginServiceInterface userLoginServiceInter;
	
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
			/*userLoginServiceInterface.validateUser(loginBean);
			userLoginServiceInter.validateUser(loginBean);
			userLoginServiceInter.validateUser(loginBean);*/
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
