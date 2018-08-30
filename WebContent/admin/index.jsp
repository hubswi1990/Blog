<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.userid }">
	<c:redirect url="./login.jsp" />
</c:if>

<% if (request.getParameter("p") != null) {
	String p = request.getParameter("p"); %>
	<jsp:include page='<%="page/" + p + ".jsp"%>' />
<% } else { %>
	<jsp:include page='<%="page/homepage.jsp"%>' />
<% } %>