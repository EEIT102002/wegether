<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
</head>
<body>
	<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      //登入後要執行的function
      returnMemberData();
      testAPI();
    } else {
      // The person is not logged into your app or we are unable to tell.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  //登錄完後呼叫 FB.getLoginStatus() 來取得最新的登入狀態
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }
	//初始化
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '2175596062729540',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
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
    FB.getLoginStatus(function(response) {
    	
      statusChangeCallback(response);//呼叫第10行印出response內容

    });

  };

  // Load the SDK asynchronously
  //非同步載入Facebook JavaScript SDK
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  //測試程式
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!';
    });
  }
  

  //把資料送到後端比對
  function returnMemberData(){
		alert("login succest");
		var id,name,email;
		var picture;
		FB.api('/me',{fields : 'id,name,email,picture'},function(response) { //呼叫FB.api()取得使用者資料
			console.log("response.id:"+ response.id);
			id = response.id;
			console.log("response.name:"+ response.name);
			name = response.name;
			console.log("response.email:"+ response.email);
			email = response.email;
			console.log("response.picture:"+ response.picture);
			picture = response.picture;
			});
		
		
// 		$.post("loginfb.controller",
// 				 { FbId:id, 
// 			 	   FbName:name,	
// 			 	   Email:email,
// 			 	   Picture:picture
// 				 },
// 				 function(result){	
// 		 			$.each(result, function(i,item){	
// 		 				divElem =("<div id='msgid'>" +
// 						'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
// 						'<img src="/wegether/member/photo/'+item[0]+'" width="50" class="img-circle">  </a>' +
// 						'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
// 						'<span style="font-size: small;">'+item[2]+'</span>'+
// 						'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除</button>'+			
// 				 		'</br>'+item[3]+'</br>'+
// 						"</div>");
// 		 				temp = temp + divElem;
// 		 			});	
// 		 			$('#demo').html(temp);
// 		 }
// 			);	
		
		
	};
  
  
  
</script>

	<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
	<!--<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>  -->

	<div class="fb-login-button" data-width="80" data-max-rows="1"
		data-size="large" data-button-type="continue_with"
		data-show-faces="true" data-auto-logout-link="true"
		data-use-continue-as="true"></div>

	<div id="status"></div>

</body>
</html>