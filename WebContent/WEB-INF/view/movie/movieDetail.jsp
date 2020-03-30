<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	section { width: 60%; margin: 50px auto;}
	section div#img { width: 40%; height: 100%; float: left; text-align: center; }
	section div#desc { width: 60%; float: right; }
</style>
<body>
	<%@ include file="../include/movieHeader.jsp" %>
	<section>
		<div id="img"><img src="${pageContext.request.contextPath }/upload/${movie.file }"></div>
		<div id="desc">
			<h3>${movie.title }</h3>
			<p>줄거리</p>
			<p>${movie.content }</p>
		</div>
	</section>
	<%@ include file="../include/movieFooter.jsp" %>
</body>
</html>