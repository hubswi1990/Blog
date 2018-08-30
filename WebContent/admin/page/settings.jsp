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
.btn-group {
    visibility:hidden;
}
table tr:hover .btn-group {
    visibility:visible;
}
</style>
<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>

	$(".btn-group a").hover(function () {
    	$(this).addClass("btn");
	}, function () {
    	$(this).removeClass("btn");
	}
	);

	function showTableView() {
		$("#tablediv").show();
		$("#newUserDiv").hide();
		$("#editUserDiv").hide();
	}
	
	function showRegisterForm() {
		$("#newUserDiv").show();
		$("#tablediv").hide();
		$("#editUserDiv").hide();
	}
	
	function showEditUserForm() {
		$("#editUserDiv").show();
		$("#tablediv").hide();
		$("#newUserDiv").hide();
	}
</script>

</head>
<body>
<div class="container">
	<div>
		<ul class="nav nav-tabs" role="tablist">
			<li><a href="./index.jsp">Homepage</a></li>
			<li><a href="./getarticles">Articles</a></li>
			<li><a href="./users">Users</a></li>
			<li class="active"><a href="./settings">Settings</a></li>
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
				<div class="col-md-3 col-xs-4">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo">Settings </a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse in">
								<div class="panel-body">
									<ul class="nav nav-pills nav-stacked" role="tablist">
  										<li role="presentation"><a href="./settings">General</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-9 col-xs-8 smallpadding">
					<div class="panel panel-default">

						<!-- ./Include Content -->
						<div class="panel-heading"> General Settings</div>
						<div class="panel-body">
							<form class="form-horizontal" role="form" action="./setglobalsettings" method="post">
								<div class="form-group">
    								<label class="col-sm-2 control-label">Site Title</label>
    								<div class="col-sm-10">
      									<input class="form-control" name="sitetitle" id="disabledInput" type="text" value="${sitetitle}">
    								</div>
  								</div>
  								<div class="form-group">
    								<label class="col-sm-2 control-label">Tagline</label>
    								<div class="col-sm-10">
      									<input name="tagline" type="text" class="form-control" value="${tagline}">
     									<span style="margin-bottom: 0px;" class="help-block">In a few words, explain what this site is about.</span>
    								</div>
  								</div>
  								<div class="form-group">
    								<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary">Update Settings</button>
    								</div>
  								</div>
							</form>				
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
