<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>ServiceListPage</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
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

/* 清單CSS */
#css_table {
	display: table;
}

.css_tr {
	display: table-row;
}

.css_td {
	display: table-cell;
}
</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default" id="stickytop">
			<div class="navbar-header">
				<button type="button" class="
				   collapsed"
					data-toggle="collapse" data-target="#dropdown_munu" id="hum">
					<span> <i class="fa fa-bars" aria-hidden="true"></i>
					</span>
				</button>
				<div class="logo">
					<h1>
						<a class="navbar-brand" href="/wegether/index.jsp">Wegther</a>
					</h1>
				</div>
			</div>
			<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
				<nav class="header_nav" id="header_nav">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#"> <span data-hover="活動">活動</span>
						</a></li>
						<li><a href="#" class="scroll"> <span data-hover="心得">心得</span>
						</a></li>
						<li><a href="#" class="scroll"> <span data-hover="發起活動">發起活動</span>
						</a></li>
						<li><a href="#" class="scroll"> <span data-hover="發起心得">發起心得</span>
						</a></li>
						<li><a id="loginSpan" href="#" class="scrol"
							data-toggle="modal" data-target="#ActPageBox"> <span
								data-hover="登入">登入</span>
						</a> <a id="logoutSpan" href="#" class="scrol" style="display: none">
								<span data-hover="登出">登出</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</nav>
	</div>
	<div class="modal fade" id="ActPageBox" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<!--白色遮罩層-->
				<div class="modal-body">
					<!--       // modal-body  有差padding -->
					<div class="modal-header">

						<h5 class="modal-title lead">
							<strong>請選擇登入方式</strong>
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" id="mid-body">
						<form id="loginform">
							<p class="loginerror"></p>
							<div class="form-group" id="ACT">
								<label for="recipient-name" class="col-form-label">帳號:</label> <input
									type="text" class="form-control" id="account" name="account">
							</div>
							<div class="form-group" id="PWD">
								<label for="recipient-name" class="col-form-label">密碼:</label> <input
									type="password" class="form-control" id="pwd" name="pwd">
							</div>
							<button type="button" class="btn btn-primary" id="login">登入</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">取消</button>
						</form>
						<div id="or" class="bg-primary text-white">
							<h4>or</h4>
						</div>
						<button type="button" class="btn big_use Google_i">
							<i class="fa fa-google" aria-hidden="true"></i>Google 登入
						</button>
						<button type="button" class="btn big_use Fb_i">
							<i class="fa fa-facebook-official" aria-hidden="true"></i>FB 登入
						</button>
						<div class="AreaCon">
							<button type="button" class="btn small_use Google_i">
								<i class="fa fa-google" aria-hidden="true"></i>
							</button>
							<button type="button" class="btn small_use Fb_i">
								<i class="fa fa-facebook-official" aria-hidden="true"></i>
							</button>
						</div>
					</div>
					<div class="modal-footer">
						<p class="small text-left">
							還沒註冊嗎?趕緊註冊一個帳號吧! <a href="javascript:void(0)">點我註冊</a>
						</p>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="small_con">
			<!--       寫在這 -->

									<h3>Select Service Table Result : ${fn:length(select)} 筆資料</h3>
									<td>${errors.SelectResult}</td>
									<c:if test="${not empty select}">
										<table>
											<thead>
												<tr>
<!-- 													<th>提問時間</th> -->
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