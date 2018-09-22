<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>問題回報</title>
<!-- 1 -->
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
<!-- 1 -->
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
</head>
<body>
		<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
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
<!-- 	1 -->
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
<!-- 	1 -->
	
</body>
</html>