<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>問題回報</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script>
	$(function() {
		$('#header_nav ul li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
		})
		// $('#upTop').click(function(){
		// 	var body = $("html, body");
		// 	body.stop().animate({scrollTop:0}, 500, 'swing');
		// 	}).mouseover(function(){
		// 		$(this).removeClass('animated');
		// 	}).mouseout(function(){
		// 		$(this).addClass('animated');
		// 	})
	})
</script>
<style>
* {
	list-style: none;
	margin: 0;
	padding: 0;
}

body {
	/* background-color: rgb(145, 145, 145);
         */
	background: url(images/v6.jpg) no-repeat;
	background-size: cover;
	background-attachment: fixed;
}

#small_con {
	width: 100%;
	min-height: 800px;
	background-color: rgba(255, 255, 255, 0.363);
}

footer {
	margin-top: 20px;
}

footer ul {
	text-align: center;
	/* border: 2px solid red; */
	display: flex;
	width: 70%;
	margin: auto;
}

footer>ul>li {
	/* border: 2px solid green; */
	flex: 1;
}

footer>ul>li a {
	font-size: 1.2em;
}

footer>ul>li ul {
	display: flex;
	flex-direction: column;
	width: 80%;
	color: rgb(255, 153, 0);
}

.container p {
	color: white;
}
</style>
</head>
<body>
	
	<div class="container">
		<div id="small_con">
			<!--       寫在這 -->

			<form action="<c:url value="/Service.controller" />">
				<div id="keyword_search">
					<span class="form-group">問題名稱 :</span> <input type="text"
						name="title" placeholder="請輸入-標題" required="required"
						value="${param.title}">
					<td>${errors.title}</td>
				</div>
				<div class="AreaCon">
					<label>問題類型 :</label> <select name="classtype" id="classtype"
						required="required">
						<option value="">--請選擇--</option>
						<option value="1">會員(例如登入或密碼問題)</option>
						<option value="2">系統錯誤回報</option>
						<option value="3">建議</option>
						<option value="4">其他</option>
					</select>
					<td>${errors.classtype}</td>
				</div>
				<div class="Content" id="Content">
					<label>問題描述 :</label>
					<textarea rows="5" cols="105" placeholder="請輸入問題" name="content"
						required="required"></textarea><td>${errors.content}</td>
				</div>
				<div id="searchbarButton">
					<input type="submit" name="servicemethod" value="提交"
						class="btn btn-warning form-control "> <input type="reset"
						value="清除" class="btn btn-warning form-control">
				</div>
				<tr><span>${errors.LoginError} ~~~訊息顯示</span></tr>
			</form>
		</div>
	</div>

	
</body>
</html>