var logindiv;
var logoutSpan;
$(function () {
    logoutSpan = $('#logoutSpan');
    loginSpan = $('#loginSpan');
    logindiv = $('#login');
    $.post(
        "/wegether/login.check"
        , ""
        , function (data) {
            if (data.state == true) {
            	
            	logingroup(data);
            }
        }
        , "json"
    )
   
    $('#login').click(function () {
        var message = $('#loginform').serialize();
        $.post(
            "/wegether/login.do"
            , message
            , function (data) {
                if (data.state == true) {
                    console.log($.cookie('token'));
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
    })
    logoutSpan.click(function () {
        logoutf();
    })
    $('#loginform').on("click","input",function(){
    	$('.loginerror').text("");
    })

})

function logingroup(data){
	 if(typeof window.loginDo === "function") {
     	loginDo();//登入後要做的方法放在loginDo()
     }                   
     logtoggle();//login或logout後的顯示切換
     connectNotice(data.ntoken);
}

function logoutf(){//登出要執行的功能
	$.post(
            "/wegether/logout.do"
            , ""
            , function (data) {
                if (data.state == true) {
                	logtoggle();
                    if(typeof window.logoutDo === "function") {
                        logoutDo();//登出後要做的方法放在logoutDo()
                    }
                }
            }
            , "json"
        )
}

function logtoggle(){ //login或logout後的顯示切換
	 loginSpan.toggle();
     logoutSpan.toggle();
}





