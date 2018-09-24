<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<script type="text/javascript" src="/wegether/js/personalLogin.js"></script>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
</head>
<body>
	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
	<div class="container">
		<div id="small_con">
	<script>
  // This is called with the results from from FB.getLoginStatus().
//   function statusChangeCallback(response) {
//     console.log('statusChangeCallback');
//     console.log(response);
//     // The response object is returned with a status field that lets the
//     // app know the current login status of the person.
//     // Full docs on the response object can be found in the documentation
//     // for FB.getLoginStatus().
//     if (response.status === 'connected') {
//       // Logged into your app and Facebook.
//       //登入後要執行的function
//       returnMemberData();
//       testAPI();
//     } else {
//       // The person is not logged into your app or we are unable to tell.
//       document.getElementById('status').innerHTML = 'Please log ' +
//         'into this app.';
//     }
//   }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  //登錄完後呼叫 FB.getLoginStatus() 來取得最新的登入狀態
//   function checkLoginState() {
//     FB.getLoginStatus(function(response) {
//       statusChangeCallback(response);
//     });
//   }
	//初始化
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '2175596062729540',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v3.1' // use graph api version 2.8
    });

    // Now that we've initialized the JavaScript SDK, we call 
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.
    
	//呼叫 FB.getLoginStatus() 來取得最新的登入狀態
//     FB.getLoginStatus(function(response) {
    	
//       statusChangeCallback(response);//呼叫第10行印出response內容

//     });

  };

  // Load the SDK asynchronously
  //非同步載入Facebook JavaScript SDK
//   (function(d, s, id) {
//     var js, fjs = d.getElementsByTagName(s)[0];
//     if (d.getElementById(id)) return;
//     js = d.createElement(s); js.id = id;
//     js.src = "https://connect.facebook.net/en_US/sdk.js";
//     fjs.parentNode.insertBefore(js, fjs);
//   }(document, 'script', 'facebook-jssdk'));
  
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

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  //測試程式
//   function testAPI() {
//     console.log('Welcome!  Fetching your information.... ');
//     FB.api('/me', function(response) {
//       console.log('Successful login for: ' + response.name);
//       document.getElementById('status').innerHTML =
//         'Thanks for logging in, ' + response.name + '!';
//     });
//   }
  
  
  
  
  
  
  
  
  var Fbid,Fbname,Fbemail;
  $(function() {
		$("#loginFB").click(function() { //點擊登入按鈕	 
			alert('onclick');
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
// 		document.getElementById("fgName").value = response.name;
// 		document.getElementById("fgEmail").value = response.email;
// 		document.getElementById("fgPic").value = response.picture;
// 		document.getElementById("click").click();});

		}else { 							//沒授權過應用程式	          
			FB.login(function(response) { //呼叫FB.login()請求使用者授權
				if (response.authResponse) {
					FB.api('/me',{fields : 'id,name,email'},
						function(response) {
						Fbid = response.id;
						console.log("response.id:"+ response.id);
						Fbname = response.name;
						console.log("response.name:"+ response.name);
						Fbemail = response.email;
						console.log("response.email:"+ response.email);
// 						document.getElementById("fgName").value = response.name;
// 						document.getElementById("fgEmail").value = response.email;
// 						document.getElementById("fgPic").value = response.picture;
// 						document.getElementById("click").click();
						//取得資料送至controller比對
						returnback();
						});
					}
				},
					{scope : 'email'});
										}
									});
						});
	});
  
  
  
  
  
  
  
  
  
  
  
  
  
//   var Fbid,Fbname,Fbemail;
//   var Fbpicture;
//   var Fbbirthday;
  //把資料送到後端比對
//   function returnMemberData(){	
// 		FB.api('/me',{fields : 'id,name,email,picture,birthday'},function(response) { //呼叫FB.api()取得使用者資料
// 			Fbid = response.id;
// 			console.log("response.id:"+ Fbid);
// 			Fbname = response.name;
// 			console.log("response.name:"+ Fbname);
// 			Fbemail = response.email;
// 			console.log("response.email:"+ Fbemail);
// 			Fbpicture = response.picture;
// 			console.log("response.picture:"+ Fbpicture);
// 			Fbbirthday=response.birthday;
// 			console.log("response.birthday:"+ Fbbirthday);
			
// 			returnback();
// 			});
// 	};
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
  	
  	
    function myFun(){
    	   alert("[JavaScript-基本類]按鈕的使用onClick-點擊按鈕時呼叫函數" + "<br>");
    	  }
  	
  
</script>

	<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
<!-- 按鈕1 -->
<!-- <fb:login-button scope="public_profile,email" onlogin="checkLoginState();"> -->
<!-- </fb:login-button>   -->
<!-- 按鈕1 -->

<!-- 按鈕2 -->
<!-- 	<div class="fb-login-button" data-width="80" data-max-rows="1" -->
<!-- 		data-size="large" data-button-type="continue_with" -->
<!-- 		data-show-faces="true" data-auto-logout-link="true" -->
<!-- 		data-use-continue-as="true" onlogin="checkLoginState();" ></div> -->
<!-- 按鈕2 -->

<!-- 按鈕3 -->
<!-- <div class="fb-login-button" data-size="large" data-button-type="login_with" -->
<!--  data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false" onlogin="checkLoginState();"></div> -->
<!-- 按鈕3 -->
<!-- 按鈕4 -->
<a href="#" id="loginFB" class="fa fa-facebook icon icon-border facebook">6666666666</a>
<input type="button" value="請點擊" onClick="myFun()">
<!-- 按鈕4 -->
	<div id="status"></div>
<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>
</html>