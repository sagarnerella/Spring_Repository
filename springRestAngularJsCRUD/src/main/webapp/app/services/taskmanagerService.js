taskManagerModule.factory('taskmanagerFactory',function($http){    
    
 
	var taskObj=[];
return {
setEditObject:function(task){
taskObj=task;
},
getEditTask:function(){
return taskObj;
},
	getTaskList: function(){
		return $http.get("http://localhost:8080/springapp/tasks").
	success(function(data, status, headers, config) {
        console.log("getTaskList", data);
    });
	
	},	
	
saveTask: function(taskName,taskDesc,taskPriority,taskStatus){
return $http.post('http://localhost:8080/springapp/tasks/insert/' +taskName+'/'+taskDesc+'/'+taskPriority+'/'+taskStatus).
success(function(data, status, headers, config) {
    console.log("saveTask", data);
});

},
	
	updateTask: function(taskName,taskDesc,taskPriority,taskStatus,taskId){
		return $http.post('http://localhost:8080/springapp/tasks/update?taskName='+taskName+'&taskDescription='+taskDesc+'&taskPriority='+taskPriority+'&taskStatus='+taskStatus+'&taskId='+taskId).
	  success(function(data, status, headers, config) {
		    console.log("updateTask", data);
	  });
	
	},
	updateRecord: function(task){
		console.log(task.hiddenTaskId);
		console.log(task.taskName);
		 return $http({

	            method: 'POST',
	            url: 'http://localhost:8080/springapp/tasks/updateRecord',
	            data: {
	            	taskId:task.hiddenTaskId,taskName:task.taskName,taskDescription:task.taskDescription,taskPriority:task.taskPriority,taskStatus:task.taskStatus
	            },
	            headers: {'Content-Type':'application/json'}
	        }).
	  success(function(data, status, headers, config) {
		    console.log("updateRecord", data);
	  });
	
	},
	
	updateTaskStatus: function(task){
		return $http({method: 'POST',
				url:'http://localhost:8080/springapp/tasks/changeTaskStatus' ,
				data: {
        	taskId:task.taskId,taskName:task.taskName,taskDescription:task.taskDescription,taskPriority:task.taskPriority,taskStatus:task.taskStatus
        },
        headers: {'Content-Type':'application/json'}
        
	}).
	  success(function(data, status, headers, config) {
		    console.log("updateTask", data);
	  });
	},
clearSelectTasks: function(selection){
	return $http.post('http://localhost:8080/springapp/tasks/archive/' +selection).
success(function(data, status, headers, config) {
    console.log("clearSelectTasks", data);
});
}

	



}
});