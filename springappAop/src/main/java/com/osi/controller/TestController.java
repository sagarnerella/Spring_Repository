package com.osi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osi.bean.LoginBean;
import com.osi.bean.Person;
import com.osi.bean.QualifierBean;
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
	
	@RequestMapping("/hello")
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");

	      return "hello";
	   }
	// Request method 'POST' not supported (in case if form method is POST controller method is GET )
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	   public String login(HttpServletRequest request,
	            HttpServletResponse response,ModelMap model,@ModelAttribute("loginBeansfds") LoginBean loginBean) {
		String response1=null;
		Map<Integer,String> modules=new HashMap<Integer,String>();
		List<String> list=new ArrayList<String>();
		list.add("list");
		list.add("alist");
		JSONArray jsonArray= new JSONArray(list);
		modules.put(1,"map");
		modules.put(2,"set");
		System.out.println("username "+request.getParameter("username"));
		System.out.println("password "+request.getParameter("password"));
		System.out.println("loginBean username "+loginBean.getUsername());
		System.out.println("loginBean password "+loginBean.getPassword());
	      model.addAttribute("message", "User LogedIn successfully..");
	      JSONObject jsonObject=null;
	      jsonObject=new JSONObject();
			jsonObject.put("task", "success");
			jsonObject.put("modules", modules);
			jsonObject.put("list", jsonArray);
			jsonObject.put("message", "Column Mappings deleted successfully");
			response1=jsonObject.toString();
			//loginBean.raiseException();
			System.out.println("qualifier name "+qualifier.getQualifierName());
			//System.out.println("person name "+person.getPersoneName());
	      return response1;
	   }
}
