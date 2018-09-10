var logindiv;
var logoutSpan;
$(function () {
    logoutSpan = $('#logoutSpan');
    loginSpan = $('#loginSpan');
    $.post(
        "/wegether/login.check"
        , ""
        , function (data) {
            if (data.state == true) {
                loginSpan.toggle();
                logoutSpan.toggle();
            }
        }
        , "json"
    )
    $('#header_nav ul li').click(function () {
        $(this).addClass('active').siblings().removeClass('active');
    })
    logindiv = $('#login');
    $('#login').click(function () {
        var message = $('#loginform').serialize();
        $.post(
            "/wegether/login.do"
            , message
            , function (data) {
                if (data.state == true) {
                    console.log($.cookie('token'));
                    loadIframe('./setting/1.html');
                    var qqq = $(logindiv).closest('.modal');
                    $(qqq).modal('hide');
                    var qqq = $(logindiv).closest('.modal');
                    $(qqq).modal('hide');
                    loginSpan.toggle();
                    logoutSpan.toggle();
                }
            }
            , "json"
        )
    })
    logoutSpan.click(function () {
//        loginSpan.toggle();
//        logoutSpan.toggle();
        if(typeof logoutDo === "function"){
        	alert(123);
        }
//        $.post(
//            "/wegether/logout.do"
//            , ""
//            , function (data) {
//                if (data.state == true) {
//                    loginSpan.toggle();
//                    logoutSpan.toggle();
//                    alert(123);
//                    if(window.logoutDo) {
//                        logoutDo();
//                    }
//                }
//            }
//            , "json"
//        )
    })

})