<%@page import="javax.swing.JOptionPane"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<style>
	* { font-family: 'Noto Sans KR', sans-serif; }
	div#container { width: 90%;  margin:0 auto;
				    overflow: hidden; 
				    position: relative;}
	h1 { text-align: center;}
	div#loginBox { width: 250px; height: 50px; position: absolute; right: 50px; bottom: 25px;}
	span#hello { display: block; width: 200px; height: 50px;  position:absolute; right: 80px; top: 70px; }
	a { text-decoration: none; color: #000;}
	nav { width: 100%; height: 50px; background: #711d19; }
	ul li { list-style: none; width: 25%; height: 50px; 
		    float: left; line-height: 50px;  text-align: center;}
	ul li a { text-decoration: none; color: #fff; }
	
	
</style>
<body>

	<%
		String auth = (String) session.getAttribute("Auth");
		if(auth != null){
			//out.println("<span id='hello'>[" + auth + "] 님 반갑습니다.</span>");
		}else{
			//JOptionPane.showMessageDialog(null, "로그인 실패");
		}
	%>
	<div id="container">
		<h1>대구 CGV</h1>
		<div id="loginBox">
			<a href="sessionLoginForm.jsp"><span id="login">로그인</span></a>
			<!-- <span id="hello">[test] 님 반갑습니다.</span> -->
		</div>
		<nav>
			<ul>
				<li><a href="main.do">HOME</a></li>
				<li><a href="list.do">상영영화</a></li>
				<li><a href="time.do">상영시간표</a></li>
				<li><a href="boardList.jsp">게시판</a></li>
			</ul>
		</nav>
		
	</div>
</body>
</html>