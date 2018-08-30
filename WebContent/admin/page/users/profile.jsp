<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.userid }">
	<c:redirect url="../../login.jsp" />
</c:if>

<form class="form-horizontal" role="form" action="./myProfile?profileId=${profile.profileId}" method="post">
  <div class="form-group">
    <label class="col-sm-2 control-label">Username</label>
    <div class="col-sm-10">
      <input class="form-control" id="disabledInput" type="text" placeholder="${profile.profileName}" disabled>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input name="email" type="email" class="form-control" value="${profile.profileEmail}">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">New Password</label>
    <div class="col-sm-10">
      <input name="newpasswd" type="password" class="form-control">
      <span style="margin-bottom: 0px;" class="help-block">If you would like to change the password type a new one. Otherwise leave this blank.</span>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">New Password</label>
    <div class="col-sm-10">
      <input name="repasswd" type="password" class="form-control">
      <span class="help-block">Type your new password again. </span>
    </div>
  </div>  
  <div class="form-group">
		<label class="col-sm-2 control-label">Role</label>
		<div class="col-sm-10">
		<c:choose>
			<c:when test="${profile.profileGroup == 'administrator'}">
				<select name="role" class="form-control">
				<option value="${profile.profileGroup}">${profile.profileGroup}</option>
				<option value="" disabled>--------------------</option>
				<c:forEach var="role" items="${groupsList}">
  					<option value="${role.nameGroup}">${role.nameGroup}</option>
  				</c:forEach>
  				</select>
			</c:when>
			<c:otherwise>
				<select name="role" class="form-control" disabled>
					<option value="${profile.profileGroup}">${profile.profileGroup}</option>
				</select>
			</c:otherwise>
		</c:choose>
		</div>
</div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
		<button type="submit" class="btn btn-primary">Update Profile</button>
		<a href="./users"><button type="button" class="btn btn-default">Cancel</button></a>
    </div>
  </div>
</form>