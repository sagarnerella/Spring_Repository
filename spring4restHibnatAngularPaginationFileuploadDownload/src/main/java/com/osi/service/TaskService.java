package com.osi.service;

import java.util.List;

import com.osi.bean.TaskBean;

public interface TaskService {
	
	
	public List<TaskBean> getTaskList();
    public int saveTask(TaskBean taskBean);
    public int updateTask(TaskBean taskBean);
}
