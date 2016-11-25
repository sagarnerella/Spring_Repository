package com.osi.dao.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.osi.bean.TaskBean;
import com.osi.dao.TaskDao;


@Repository
public class TaskDaoImpl implements TaskDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<TaskBean> getTaskList() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria crteri=session.createCriteria(TaskBean.class);
		List<TaskBean> listTaskBean=crteri.list();
		return listTaskBean;
	}


	@Override
	public int saveTask(TaskBean taskBean) {
		// TODO Auto-generated method stub
		int taskId=0;
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(taskBean);
		session.beginTransaction().commit();
		taskId=1;
		return taskId;
	}


	@Override
	public int updateTask(TaskBean taskBean) {
		// TODO Auto-generated method stub
		int taskId=0;
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(taskBean);
		session.beginTransaction().commit();
		taskId=1;
		return taskId;
	}

}
