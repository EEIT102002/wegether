<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--  <link rel="stylesheet" href=<c:url value="../css/login.css" />">-->
<title>Login</title>

	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
		
	</script>
	<!-- Meta tag Keywords -->
	<!-- css files -->
	<link rel="stylesheet" href="../css/login-style.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link rel="stylesheet" href="../css/fontawesome-all.css">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //css files -->
	<!-- web-fonts -->
	<link href="//fonts.googleapis.com/css?family=Nova+Round" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"></link>
	<!-- //web-fonts -->
</head>

<body>
	

			

	

	
	<a href="#" id="loginFB" class="fa fa-facebook icon icon-border facebook"> </a>
	<a href="#" id="loginGoogle" class="fa fa-google icon icon-border googleplus"> </a>

	<br />
	<form action="<c:url value="/loginfb.controller" />" method="post" id="form1">

		<div style="visibility: hidden">
			<input type="text" id="fgId" name="id"value="${param.id}"> 
			<input type="text" id="fgName" name="name"value="${param.name}"> 
			<input type="text" id="fgEmail"name="account" value="${param.account}">
			<input type="text" id="fgPic" name="user_photo" value="${param.user_photo}">
			<input type="submit" value="登入" id="click">

		</div>
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://apis.google.com/js/api:client.js"></script>

	<script>
		//Google登入
		var googleUser = {};
		var startApp = function() {
			gapi.load('auth2', function() {
				attachSignin(document.getElementById('loginGoogle'));
			});
		};
		function attachSignin(element) {
			auth2 = gapi.auth2
					.init({
						client_id : '694831066590-929gg592s3qr0cuh5uh7mt99jv1etiue.apps.googleusercontent.com', //客户端ID
						cookiepolicy : 'single_host_origin',
						scope : 'profile'
					});
			auth2.attachClickHandler(element, {}, function(googleUser) {
				var profile = auth2.currentUser.get().getBasicProfile();
				// 			console.log('ID: ' + profile.getId());
				// 			console.log('Full Name: ' + profile.getName());
				// 			console.log('Given Name: ' + profile.getGivenName());
				// 			console.log('Family Name: ' + profile.getFamilyName());
				// 			console.log('Image URL: ' + profile.getImageUrl());
				// 			console.log('Email: ' + profile.getEmail());
				document.getElementById("fgName").value = profile.getName();
				document.getElementById("fgEmail").value = profile.getEmail();
				document.getElementById("click").click();
			}, function(error) {
				console.log(JSON.stringify(error, undefined, 2));
			});
		}
		startApp();

		
		
		
		
		
		//FB初始化
		window.fbAsyncInit = function() {
			FB.init({
				appId : '252317735623075',
				cookie : true,
				xfbml : true,
				version : 'v3.1'
			});
			//記錄用戶行為資料，可在後台查看用戶資訊  
			FB.AppEvents.logPageView();
		};
		//嵌入臉書sdk
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/zh_TW/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		$(function() {
			$("#loginFB").click(function() { //點擊登入按鈕	      
			FB.getLoginStatus(function(response) { //檢查臉書登入狀態	        
			if (response.authResponse) { //如果已經有授權過應用程式	
			FB.api('/me',{fields : 'id,name,email,picture'},function(response) { //呼叫FB.api()取得使用者資料
			console.log("response.id:"+ response.id);
			console.log("response.name:"+ response.name);
			console.log("response.email:"+ response.email);
			//取得資料送至controller比對
			document.getElementById("fgId").value = response.id;
			document.getElementById("fgName").value = response.name;
			document.getElementById("fgEmail").value = response.email;
			document.getElementById("fgPic").value = response.picture;
			document.getElementById("click").click();});

			} else { //沒授權過應用程式	          
				FB.login(function(response) { //呼叫FB.login()請求使用者授權
					if (response.authResponse) {
						FB.api('/me',{fields : 'id,name,email,picture'},
							function(response) {
							document.getElementById("fgId").value = response.id;
							document.getElementById("fgName").value = response.name;
							document.getElementById("fgEmail").value = response.email;
							document.getElementById("fgPic").value = response.picture;
							document.getElementById("click").click();
							});
						}
					},
						{scope : 'email'});
											}
										});
							});
		});
	</script>

</body>
</html>