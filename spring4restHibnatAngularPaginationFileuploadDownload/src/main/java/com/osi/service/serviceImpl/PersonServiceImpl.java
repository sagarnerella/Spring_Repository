package com.osi.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.bean.PersonBean;
import com.osi.dao.PersonDao;
import com.osi.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	PersonDao personDao;
	public String savePerson(PersonBean person){
		String result=personDao.savePerson(person);
		return result;
	}
	
	public List<PersonBean> getPersonList(){
		return personDao.getPersonList();
		
	}
	
	public void updatePerson(PersonBean person){
		personDao.updatePerson(person);
	}
	
}
