<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	section { min-height: 500px; }
	table { width: 60%;  margin:0 auto;
		    border-collapse: collapse; }
	th, td { border: 1px solid #000; height:50px;}
</style>
<body>
	<%@ include file="../include/movieHeader.jsp" %>
		
	<section>
		<h1>상영 시간표</h1>
		<table>
			<c:forEach var="movie" items="${list }">
				<tr>
					<th>${movie.title }</th>
					<td>${movie.time }</td>
				</tr>
			</c:forEach>
			<%-- <%
				ArrayList<Movie> list =  movieMng.getList();
				for(int i=0; i<list.size(); i++){
					Movie movie = list.get(i);
					out.println("<tr><th>" + movie.getTitle() + "</th>");
					out.println("<td>" + movie.getTime() + "</td></tr>");
				}
			%> --%><!-- 
			<tr>
				<th>부산행</th>
				<td>14:30, 17:00</td>
			</tr> -->
		</table>
	</section>
		
	<%@ include file="../include/movieFooter.jsp" %>
</body>
</html>