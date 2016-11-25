<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="springapp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AngularJS Task Manager</title>
<link href='./css/style.css' rel="stylesheet" type="text/css"></link>
<link href='./css/css/font-awesome.css' rel="stylesheet" type="text/css"></link>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="./css/ngDialog.css">
<link rel="stylesheet" href="./css/ngDialog-theme-default.css">


<script src="scripts/angular.js"></script>
<script src="scripts/angular-route.js"></script>
  <!-- <script data-require="angular-animate@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular-animate.js"></script> -->
  <script src="scripts/ngDialog.js"></script>
<script type="text/javascript" src="./app/app.js"></script>
<script type="text/javascript" src="./app/controllers/taskmanagerController.js"></script>
<script type="text/javascript" src="./app/services/taskmanagerService.js"></script>
</head>
<body>

<div ng-view ></div>
</body>
</html>