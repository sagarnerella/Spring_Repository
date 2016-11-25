package com.osi.dao.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.osi.bean.PersonBean;
import com.osi.bean.TaskBean;
import com.osi.dao.PersonDao;
import com.osi.pojo.PersonPojo;

@Repository
public class PersonDaoImpl implements PersonDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
       public List<PersonBean> getPersonList(){
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria crteri=session.createCriteria(PersonPojo.class);
		ProjectionList projectionList=Projections.projectionList().add(Projections.property("personId")).add(Projections.property("firstName")).add(Projections.property("lastName"));
		crteri.setProjection(projectionList);
		List<PersonBean> listPersonBean=new ArrayList<PersonBean>();
		List<Object[]> listArr=crteri.list();
		 for(Object[] arr : listArr){
			 PersonBean personBean=new PersonBean();
			 personBean.setPersonId((Integer)arr[0]);
				personBean.setFirstName((String)arr[1]);
				personBean.setLastName((String)arr[2]);
				listPersonBean.add(personBean);
			}
		//List<PersonPojo> listPersonPojo=crteri.list();
		session.beginTransaction().commit();
		//List<PersonBean> listPersonBean=convertIntoPojoList(listPersonPojo);
		return listPersonBean;
	}
	
	public String savePerson(PersonBean person){
		String result="fail";
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		PersonPojo personPojo=convertIntoPojo(person);
		session.save(personPojo);
		result="success";
		session.beginTransaction().commit();
				return result;
	}
	
	public void updatePerson(PersonBean person){
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		PersonPojo personPojo=convertIntoPojo(person);
		session.update(personPojo);
		session.beginTransaction().commit();
	}
	
	
	public PersonPojo convertIntoPojo(PersonBean personBean){
		PersonPojo personPojo=new PersonPojo();
		if(personBean.getPersonId()!=null)
			personPojo.setPersonId(personBean.getPersonId());
		personPojo.setFirstName(personBean.getFirstName());
		personPojo.setLastName(personBean.getLastName());
		personPojo.setEmail(personBean.getEmail());
		return personPojo;
	}
	public List<PersonBean> convertIntoPojoList(List<PersonPojo> personPojo){
		List<PersonBean> listPersonBean=new ArrayList<PersonBean>();
		for(PersonPojo personPojoObj:personPojo){
		
		PersonBean personBean=new PersonBean();
		personBean.setFirstName(personPojoObj.getFirstName());
		personBean.setLastName(personPojoObj.getLastName());
		personBean.setEmail(personPojoObj.getEmail());
		listPersonBean.add(personBean);
		}
		return listPersonBean;
	}
}
