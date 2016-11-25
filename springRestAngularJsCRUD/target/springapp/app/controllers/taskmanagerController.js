taskManagerModule.controller('taskManagerController', function ($scope,$rootScope,$http,taskmanagerFactory,ngDialog) {
	
	console.log("am in controller ");
	$scope.toggle=true;
	$scope.edittoggle=true;
	$scope.selection = [];
	
	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	
	//get all tasks and display initially
	
	        $scope.tasks = taskmanagerFactory.getTaskList().success(function(data) {
	   		 $scope.tasks=data;
	   	 });
	   	/*$scope.tasks=[{"taskId":1,"taskName":"test","taskDescription":"test desc","taskPriority":"LOW","taskStatus":"ACTIVE"
,"taskStartTime":null,"taskEndTime":null,"taskArchived":0}];*/
		 
		 //<!-- this code used to show dailog box-->
		 
	        $scope.openAddTaskForm = function() {
			console.log("openAddTaskForm method");
                var new_dialog = ngDialog.open({ id: 'addTaskTemplate', template: 'addTaskTemplate',controller:'addTaskCtrl' });
                // example on checking whether created `new_dialog` is open
                /*$timeout(function() {
                    console.log(ngDialog.isOpen(new_dialog.id));
                }, 2000)*/
				
            };
			var task=[];
			$scope.openEditTaskForm = function(taskdet) {
			
			taskmanagerFactory.setEditObject(taskdet);
			var new_dialog = ngDialog.open({ id: 'editTaskTemplate', template: 'editTaskTemplate',controller:'editTaskCtrl' });
			}
			// call method like below from another controller
			 //$rootScope.$emit("loadAllTasks", {});
			//<!-- code to call one controller method from another controller method-->
          $rootScope.$on("loadAllTasks", function(){
           $scope.loadAllTasks();
          });

          $scope.loadAllTasks = function() {
            // task
			$scope.tasks = taskmanagerFactory.getTaskList().success(function(data) {
	   		 $scope.tasks=data;
	   	 });
          }
          //<!-- end of code to call one controller method from another controller method-->
		  
		  
            $rootScope.$on('ngDialog.opened', function (e, $dialog) {
                console.log('ngDialog opened: ' + $dialog.attr('id'));
            });
			$rootScope.$on('ngDialog.closed', function (e, $dialog) {
                console.log('ngDialog closed: ' + $dialog.attr('id'));
            });

            $rootScope.$on('ngDialog.closing', function (e, $dialog) {
                console.log('ngDialog closing: ' + $dialog.attr('id'));
            });

            $rootScope.$on('ngDialog.templateLoading', function (e, template) {
                console.log('ngDialog template is loading: ' + template);
            });

            $rootScope.$on('ngDialog.templateLoaded', function (e, template) {
                console.log('ngDialog template loaded: ' + template);
            });
			
			
	        
	        
	//add a new task
	
		
	// toggle selection for a given task by task id
	  $scope.toggleSelection = function toggleSelection(task) {
		  alert("task "+task);
        var taskMessage="";
	    if (task.taskStatus=='COMPLETED') {
	    	task.taskStatus='ACTIVE';
	    	taskMessage="Task unmarked";
	    }else{
	    	task.taskStatus='COMPLETED';
	    	taskMessage="Task marked completed";
	    }
	    
	    
	    
	    taskmanagerFactory.updateTaskStatus(task).success(function(data) {
	    	
	    	$scope.tasks = data;
	    	alert(taskMessage);
	    	if (task.taskId > -1) {
	    		$scope.selection.splice(task.taskId, 1);
	    	}else
	  	    // is newly selected
	  	   
	    		$scope.selection.push(taskId);
	    });
	    // is currently selected
	    
	  };
	  
	
	// Archive Completed Tasks
	  $scope.archiveTasks = function archiveTasks() {
		  var selection=$scope.selection;
		  taskmanagerFactory.clearSelectTasks(selection).success(function(data) {
			  $scope.tasks = data;
			     alert("Successfully Archived");
		  });
	  };
	
	  
	  
});

//Angularjs Directive for confirm dialog box
taskManagerModule.directive('ngConfirmClick', [
	function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);
 taskManagerModule.controller('addTaskCtrl', function ($scope, ngDialog,taskmanagerFactory,$rootScope) {
 console.log("inside controller");
 
 
 
             $scope.taskList = [
      { id: 'ACTIVE', name: 'ACTIVE' },
      { id: 'COMPLETED', name: 'COMPLETED' },
     
      ];
    // Pre-select task by object
    $scope.taskStatus = { id: 'ACTIVE', name: 'ACTIVE' };
	
	
	
	 $scope.priorities = [
      { id: 'HIGH', name: 'HIGH' },
      { id: 'LOW', name: 'LOW' },
     { id: 'MEDIUM', name: 'MEDIUM' }
      ];
        
$scope.taskPriority = { id: 'LOW', name: 'LOW' };	


$scope.addTask = function addTask() {
		
		console.log("am in add function ");
		
		if($scope.taskName=="" || $scope.taskDesc=="" || $scope.taskPriority == "" || $scope.taskStatus == ""){
			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
		}
		else{
			taskmanagerFactory.saveTask($scope.taskName,$scope.taskDescription,$scope.taskPriority.name,$scope.taskStatus.name)
			.success(function(data) {
			  alert("task added successfully");
				 $scope.closeThisDialog();
				  $rootScope.$emit("loadAllTasks", {});
				 
		   	 });
		 
		}
		
		
		
		
	};	
        });
		
		taskManagerModule.controller('editTaskCtrl', function ($scope, ngDialog,taskmanagerFactory,$rootScope) {
 console.log("editTaskCtrl controller");
  
 var task=taskmanagerFactory.getEditTask();
			 console.log("edit task sdfdsffa "+task.taskName);
	    	  $scope.taskName=task.taskName;
				 $scope.taskDescription=task.taskDescription;
				 
				 
	            $scope.hiddenTaskId=task.taskId;
				
				
				$scope.taskList = [
      { id: 'ACTIVE', name: 'ACTIVE' },
      { id: 'COMPLETED', name: 'COMPLETED' },
     
      ];
    // Pre-select task by object
    $scope.taskStatus = { id: task.taskStatus, name: task.taskStatus };
	
	
	
	 $scope.priorities = [
      { id: 'HIGH', name: 'HIGH' },
      { id: 'LOW', name: 'LOW' },
     { id: 'MEDIUM', name: 'MEDIUM' }
      ];
	  $scope.taskPriority = { id: task.taskPriority, name: task.taskPriority };	
	  
	  
	  $scope.updateTask = function() {
	        	
	        	console.log("in update function "+$scope.taskName);

				var editTask=[];
				editTask={taskName:$scope.taskName,taskDescription:$scope.taskDescription,taskPriority:$scope.taskPriority.name,taskStatus:$scope.taskStatus.name,hiddenTaskId:$scope.hiddenTaskId};
	        	//taskmanagerFactory.updateTask($scope.updateTask.taskName,$scope.updateTask.taskDescription,$scope.updateTask.taskPriority,$scope.updateTask.taskStatus,$scope.updateTask.hiddenTaskId)
	        	taskmanagerFactory.updateRecord(editTask)
	        	.success(function(data) {
			   		alert("Task updateTask");
					$scope.closeThisDialog();
				  $rootScope.$emit("loadAllTasks", {});
			   	 });
	        	
	        }
 });