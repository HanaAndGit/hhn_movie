<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	div#mainImg { width: 80%; margin: 0 auto;}
	img { width: 100%; margin-top: 30px; }
</style>
<body>
	<%@ include file="../include/movieHeader.jsp" %>
	
	<div id="mainImg">
		<img src="${pageContext.request.contextPath }/upload/cgvdaegu.jpg">
	</div>
	
	<%@ include file="../include/movieFooter.jsp" %>
</body>
</html>