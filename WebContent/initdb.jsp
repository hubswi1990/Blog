<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MyBlog - just another blog site</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/blog-home.css" rel="stylesheet">
<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Setup database</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" action="./setupDatabase" method="post">
			<h4>Step 1: Create Admin</h4>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Admin Name</label>
					<div class="col-sm-10">
						<input name="adminName" type="text" class="form-control" id="inputPassword3" placeholder="Enter Admin Name">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input name="adminPassword" type="password" class="form-control" id="inputPassword3" placeholder="Enter Password">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input name="adminEmail" type="email" class="form-control" id="inputEmail3" placeholder="Enter Email">
					</div>
				</div>
				<h4>Step 2: General Settings</h4>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Site Title:</label>
					<div class="col-sm-10">
						<input name="siteTitle" type="text" class="form-control" id="inputPassword3" placeholder="Enter Site title">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Tagline</label>
					<div class="col-sm-10">
						<input name="tagline" type="text" class="form-control" id="inputPassword3" placeholder="Enter tagline">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">OK !</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>