    var app=angular.module('pagination-demo', ['ui.bootstrap','ngRoute','app.controllers']);
    app.config(['$routeProvider',function($routeProvider){
	$routeProvider.when('/',{
	controller:'PaginationDemoCtrl',
	templateUrl:'./app/views/paginationex.html'
	}).
	otherwise({redirectTo:'/'});
	
	
	}]);
   
	