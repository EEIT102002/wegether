<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="css/style.css"/>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>

</head>
<body>
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
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="mid-body">
                        <form id="loginform">
                        	<p class="loginerror" ></p>
                            <div class="form-group" id="ACT">
                                <label for="recipient-name" class="col-form-label">帳號:</label>
                                <input type="text" class="form-control" id="account" name="account">
                            </div>
                            <div class="form-group" id="PWD">
                                <label for="recipient-name" class="col-form-label">密碼:</label>
                                <input type="password" class="form-control" id="pwd" name="pwd">
                            </div>
                            <button type="button" class="btn btn-primary" id="login">登入</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        </form>
                        <div id="or" class="bg-primary text-white">
                            <h4>or</h4>
                        </div>
                        <button type="button" class="btn big_use Google_i" id="googlelogin">
                            <i class="fa fa-google" aria-hidden="true"></i>Google 登入</button>
                        <button type="button" class="btn big_use Fb_i" id="facebooklogin" onClick="fbLogin()">
                            <i class="fa fa-facebook-official" aria-hidden="true"></i>FB 登入</button>
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
                        <p class="small text-left">還沒註冊嗎?趕緊註冊一個帳號吧!
                            <a href="javascript:void(0)">點我註冊</a>
                        </p>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the persopren.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        login(response.authResponse.accessToken);
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
        console.log('The person is logged into Facebook, but not your app');
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
        console.log("The person is not logged into Facebook");
    }
}
// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}
window.fbAsyncInit = function() {
    FB.init({
        appId      : "2135323470127415",
        cookie     : true,  // enable cookies to allow the server to access 
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v3.1' // use version 2.2
    });
};
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
// Load the SDK asynchronously
(function(d, s, id) {
var js, fjs = d.getElementsByTagName(s)[0];
if (d.getElementById(id)) return;
js = d.createElement(s); js.id = id;
js.src = "//connect.facebook.net/en_US/sdk.js";
fjs.parentNode.insertBefore(js, fjs);
}(document, "script", "facebook-jssdk"));
// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function loginNEMI(token) {
    // 把 access_token 傳至後端再做資料拿取
    console.log("Welcome!  Fetching your information.... ");
    var xhr=new XMLHttpRequest();
    xhr.open("POST", "/login", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange=function()
    {
      if(xhr.readyState === 4 && xhr.status === 200)
      {
          if(JSON.parse(xhr.responseText).status === "ok")
            location.href="/index";
          else
            alert("something wrong!");
      }  
    };
    xhr.send("token="+token);
}
// custom fb login button
function fb_login()
{
    // FB 第三方登入，要求公開資料與email
    FB.login(function(response)
    {
        statusChangeCallback(response);
        console.log(response);
    }, {scope: 'public_profile,email'});
}
</script>
</html>