<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.userid }">
	<c:redirect url="../login.jsp" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<title>BlogMeJava</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/newcss.css" rel="stylesheet">
<style type="text/css">
body {
	font:12px Verdana,Arial;
}
</style>
<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div>
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="./index.jsp">Homepage</a></li>
			<li><a href="./getarticles">Articles</a></li>
			<li><a href="./users">Users</a></li>
			<li><a href="./settings">Settings</a></li>
			<li class="dropdown" style="float: right;">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
					<span class="glyphicon glyphicon-user"></span>
					<span style="margin-left: 5px;" class="caret"></span>
				</a>
				<ul class="dropdown-menu dropdown-menu-right" role="menu">
					<li>
						<a href="./myProfile?profileId=${sessionScope.userid}"> 
							<span style="margin-right: 5px;" class="glyphicon glyphicon-user"></span>User Profile
						</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="./logout">
						<span style="margin-right: 5px;" class="glyphicon glyphicon-log-out"></span>Log Out
						</a>
					</li>
				</ul>
		</ul>
	</div>
	<div class="panel panel-my-color">
		<div class="panel-body">
			<div class="row">				
					<div class="panel panel-default" style="margin-left: 15px; margin-right: 15px;">
						<!-- Content -->
						<div class="panel-heading">Dashboard</div>
						<div class="panel-body">
						<h3>Welcome</h3>
						<hr>
						<p>Congratulations You now have a fully functional instalations of CMS BlogMeJava 
							and you are almost ready to start building your blog</p>
						</div>
					</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
