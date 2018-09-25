<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<script type="text/javascript" src="/wegether/js/personalLogin.js"></script>
<link rel="stylesheet" href="/wegether/css/Non-home.css">


<script>

	//初始化
	window.fbAsyncInit = function() {
		FB.init({
				appId      : '2175596062729540',
				cookie     : true,  // enable cookies to allow the server to access 
                    // the session
				xfbml      : true,  // parse social plugins on this page
				version    : 'v3.1' // use graph api version 2.8
				});
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
		
			$("#loginFB").click(function(){
				FB.getLoginStatus(function(response) { //檢查臉書登入狀態	        
					if (response.authResponse) { //如果已經有授權過應用程式	
					FB.api('/me',{fields : 'id,name,email,picture'},function(response) { //呼叫FB.api()取得使用者資料
					Fbid = response.id;
					console.log("response.id:"+ response.id);
					Fbname = response.name;
					console.log("response.name:"+ response.name);
					Fbemail = response.email;
					console.log("response.email:"+ response.email);
					//取得資料送至controller比對
			 		returnback();
//			 		document.getElementById("fgName").value = response.name;
//			 		document.getElementById("fgEmail").value = response.email;
//			 		document.getElementById("fgPic").value = response.picture;
// 					document.getElementById("click").click();
					});

					}else { 	
						//沒授權過應用程式	          
						FB.login(function(response) { 
							//呼叫FB.login()請求使用者授權
							if (response.authResponse) {
								FB.api('/me',{fields : 'id,name,email'},
									function(response) {
									Fbid = response.id;
									console.log("response.id:"+ response.id);
									Fbname = response.name;
									console.log("response.name:"+ response.name);
									Fbemail = response.email;
									console.log("response.email:"+ response.email);
//			 						document.getElementById("fgName").value = response.name;
//			 						document.getElementById("fgEmail").value = response.email;
//			 						document.getElementById("fgPic").value = response.picture;
//			 						document.getElementById("click").click();
									//取得資料送至controller比對
			 						returnback();
									});
								}
							},
								{scope : 'email'});
													}
												});				
		  								});
// 		系統登入function


		  	function returnback(){
		  		console.log("responseout.email:"+ Fbemail);
				$.get("FbloginCheck.controller/"+Fbid+"/"+Fbemail+"/"+Fbname ,
						  function(data){
							fblogin();
				 },'json');	
				
		  	}
		  	
		  	function fblogin(){

		        $.post(
		            "/wegether/login.do"
		            , {account:Fbemail ,pwd:"EA123456"}
		            , function (data) {
		                if (data.state == true) {
		                    var qqq = $(logindiv).closest('.modal');
		                    $(qqq).modal('hide');
		                    logingroup(data);
		                  
		                }else{
		                	$('.loginerror').text("登入失敗");
		                	$('#pwd').val("");
		                }
		                
		            }
		            , "json"
		        )
		  		
		  	}


// 		--------
		
	})


	
</script>

<style>

</style>
</head>
<body>

	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
	<div class="container">
		<div id="small_con">
			<!--       寫在這 -->

							
			<a href="#" id="loginFB">Facebook登入</a>	

					
			<!-- core end -->

		</div>
	</div>
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
	
</body>
<script>
	
</script>
</html>