<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<link
	href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
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
	
 
	
	
	function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}
}
	  document.addEventListener("DOMContentLoaded", function () {
          document.getElementById("idName").addEventListener("blur",chkName);  //事件繫結，焦點離開                                    
                            });
       
     
      //姓名驗證
      function chkName() {            
          var idName = document.getElementById("idName").value;
          var re=/^[\u4e00-\u9fff]{2,}$/; //中文字在unicode的區間
          if(idName==""){
              document.getElementById("idspName").
              innerHTML=" <i > 姓名不可為空白</i>";
          }else  if (re.test(idName)) 
              document.getElementById("idspName").
              innerHTML="";
          else              
              document.getElementById("idspName").
              innerHTML=" <i > 必須兩個字以上的中文字！</i>";
      }

      
    
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
</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default" id="stickytop">
		<div class="navbar-header">
			<button type="button" class="
				   collapsed"
				data-toggle="collapse" data-target="#dropdown_munu" id="hum">
				<span><i class="fa fa-bars" aria-hidden="true"></i></span>
			</button>
			<div class="logo">
				<h1>
					<a class="navbar-brand" href="index.html">Wegther</a>
				</h1>
			</div>
		</div>
		<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
			<nav class="header_nav" id="header_nav">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#"><span data-hover="活動">活動</span></a></li>
				<li><a href="#" class="scroll"><span data-hover="心得">心得</span></a></li>
				<li><a href="#" class="scroll"><span data-hover="發起活動">發起活動</span></a></li>
				<li><a href="#" class="scroll"><span data-hover="發起心得">發起心得</span></a></li>
				<li><a href="#" class="scrol" data-toggle="modal"
					data-target="#ActPageBox"><span data-hover="登入">登入</span></a></li>
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
						<div class="form-group" id="ACT">
							<label for="recipient-name" class="col-form-label">帳號:</label> <input
								type="text" class="form-control" id="account">
						</div>
						<div class="form-group" id="PWD">
							<label for="recipient-name" class="col-form-label">密碼:</label> <input
								type="text" class="form-control" id="password">
						</div>
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
						<button type="button" class="btn btn-primary">登入</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="small_con">
		
			<div align="center">
				<hr >
				註冊頁面
				<hr/>
				<form id="xxx" action="<c:url value="/register.controller" />" method="post"
					accept-charset="ISO-8859-1" enctype="multipart/form-data">
					<table border="0" align="center">
						<tr>
							<td colspan="2" align="center"></td>
						</tr>
						<tr>
							<td>帳號(信箱):</td>
							<td><input  type="text" name="account" value="${param.account}" ></td>
							<td>${inputRrrors.account}</td>
						</tr>
						<tr>
							<td>密碼:</td>
							<td><input id="idPwd" type="password" name="pwd" value="${param.pwd}"  ></td>
							<td id="idspPwd"></td>
						</tr>
						<tr>
							<td>姓名:</td>
							<td><input id="idName" type="text" name="name" value="${param.name}" ></td>
							<td id="idspName"></td>
							
						</tr>
						<tr>
							<td>暱稱:</td>
							<td><input  type="text" name="nickname" value="${param.nickname}"></td>
							<td>${inputRrrors.nickname}</td>
						</tr>
						<tr>
							<td>出生日期:</td>
							<td><input type="date" name="birthday" value="${param.birthday}" required="required"></td>
						</tr>
						
						<tr>
							<td>性别:</td>
							<td>
							      <select name="sex" size="1">
									<option value="0">男生</option>
									<option value="1">女生</option>		
							  </select>
							</td>
						</tr>
						<tr>
							<td>職業:</td>							
							 <td><input  type="text" name="job" value="${param.name}"></td>		
						</tr>						
						<tr>
							<td>城市/所在地:</td>
							<td>
								<select name="city"  >
									<option value="0">台北市</option>
									<option value="1">新北市</option>
								</select>
								
							</td>
						</tr>
						
						<tr>
							<td>詳細地址:</td>
							<td><input type="text" name="addr" value="${param.addr}"></td>
							<td>${inputRrrors.addr}</td>
						</tr>						
						<tr>
							<td>電話:</td>
							<td><input type="text" name="tel" value="${param.tel}"></td>
							<td>${inputRrrors.tel}</td>
						</tr>
												
						<tr>
							<td>自我介紹:</td>
							<td><textarea rows="5" cols="30" name="content" value="${param.content}"></textarea></td>
							<td>${inputRrrors.content}</td>
						</tr>
						<tr>
							<td>聚會類型:</td>
							<td>
							<input type="checkbox" name="favorite" value="輕鬆聊" checked>輕鬆聊
							<input type="checkbox" name="favorite" value="浪漫約會">浪漫約會
							<input type="checkbox" name="favorite" value="寵物">寵物
							<br>
							<input type="checkbox" name="favorite" value="桌遊">桌遊
							<input type="checkbox" name="favorite" value="郊遊踏青">郊遊踏青
							<input type="checkbox" name="favorite" value="電影">電影
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center" >
							  <input type="submit" name="prodaction" value="送出"> 
							  <input type="button" value="Clear" onclick="clearForm()">
							</td>
						</tr>
					</table>
				</form>
			</div>

			<!--       寫在這 -->
		</div>
	</div>

	<!-- <footer>
		<div class="container">
			<p id="fw">Wegther 2018</p>
		</div>
	</footer> -->
	<!-- <i  id="upTop" class="fa fa-chevron-circle-up animated infinite bounce" aria-hidden="true"></i> -->
	<footer class="container">
	<ul>
		<li><a href="" title="">關於Wegether</a>
			<ul>
				<li>-我們的出發</li>
				<li>-我們的團隊</li>
				<li>-我們的服務</li>
			</ul></li>
		<li><a href="" title="">聯絡我們</a>
			<ul>
				<li>-聯絡我們</li>
			</ul></li>
	</ul>
	<p class="text-center">- Wegther 2018 EEIT10202 -</p>
	</footer>
</body>
</html>