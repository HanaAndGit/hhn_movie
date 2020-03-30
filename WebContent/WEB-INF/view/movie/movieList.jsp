<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	a#add { width:200px; height: 50px;display: block; text-decoration: none; 
		    color: #000; line-height: 50px;  position: absolute; right:100px; }
 	section#movieBox { width: 80%; min-height: 800px;  margin:0 auto; }
	div.box { width: 190px; float: left; margin-top: 50px; margin-left:90px; }
	div.box img { width: 100%; }
	div.box p { text-align: center;} 
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(function(){
		$(".box").click(function(){
			var i = $(this).children("input").val();
			window.location.href = "detail.do?no="+i;
		})
	})
</script>
<body>
	<%@ include file="../include/movieHeader.jsp" %>
	<a href="add.do" id="add">상영영화 추가하기</a>
	 <section id="movieBox">
	 
	 	 <c:forEach var="movie" items="${list }">
	 		<div class="box">
	 			<input type="hidden" value="${movie.no }">
	 			<img src="${pageContext.request.contextPath }/upload/${movie.file }">
	 			<p>${movie.title }</p>
	 		</div>
	 	</c:forEach> 
	 	
	</section> 
	<%@ include file="../include/movieFooter.jsp" %>
</body>
</html>