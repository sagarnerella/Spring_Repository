<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
<title>Spring MVC Tutorial by Crunchify - Hello World Spring MVC
	Example</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>${message}
 
	<br>
	<br>
	Logged<sec:authentication property="name"/><sec:authentication property="authorities"/>
	<br>
	<br>
	<a href="logout">Logged Out</a>
	
	<sec:authorize access="hasRole( 'Admin' )">
	<h1>This is Admin Part</h1>
	</sec:authorize>
	<form  name="loginform" id="loginform" action="loginDet" method="post">
<input type="submit" value="loginDet" id="login"></form>
	
</body>
</html>