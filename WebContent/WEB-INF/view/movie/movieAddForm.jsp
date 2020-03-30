<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	fieldset { width: 600px; margin: 30px auto;}
	label { width: 150px; float: left; }
</style>
<body>
	<%@ include file="../include/movieHeader.jsp" %>
	<form action="add.do" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>영화 정보</legend>
			<p>
				<label>제목 </label>
				<input type="text" name="title" placeholder="영화 이름">
			</p>
			<p>
				<label>상세 설명</label>
				<textarea rows="10" cols="50" name="content" placeholder="영화 상세 이름"></textarea>
			</p>	
			<p>
				<label>파일 </label>
				<input type="file" name="file">
			</p>  
			<p>
				<label>상영 시간</label>
				<input type="text" name="time">
			</p>
			<p>
				<input type="submit" value="전송">
			</p>
		</fieldset>
		
	</form>
	<%@ include file="../include/movieFooter.jsp" %>
</body>
</html>