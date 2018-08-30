<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.userid }">
	<c:redirect url="../../login.jsp" />
</c:if>

<form role="form" action="./addUser" method="post">
	<div class="form-group">
		<label>Username</label> 
		<input name="username" type="text" class="form-control" placeholder="Enter Username">
	</div>
	<div class="form-group">
		<label>Email address</label> 
		<input name="email" type="email" class="form-control" placeholder="Enter email">
	</div>
	<div class="form-group">
		<label>Password</label> 
		<input name="password" type="password" class="form-control" placeholder="Password">
	</div>
	<div class="form-group">
		<label>Role</label> 
		<select name="role" class="form-control">
		<c:forEach var="role" items="${groupsList}">
  			<option value="${role.groupsId}">${role.nameGroup}</option>
  		</c:forEach>
		</select>
	</div>
	<div>
		<button type="submit" class="btn btn-primary">Add New User</button>
		<a href="./users"><button type="button" class="btn btn-default">Cancel</button></a>
	</div>
	`
</form>
