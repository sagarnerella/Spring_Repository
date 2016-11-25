taskManagerModule.controller('introController', function($scope,NgTableParams,paginationFactory,ngDialog,$rootScope){
	            var self = this;
				paginationFactory.getPersonList().success(function(response) {
	   		 var data=response;
			 self.tableParams = new NgTableParams({ count: 5}, { counts: [5, 10, 25], dataset: data});
	   	    });
				
				
				
				$rootScope.$on("getPersonList", function(event){
           $scope.getPersonList();
          });

          $scope.getPersonList = function() {
            // task
			 paginationFactory.getPersonList().success(function(data) {
	   		 data=data;
			 self.tableParams = new NgTableParams({ count: 5}, { counts: [5, 10, 25], dataset: data});
	   	 });
          }
		  
		  $rootScope.$on("perfmReqdOperation", function(event,PersonDet,method){
           $scope.perfmReqdOperation(PersonDet,method);
          });
		  
		  $scope.perfmReqdOperation=function(PersonDet,method){
		  
		  paginationFactory.savePerson(PersonDet,method).success(function(response) {				
	            });
		  }
		  
		  
		  
		  
		  /*
            var data = [{name: "Moroni", age: 50},
                {name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27},
				{name: "Simon", age: 43},
                {name: "Jacob", age: 27},
                {name: "Nephi", age: 29},
                {name: "Christian", age: 34},
                {name: "Tiancum", age: 43},
                {name: "Jacob", age: 27}
            ];
			self.tableParams = new NgTableParams({ count: 5}, { counts: [5, 10, 25], dataset: data});
			*/
			
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
			
			
			
			
			$scope.addPerson=function() {
			var new_dialog =ngDialog.open({ id: 'addPersonTemplate', template: 'addPersonTemplate',controller:'addPersonCtrl' });
			}
			
			$scope.editPersonInfo=function(UserDetials){
			paginationFactory.setEditPerson(UserDetials);
			var new_dialog =ngDialog.open({ id: 'addPersonTemplate', template: 'addPersonTemplate',controller:'editPersonCtrl' });
			
			}
        });
		taskManagerModule.controller('editPersonCtrl', function ($scope, ngDialog,$rootScope,paginationFactory) {
		
		$scope.personalDetails=paginationFactory.getEditPerson();
		
		$scope.addPersonDet=function(PersonDet){
		
		//$rootScope.$emit("perfmReqdOperation", personalDet,"update");
		
		paginationFactory.savePerson(PersonDet,"update").success(function(response) {
		 alert("User Updated Successfully");
          $scope.closeThisDialog();
				$rootScope.$emit("getPersonList", {});		
	            });
				
				
				
		}
		
		});
		
		taskManagerModule.controller('addPersonCtrl', function ($scope, ngDialog,$rootScope,paginationFactory) {
		$scope.addPersonDet=function(personalDetails){
		//$rootScope.$emit("perfmReqdOperation", personalDetails,"save");
		
		
		paginationFactory.savePerson(personalDetails,"save").success(function(response) {	
		alert("User Saved Successfully");
          $scope.closeThisDialog();
		  $rootScope.$emit("getPersonList", {});		
	            });
				
		}
		
		});