<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="ServiceSelect.controller" class="btn btn-warning form-control">select</a>
	<br>
	<a href="Service.jsp">Service 回報區</a>
	<br>

	<form action="<c:url value="/Trackmember.insert" />" method="GET">
	<input type="text" name="Memberid">Memberid</input>
	<input type="submit" name="insert" value="加追蹤">
	</form><br>
	
	<form action="<c:url value="/Trackmember.delete" />" method="POST">
	<input type="text" name="Memberid">Memberid</input>
	<input type="submit" name="delete" value="取消追蹤">
	</form>

	<!-- <a href="Trackmember.controller" class="btn btn-warning form-control">selecttrack</a><br> -->
</body>
</html>