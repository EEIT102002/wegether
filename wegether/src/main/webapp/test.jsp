<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<title>test</title>
<script>
	$(function(){
		var x = $("#inputid").val();
// 	 	var x = $("#spanid").val();
		console.log("#inputid:"+x);
	})

</script>
</head>
<body>
<p>${param.test}</p>

<button id="memBut" type="button" value="123">456</button>
<input id="inputid" type="text" value="555" style="display:none;"/>
<div id="divid" class="divclass" value="7777"></div>
<span id="spanid"value="88"></span>
</body>
</html>
