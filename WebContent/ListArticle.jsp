<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty articlesList}">
	<c:redirect url="./getarticle" />
</c:if>

<!DOCTYPE html>
<html lang="en">

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

<!-- Page Content -->
<div class="container">

<div class="row">

	<!-- Blog Entries Column -->
	<div class="col-md-8">

		<h1 class="page-header">
			${sitetitle} <small>${tagline}</small>
		</h1>

		<c:forEach var="article" items="${articlesList}">
			<h2><a href="./readPosts?id=${article.postId}&page=${currentPage}">${article.postTitle}</a></h2>
			<p>
				<span class="glyphicon glyphicon-time"></span> 
				Posted on ${article.postCreateAt}
			</p>
			
			<hr>
			<p>${article.postContent}</p>
			<a class="btn btn-primary" href="./readPosts?id=${article.postId}&page=${currentPage}">Read More
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>
			<hr>
		</c:forEach>

		<!-- Pager -->
		<ul class="pager">
			<li class="previous">
			<c:choose>
				<c:when test="${currentPage == 1}">
					<a href="#">&larr; Older</a>
				</c:when>
				<c:otherwise>
					<a href="./getarticle?page=${currentPage - 1}">&larr; Newer</a>
				</c:otherwise>
			</c:choose></li>

			<li class="next">
			<c:choose>
				<c:when test="${currentPage == noOfPages}">
					<a href="#">Newer &rarr;</a>
				</c:when>
				<c:otherwise>
	 				<a href="./getarticle?page=${currentPage + 1}">Older &rarr;</a>
				</c:otherwise>
			</c:choose>
			</li>
		</ul>
	</div>

	<!-- Blog Sidebar Widgets Column -->
	<div class="col-md-4">

		<!-- Side Widget Well -->
		<div class="well">
			<h4>Recent Posts</h4>
			<ul class="nav nav-pills nav-stacked">
				<c:forEach var="i" items="${recentPosts}">
					<li><a href="./readPosts?id=${i.postId}&page=${currentPage}">${i.postTitle}</a></li>
				</c:forEach>
			</ul>		
		</div>
	</div>
</div>
<!-- /.row -->

<hr>
</div>

<!-- /.container -->
</body>
</html>
