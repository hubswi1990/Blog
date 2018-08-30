<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty article }">
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

<body>

<!-- Page Content -->
<div class="container">
	<div class="row">
		<!-- Blog Post Content Column -->
        <div class="col-lg-8">
			<!-- Blog Post -->
			<!-- Title -->
            <h1>${article.postTitle}</h1>
			<hr>
			<!-- Date/Time -->
            <p><span class="glyphicon glyphicon-time"></span> Posted on ${article.postCreateAt}</p>
			<hr>
			<!-- Post Content -->
            <p>${article.postContent}</p>
			<a class="btn btn-primary" href="./getarticle?page=${page}"><span class="glyphicon glyphicon-chevron-left"> Back</span></a>
			<hr>
			</div>
        </div>
        <!-- /.row -->
        <hr>
    </div>
    <!-- /.container -->
</body>
</html>