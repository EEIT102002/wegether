<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TestPage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/activityPage.css">
<script src="/wegether/js/activityPage.js"></script>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css"	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
<!-- 登入使用 -->
<script src="/wegether/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/wegether/js/noticeWebStocket.js" type="text/javascript"></script>
<script src="/wegether/js/logMethod.js" type="text/javascript"></script>
<!-- 登入使用 END -->
<!-- 留言 /心得心享 視窗  -->
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- 留言 /心得心享 視窗 END -->
<!-- applyForm -->
<script src="/wegether/js/applyForm.js" type="text/javascript"></script>
<link rel="stylesheet" href="/wegether/css/applyForm.css">
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
</body>
</html>
