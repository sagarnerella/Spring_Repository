package com.osi.dao;

import java.util.List;

import com.osi.bean.TaskBean;

public interface TaskDao {
	public List<TaskBean> getTaskList();
	public int saveTask(TaskBean taskBean);
	 public int updateTask(TaskBean taskBean);
}
