<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<title>test</title>
<%
String name= request.getParameter("name");
String city = request.getParameter("city");
out.println(name+":"+city);
out.println(name+":"+city);

System.out.println(request.getParameter("city"));   //1
System.out.println(request.getParameter("name"));  //aaa

%>
</head>
<body>
<p>${param.test}</p>

</body>
</html>
