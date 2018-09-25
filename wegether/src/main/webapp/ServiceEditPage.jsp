<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>ServiceEdit</title>
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
					<span class="form-group">問題名稱 :</span><input type="text"
						name="title" placeholder="請輸入-標題" required="required"
						value="${param.title}">

					<%-- 					<td>${errors.title}</td> <input type="text" name="memberid" --%>
					<%-- 						placeholder="memberid" value="${param.memberid}"> --%>

					<!-- 						<input type="text" name="id" -->
					<%-- 						type="hidden" value="${param.id}"> --%>

					<input type="hidden" name="id" value="${param.id}"></input> <input
						type="hidden" name="asktime" value="${param.asktime}"></input>

					<td>${errors.memberid}</td> <br>

				</div>
				<div class="AreaCon">
					<label>問題類型 :</label> <select name="classtype" id="classtype"
						required="required">
						<%-- 						<c:if test="${param.classtype==0}"><option value="0">--請選擇--</option> </c:if> --%>
						<%-- 						<c:if test="${param.classtype==1}"><option value="1">會員(例如登入或密碼問題)</option> </c:if> --%>
						<%-- 						<c:if test="${param.classtype==2}"><option value="2">系統錯誤回報</option> </c:if> --%>
						<%-- 						<c:if test="${param.classtype==3}"><option value="3">建議</option> </c:if> --%>
						<%-- 						<c:if test="${param.classtype==4}"><option value="4">其他</option> </c:if> --%>
						<option value="0">--請選擇--</option>
						<option value="1">會員(例如登入或密碼問題)</option>
						<option value="2">系統錯誤回報</option>
						<option value="3">建議</option>
						<option value="4">其他</option>
					</select>
					<td>${errors.classtype}</td>
				</div>
				<div class="Content" id="Content">
					<span class="form-group">問題描述 :</span>
					<td>${errors.Content}</td>
					<textarea rows="5" cols="70" placeholder="請輸入問題" name="content"
						required="required">${param.content}</textarea>
				</div>
				<div id="searchbarButton">
					<input type="submit" name="servicemethod" value="更新"
						class="btn btn-warning form-control ">
					<button class="btn btn-warning form-control "
						onclick="{ if(confirm('確定取消?'))location.href='<c:url value="/ServiceSelect.controller" />'}">取消</button>
				</div>
				<tr>
					<td>${errors.action}</td>
				</tr>
			</form>
			<a href="ServiceSelect.controller"
				class="btn btn-warning form-control ">回到清單</a>

		</div>
	</div>

	<!-- <footer>
		<div class="container">
			<p id="fw">Wegther 2018</p>
		</div>
	</footer> -->
	<!-- <i  id="upTop" class="fa fa-chevron-circle-up animated infinite bounce" aria-hidden="true"></i> -->
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>
</html>