var taskManagerModule = angular.module('springapp', ['ngRoute','ngDialog','ngTable', 'ngSocial', 'ngSanitize']);
taskManagerModule.config(['$routeProvider','ngDialogProvider',function($routeProvider,ngDialogProvider){
	
	
	
	$routeProvider.when('/', {
                        templateUrl: 'app/views/fileupload.html',
                        controller: 'fileUploadController',
                        controllerAs: 'vm'
                    }).when('/pagination', {
                        templateUrl: 'app/views/overview.html',
                        controller: 'introController',
                        controllerAs: 'vm'
                    }).
					when('/task',{
				controller:'taskManagerController',
				templateUrl:'app/views/task.html'
				}).
				otherwise({redirectTo:'/'});
	
	 ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
                plain: false,
                showClose: true,
                closeByDocument: true,
                closeByEscape: true,
                appendTo: false,
                preCloseCallback: function () {
                    console.log('default pre-close callback');
                }
            });
	}]);
