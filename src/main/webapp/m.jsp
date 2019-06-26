<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>m.jsp</h3>

	要想有成就： 一门深入 长时熏修。 
	
	<form action="users" method="post">
		<button type="submit">post</button>
	</form>
	
	<form action="users" method="get">
		<button type="submit">get</button>
	</form>
	
	<form action="users" method="post">
		<input type="hidden" name="_method" value="put" />
		<button type="submit">put</button>
	</form>
	
	<form action="users" method="post">
		<input type="hidden" name="_method" value="delete" />
		<button type="submit">delete</button>
	</form>
	
</body>
</html>