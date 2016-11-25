package com.osi.service;

import java.util.List;

import com.osi.bean.PersonBean;

public interface PersonService {

	
	public String savePerson(PersonBean person);
	public List<PersonBean> getPersonList();
	public void updatePerson(PersonBean person);
}
