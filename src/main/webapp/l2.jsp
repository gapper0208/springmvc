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
	<h3>l2.jsp</h3>
	
	<form action="/springmvc/user/update.do" method="post">
		uid:<input name="uid"  value="${user.uid }"/>${uidError }<br />
		name:<input name="name" value="${user.name }" />${nameError }<br />
		birthday:<input name="birthday" value="<fmt:formatDate value="${user.birthday }" pattern="yyyy/MM/dd"/>" /><br />
		money:<input name="money" value="${user.money }"  /><br />
		cellphone:<input name="cellphone" value="${user.cellphone }"  />${cellphoneError }<br />
		<button type="submit">GO</button>
	</form>
	
	
	
	
</body>
</html>