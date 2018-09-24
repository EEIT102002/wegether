<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//TW" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>Home</title>
	<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
	<link rel="stylesheet" href="/wegether/css/Non-home.css">
    <script src="./js/setting.js"></script>
    <link rel="stylesheet" href="/wegether/setting/css/friendlist.css">
    <link rel="stylesheet" href="/wegether/setting/css/setting.css">
    <script>
        $(function () {
            $('#header_nav ul li').click(function () {
                $(this).addClass('active').siblings().removeClass('active');
            })
        })
    </script>
    <style>
       
    </style>
</head>

<body>
  	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
    <div class="modal fade" id="searchBox" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class= 'friendList'>
					
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-default">關閉</button>
				</div>
			</div>

		</div>
	</div>
    <div class="container">
        <div id="small_con">
            <div id="setting">
                <div class="list">
                    <ul id="list">
                        <li class="choose">個人資料</li>
                        <li>帳號設定</li>
                        <li>隱私設定</li>
                        <li>好友</li>
                        <li>追蹤名單</li>
                        <li>黑名單</li>
                    </ul>
                </div>
                <div class="vl">
                </div>
                <div id="maindiv">
                    <iframe frameborder="0" scrolling="no" id="settingchild"></iframe>
                </div>
            </div>
        </div>
    </div>
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>

</html>