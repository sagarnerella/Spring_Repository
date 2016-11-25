package com.osi.controller;

import java.text.ParseException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osi.dao.TaskManagerService;
import com.osi.service.TaskService;
import com.osi.bean.Task;
import com.osi.bean.TaskBean;


@RestController
public class TaskManagerController {
	
	
	@Autowired
	TaskService taskService;
	TaskManagerService taskmanagerservice=new TaskManagerService();
	
	 @RequestMapping(value="/tasks",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<TaskBean> getAllTasks() {	 
	  //List<Task> tasks=taskmanagerservice.getAllTasks();
		 List<TaskBean> tasks= taskService.getTaskList();
	  return tasks;
	
	 }
	 
	 
	 @RequestMapping(value="/tasks/archive/{taskIds}",method = RequestMethod.POST,headers="Accept=application/json")
	 public void archiveAllTasks(@PathVariable int[] taskIds) {	
		 for(int i=0;i<taskIds.length;i++){
			 taskmanagerservice.archiveTask(taskIds[i]);	
			 
		 }
	  //List<Task> tasks=taskmanagerservice.getAllTasks();
	  //return tasks;
	
	 }
	 
	 @RequestMapping(value="/tasks/changeTaskStatus",method = RequestMethod.POST,headers="Accept=application/json")
	 public void changeTaskStatus(@RequestBody TaskBean taskUpdate) throws ParseException {	
		 //taskmanagerservice.changeTaskStatus(taskId,taskStatus);
		 taskService.updateTask(taskUpdate);
		 //return taskService.getTaskList();
		 
	 }
	 
	 @RequestMapping(value="/tasks/insert/{taskName}/{taskDesc}/{taskPriority}/{taskStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public void addTask(@PathVariable String taskName,@PathVariable String taskDesc,@PathVariable String taskPriority,@PathVariable String taskStatus) throws ParseException {	
		 TaskBean taskBean = new TaskBean();
			taskBean.setTaskName(taskName);
			taskBean.setTaskDescription(taskDesc);
			taskBean.setTaskPriority(taskPriority);
			taskBean.setTaskStatus(taskStatus);
			taskService.saveTask(taskBean);
		//return taskService.getTaskList();
		 
	 }
	 
	 @RequestMapping(value="/tasks/updateRecord",method = RequestMethod.POST,headers="Accept=application/json")
	 public void updateRecord(@RequestBody TaskBean taskUpdate){
		 taskService.updateTask(taskUpdate);
		 //return taskService.getTaskList();
	 }
	 
	 
	 @RequestMapping(value="/tasks/update",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<TaskBean> updateTask(@RequestParam("taskName") String taskName,@RequestParam("taskDescription") String taskDescription,@RequestParam("taskPriority") String taskPriority,@RequestParam("taskStatus") String taskStatus,@RequestParam("taskId") int taskId){
		 TaskBean task = new TaskBean();
			task.setTaskName(taskName);
			task.setTaskDescription(taskDescription);
			task.setTaskPriority(taskPriority);
			task.setTaskStatus(taskStatus);
			task.setTaskId(taskId);
			//taskmanagerservice.updateTask(task);
		 return taskService.getTaskList();
	 }
}