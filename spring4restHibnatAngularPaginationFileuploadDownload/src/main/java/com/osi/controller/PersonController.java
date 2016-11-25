package com.osi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osi.bean.PersonBean;
import com.osi.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value="personlist",method = RequestMethod.GET,headers="Accept=application/json")
	public List<PersonBean> getPersonList(){
		List<PersonBean> listPersonBean=personService.getPersonList();
		return listPersonBean;
	}
		
	
	@RequestMapping(value="/person/save",method = RequestMethod.POST,headers="Accept=application/json")
	public void savePerson(@RequestBody PersonBean personBean){
		String result="fail";
		result=personService.savePerson(personBean);
		//return result;
	}
	
	@RequestMapping(value="/person/update",method=RequestMethod.POST,headers="Accept=application/json")
	public void updatePerson(@RequestBody PersonBean personBean){
		personService.updatePerson(personBean);
	}

}
