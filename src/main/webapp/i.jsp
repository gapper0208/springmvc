<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>i.jsp</h3>
	
	<form action="upload.do" enctype="multipart/form-data" method="post">
		photo:<input type="file" name="photo" /><br />
		<button type="submit">提交</button>
	</form>
</body>
</html>