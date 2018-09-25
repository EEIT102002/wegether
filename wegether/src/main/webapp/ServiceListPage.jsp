<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>ServiceListPage</title>
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

									<h3>Select Service Table Result : ${fn:length(select)} 筆資料</h3>
									<td>${errors.SelectResult}</td>
									<c:if test="${not empty select}">
										<table align="center">
											<thead>
												<tr>
<!-- 												<th>提問時間</th> -->
													<th>會員ID</th>
													<th>標題</th>
													<th>類型</th>
													<th>內容描述</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="obj" items="${select}">
													<c:url value="/ServiceEditPage.jsp" var="link" scope="page">
														<c:param name="memberid" value="${obj.memberid}" />
														<c:param name="id" value="${obj.id}" />
														<c:param name="asktime" value="${obj.asktime}" />
														<c:param name="title" value="${obj.title}" />
														<c:param name="classtype" value="${obj.classtype}" />
														<c:param name="content" value="${obj.content}" />
													</c:url>
													<tr>
														<div>
															<span>
<%-- 																<td>${obj.asktime}</td> --%>
																<td><a href="${link}">${obj.memberid}</td>
																<td><a href="${link}">${obj.title}</td>
															</span> <span>
																<td><c:if test="${obj.classtype==1}">會員(例如登入或密碼問題)</c:if>
																	<c:if test="${obj.classtype==2}">系統錯誤回報</c:if> <c:if
			 															test="${obj.classtype==3}">建議</c:if> <c:if  
																		test="${obj.classtype==4}">其他</c:if></td>  
															</span>
																<td>${errors.SelectError}</td>
														</div>
														<td>${obj.content}</td>
														<td><input type="hidden" name="id" value="${param.id}"></input></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:if>




			<h3>
				<a href="<c:url value="/Service.jsp" />">Service 回報區</a>
			</h3>

		</div>
	</div>

		<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>
</html>