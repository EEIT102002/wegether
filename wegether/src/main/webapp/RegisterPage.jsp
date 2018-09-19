<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src="/wegether/js/jquery.cookie.js" type="text/javascript"></script>
<script src="./js/setting.js"></script>
<script src="/wegether/js/noticeWebStocket.js" type="text/javascript"></script>
<script src="/wegether/js/logMethod.js" type="text/javascript"></script>
<script type="text/javascript" >
	$(function() {
		var array_for_city = [ '基隆市', '台北市', '新北市', '宜蘭縣', '桃園市', '新竹市', '新竹縣',
				'苗栗縣', '台中市', '彰化縣', '南投縣', '雲林縣', '嘉義市', '嘉義縣', '台南市', '高雄市',
				'屏東縣', '花蓮縣', '台東縣', '澎湖', '金門', '馬祖' ]
		for (var i = 1; i <= array_for_city.length; i++) {
			//		              console.log(array_for_city[i-1]);
			var x1 = "<option value="+i+">" + array_for_city[i - 1]
					+ "</option>"
			$('#zzz').append(x1);
		}

		$('#header_nav ul li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
		})

	})

	function loginDo(){
		
		
	}
	
	function logoutDo(){
		
		
	}
	
	function clearForm() {
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				inputs[i].value = "";
			}
		}
	}
	document.addEventListener("DOMContentLoaded", function() {
		
		document.getElementById("idaccount").addEventListener("blur", chkaccount); //事件繫結，焦點離開 
		document.getElementById("idpassword").addEventListener("blur", chkpassword); //事件繫結，焦點離開 
		document.getElementById("idrepassword").addEventListener("blur", chkrepassword); //事件繫結，焦點離開 
		document.getElementById("idtel").addEventListener("blur", chktel); //事件繫結，焦點離開 
		document.getElementById("idName").addEventListener("blur", chkName); //事件繫結，焦點離開 
		document.getElementById("idaddr").addEventListener("blur", chaddr); //事件繫結，焦點離開  
		document.getElementById("idjob").addEventListener("blur", chjob); //事件繫結，焦點離開  
	});
	
	//帳號驗證消除
	function chkaccount() {
		var idaccount = document.getElementById("idaccount").value;
		if (idaccount == "") {
			document.getElementById("idspaccount").innerHTML = "帳號不可為空白";
		} else
			document.getElementById("idspaccount").innerHTML = "";
	}
	//密碼驗證消除
	function chkpassword() {
		var idpassword = document.getElementById("idpassword").value;
		if (idpassword == "") {
			document.getElementById("idsppassword").innerHTML = "密碼不可為空白";
		} else
			document.getElementById("idsppassword").innerHTML = "";
	}
	//確認密碼驗證消除
	function chkrepassword() {
		var idrepassword = document.getElementById("idrepassword").value;
		if (idrepassword == "") {
			document.getElementById("idsprepassword").innerHTML = "確認密碼不可為空白";
		} else
			document.getElementById("idsprepassword").innerHTML = "";
	}
	//電話驗證消除
	function chktel() {
		var idtel = document.getElementById("idtel").value;
		if (idtel == "") {
			document.getElementById("idsptel").innerHTML = "電話不可為空白";
		} else
			document.getElementById("idsptel").innerHTML = "";
	}

	//姓名驗證
	function chkName() {
		var idName = document.getElementById("idName").value;
		var re = /^[\u4e00-\u9fff]{2,}$/; //中文字在unicode的區間
		if (idName == "") {
			document.getElementById("idspName").innerHTML = " <i style='color:red'> 姓名不可為空白</i>";
		} else if (re.test(idName))
			document.getElementById("idspName").innerHTML = "";
		else
			document.getElementById("idspName").innerHTML = " <i style='color:red' > 必須兩個字以上的中文字！</i>";
	}
// 	//地址中文驗證
	function chaddr() {
		var idaddr = document.getElementById("idaddr").value;
		var re = /^[\u4e00-\u9fff]{0,}$/; //中文字在unicode的區間
		if (idaddr == "") {
			document.getElementById("idspaddr").innerHTML = "";
		} else if (re.test(idaddr))
			document.getElementById("idspaddr").innerHTML = "";
		else 
			document.getElementById("idspaddr").innerHTML = " <i style='color:red' >請輸入中文</i>";
	}
// 	//職業中文驗證
	function chjob() {
		var idjob = document.getElementById("idjob").value;
		var re = /^[\u4e00-\u9fff]{0,}$/; //中文字在unicode的區間
		if (idjob == "") {
			document.getElementById("idspjob").innerHTML = "";
		} else if (re.test(idjob))
			document.getElementById("idspjob").innerHTML = "";
		else 
			document.getElementById("idspjob").innerHTML = " <i style='color:red' >請輸入中文</i>";
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

.s1 {
	font-size: 13px;
	font-style: italic;
	margin-top: 4px;
	color: grey;
	padding-bottom: 4px;
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
				<span> <i class="fa fa-bars" aria-hidden="true"></i>
				</span>
			</button>
			<div class="logo">
				<h1>
					<a class="navbar-brand" href="/wegether/index.jsp">Wegther111</a>
				</h1>
			</div>
		</div>
		<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
			<nav class="header_nav" id="header_nav">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#"> <span data-hover="活動">活動</span>
				</a></li>
				<li><a href="#" class="scroll"> <span data-hover="心得">心得</span>
				</a></li>
				<li><a href="#" class="scroll"> <span data-hover="發起活動">發起活動</span>
				</a></li>
				<li><a href="#" class="scroll"> <span data-hover="發起心得">發起心得</span>
				</a></li>
				<li><a id="loginSpan" href="#" class="scrol"
					data-toggle="modal" data-target="#ActPageBox"> <span
						data-hover="登入">登入</span>
				</a> <a id="logoutSpan" href="#" class="scrol" style="display: none">
						<span data-hover="登出">登出</span>
				</a></li>
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
	<div class="container">
		<div id="small_con">

			<div align="center">
				<hr>
				<h3 style="color:#0066FF	;padding: 4px; font-weight: bold; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">
				註冊頁面
				</h3>
				<hr />
				<form style="margin-left: 98px"style="margin-left: 40px"id="xxx" action="<c:url value="/register.controller" />"
					method="post" accept-charset="ISO-8859-1"
					enctype="multipart/form-data">
					<table  border="0" align="center"
						style="border-collapse: separate; border-spacing: 0px 6px;font-family:Microsoft JhengHei;color:#0044BB;">
						<tr>
<!-- 						style="font-family:DFKai-sb;" -->
							<td colspan="2" align="center"></td>
						</tr>
						<tr>
							<td>帳&nbsp;號&nbsp;(信&nbsp;箱):</td>
							<td><input id="idaccount" type="text" name="account"
								value="${param.account}"><i id="idspaccount" style=color:red;>${inputRrrors.account}</i></td>

						</tr>
						<tr>
							<td></td>
							<td class="s1">(例如:stvc@yahoo.com.tw)</td>
						</tr>
						<tr>
							<td >密&nbsp;碼:</td>
							<td><input id="idpassword" type="password" name="pwd" value="${param.pwd}">
							<i id="idsppassword" style=color:red;>${inputRrrors.pwd}</i>
							</td>
						</tr>
						<tr>
							<td>確&nbsp;認&nbsp;密&nbsp;碼:&nbsp;</td>
							<td><input id="idrepassword" type="password" name="pwd2" value="${param.pwd2}">
							<i id="idsprepassword" style=color:red;>${inputRrrors.pwd2}</i>
							
							</td>
						</tr>
						<tr>
							<td></td>
							<td class="s1">(1.密碼長度8~16碼、2.不能有特殊符號、3.必須要有英文及數字)</td>
						</tr>
						<tr>
							<td>姓&nbsp;名:</td>
							<td><input id="idName" type="text" name="name"
								value="${param.name}"> 
								<i style=color:red; id="idspName">${inputRrrors.name}</i>
							</td>
						</tr>
						<tr>
							<td></td>
							<td class="s1">(1.不可空白，2.至少兩個字以上，3.必須全部為中文字)</td>

						</tr>
						<tr>
							<td>暱&nbsp;稱:</td>
							<td><input type="text" name="nickname"
								value="${param.nickname}"></td>
							<td>${inputRrrors.nickname}</td>
						</tr>
						<tr>
							<td>出&nbsp;生&nbsp;日&nbsp;期:&nbsp;</td>
							<td><input style="width: 165px; height: 25px;" type="date"
								name="birthday" value="${param.birthday}" required="required"
								min="1900-09-06"></td>
						</tr>

						<tr>
							<td>性&nbsp;别:</td>
							<td><select name="sex" size="1">
									<option value="0">男生</option>
									<option value="1">女生</option>
							</select></td>
						</tr>
						<tr>
							<td>職&nbsp;業:</td>
							<td><input type="text" name="job" value="${param.name}" id="idjob"><i id="idspjob">${inputRrrors.job}</i></td>
						</tr>
						<tr>
							<td>城&nbsp;市&nbsp;/&nbsp;地&nbsp;址&nbsp;:&nbsp;</td>
							<td><select name="city" id="zzz">
									<option value="0">請選擇</option>
							</select> <input style="width: 110px; height: 25px;" type="text"
								name="addr" value="${param.addr}" id="idaddr"><i style=color:red; id="idspaddr">${inputRrrors.addr}</i></td>
                                                                                       
						</tr>

						<tr>
							<td>電&nbsp;話:</td>
							<td><input type="text" name="tel" value="${param.tel}" id="idtel"><i id="idsptel" style=color:red>${inputRrrors.tel}</i></td>

						</tr>
						<tr>
							<td></td>
							<td class="s1">(手機電話 : 0939905649)</td>
						</tr>

						<tr>
							<td>自&nbsp;我&nbsp;介&nbsp;紹:&nbsp;</td>
							<td><textarea rows="4" cols="24" name="content"
									value="${param.content}"></textarea></td>
							<td>${inputRrrors.content}</td>
						</tr>
						<tr>
							<td>聚&nbsp;會&nbsp;類&nbsp;型:&nbsp;</td>
							<td><input type="checkbox" name="favorite" value="輕鬆聊"
								checked>輕鬆聊 <input type="checkbox" name="favorite"
								value="浪漫約會">浪漫約會 <input type="checkbox" name="favorite"
								value="寵物">寵物 <br> <input type="checkbox"
								name="favorite" value="桌遊">桌遊 <input type="checkbox"
								name="favorite" value="郊遊踏青">郊遊踏青 <input type="checkbox"
								name="favorite" value="電影">電影</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input
								style="margin-top: 5px;margin-right: 50px;" type="submit" name="prodaction"
								value="送出"> <input style="margin-top: 5px;margin-right: 50px;" type="button" value="Clear"
								onclick="clearForm()"></td>
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