package com.osi.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.bean.TaskBean;
import com.osi.dao.TaskDao;
import com.osi.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskDao taskDao;
	public List<TaskBean> getTaskList(){
		
		return taskDao.getTaskList();
		
	}
	
	public int saveTask(TaskBean taskBean){
		return taskDao.saveTask(taskBean);
	}

	@Override
	public int updateTask(TaskBean taskBean) {
		// TODO Auto-generated method stub
		return taskDao.updateTask(taskBean);
	}
}
