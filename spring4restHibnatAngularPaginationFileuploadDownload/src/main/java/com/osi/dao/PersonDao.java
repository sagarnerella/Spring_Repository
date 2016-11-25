package com.osi.dao;

import java.util.List;

import com.osi.bean.PersonBean;

public interface PersonDao {
	public String savePerson(PersonBean person);
	public List<PersonBean> getPersonList();
	public void updatePerson(PersonBean person);
}
