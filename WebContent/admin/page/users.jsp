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
			<li class="active"><a href="./users">Users</a></li>
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
				<div class="col-md-3 col-xs-4">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo"> Users </a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse in">
								<div class="panel-body">
									<ul class="nav nav-pills nav-stacked" role="tablist">
  										<li role="presentation"><a href="./users">All Users</a></li>
  										<li role="presentation"><a href="./addUser">Add New</a></li>
  										<li role="presentation"><a href="./myProfile?profileId=${sessionScope.userid}">Your Profile</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-9 col-xs-8 smallpadding">
					<div class="panel panel-default">

						<!-- ./Include Content -->
						<div class="panel-heading"> Manage: Users
							<a href="./addUser">
							<button style="float: right;" type="button" class="btn btn-default btn-xs">
								<span class="glyphicon glyphicon-plus"></span> Add New
							</button>
							</a>
						</div>
						<div class="panel-body">

						<% if (request.getParameter("pp") != null) {
							String pp = request.getParameter("pp"); %>
							<jsp:include page='<%="users/" + pp + ".jsp"%>' />
						<% } else { %>
							<!-- ./Include Table Content -->
							<div id="tablediv">
								<div class="row">
									<div style="margin-left: 15px; margin-right: 15px; margin-bottom: 0;">
										<table class="table table-hover">
											<thead>
												<tr>
													<th>Username</th>
													<th>Email</th>
													<th>Register Date</th>
													<th>Role</th>
													<th>Status</th>												
												</tr>
											</thead>
											<tbody>
												<c:forEach var="user" items="${usersList}">
													<tr>
														<td>${user.profileName}
														<div class="btn-group " style="display: inherit;">	
															<a class=" btn-mini" href="./myProfile?profileId=${user.profileId}">Edit</a>
															<c:if test="${user.profileId != sessionScope.userid }">
																<a class=" btn-mini" href="./removeUser?id=${user.profileId}"> | Delete</a>	
															</c:if>														
			 											</div>
														</td>
														<td>${user.profileEmail}</td>
														<td>${user.profileRegistered}</td>
														<td>${user.profileGroup}</td>
														<td>
															<c:choose>
																<c:when test="${user.profileStatus == false}">
																	<a href="./changeStatusUser?id=${user.profileId}"><span class="glyphicon glyphicon-remove-circle"></span></a>
																	<div class="btn-group " style="display: inherit;">	
																		<a class=" btn-mini" href="./changeStatusUser?id=${user.profileId}">Enabled</a>
			 														</div>
																</c:when>
																<c:otherwise>
																	<a href="./changeStatusUser?id=${user.profileId}"><span class="glyphicon glyphicon-ok-circle"></span></a>
																	<div class="btn-group " style="display: inherit;">	
																		<a class=" btn-mini" href="./changeStatusUser?id=${user.profileId}">Disabled</a>
			 														</div>
																</c:otherwise>
															</c:choose>
														</td>													
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div style="float: right; margin-top: 0; margin-right: 15px;">
										<ul class="pagination" style="margin: 0;">
											<c:if test="${currentPage != 1}">
												<li><a href="./users?page=${currentPage - 1}">Previous</a></li>
											</c:if>
											<c:forEach begin="1" end="${noOfPages}" var="i">
												<c:choose>
													<c:when test="${currentPage eq i}">
														<li class="active"><a href="#">${i}</a></li>
													</c:when>
													<c:otherwise>
														<li><a href="./users?page=${i}">${i}</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											<c:if test="${currentPage lt noOfPages}">
												<li><a href="./users?page=${currentPage + 1}">Next</a></li>
											</c:if>
										</ul>
									</div>
								</div>
							</div>
						<% } %>						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
