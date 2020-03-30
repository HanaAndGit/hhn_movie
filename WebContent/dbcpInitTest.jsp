<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection conn = null;
		try{
			
		String jdbcDriver = "jdbc:apache:commons:dbcp:hhn_movie";
		conn = DriverManager.getConnection(jdbcDriver);
		if(conn != null){
			out.println("커넥션 풀에 연결 되었습니다.");
		}else{
			out.println("연결 실패 했습니다.");
		}
	}finally{
		if(conn != null){
			conn.close();
		}
	}
		
	%>
</body>
</html>