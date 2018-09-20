<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<link
	href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
<title>增加心得</title>
<script>
	$(function() {
		$('#header_nav ul li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
		})
	})
	document.addEventListener("DOMContentLoaded", function() {
		//document.getElementById("actPic1").addEventListener("change", fileMultiViewer);
		document.getElementById("addPic").addEventListener("click", function(){
			var idname = $(':file[name^="multi"]:last').attr("id");
			var count = idname.charAt(6);
			count++;
			var div = '<tr class="tr"><td></td><td><img id="picZone'+count+'" class="selPic2"><input type="file" class="up" name="multipicture" id="actPic'+count+'" accept="image/*"></td></tr>';
			$('.tr :last').after(div);
		});
	});
	function fileMultiViewer() {
 		$('#picZone1').empty();
		//取得使用者在檔案選擇標籤中選取檔案
		var theFiles = document.getElementById("actPic1").files;
		for (var i = 0; i < theFiles.length; i++) {
			//1. 建立FileReader物件
			var reader = new FileReader();
			//3.onload資料讀取成功完成時觸發
			reader.addEventListener("load", function(e) {
				//4. 將檔案內容暫存
				var fileMultiContent = e.target.result;
				//alert(fileMultiContent);
				//5. 找到img標籤
				var imgobj = document.createElement("img");
				//6. 設定img的src屬性
				imgobj.setAttribute("src", fileMultiContent);
				imgobj.setAttribute("class","multi");
				document.getElementById("picZone1").appendChild(imgobj);
			});
			//2. 使用readAsDataURL方法，讀取檔案內容
			reader.readAsDataURL(theFiles[i]);
		}
	}

	$(function() {
		$('#preBotton').click(function() {
			$('#actDescription').empty().append($('#insertDes').val());
		})
	})
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
.multi{
	height:150px;
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
					<a class="navbar-brand" href="index.html">Wegether</a>
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
							<label for="recipient-name" class="col-form-label">帳號:</label>
							<input type="text" class="form-control" id="account" name="account">
						</div>
						<div class="form-group" id="PWD">
							<label for="recipient-name" class="col-form-label">密碼:</label>
							<input type="text" class="form-control" id="pwd" name="pwd">
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
						<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="small_con">
			<!--       寫在這 -->
			增加心得
			<form id="ArticleCreateForm"
				action="<c:url value="/articleCreate.controller"/>" method="post"
				accept-charset="ISO-8859-1" enctype="multipart/form-data">
				<table>
					<tr>
						<td>聚會標題</td>
						<td>123</td>
					</tr>
					<tr>
						<td>心得</td>
						<td><textarea name="content" id="insertDes" cols="30" rows="10"></textarea>${content}</td>
					</tr>
					<tr class="tr">
						<td>上傳活動照片</td>
						<td><img id="picZone1" class="selPic2">
						<input type="file" class="up" name="multipicture" id="actPic1" accept="image/*"></td>
					</tr>
					<tr><td></td><td><input type="button" id="addPic" value="新增圖片"/></td></tr>
				</table>

				<input type="button" name="" value="預覽" id="preBotton" data-target="#preview" data-toggle="modal" />
				<div class="modal fade" id="preview" tabindex="-1" role="dialog">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<!--白色遮罩層-->
							<div class="modal-body">
								<!--       // modal-body  有差padding -->
								<div class="modal-header">
									<h5 class="modal-title lead">
										<strong>預覽</strong>
									</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body" id="mid-body">
									<table>
										<tr>
											<td>聚會標題</td>
											<td id="actName"></td>
										</tr>
										<tr>
											<td>詳細描述</td>
											<td id="actDescription"></td>
										</tr>
										<tr>
											<td>上傳活動照片</td>
											<td id="artPic"></td>
										</tr>
									</table>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary">確認送出</button>
									<button type="button" class="btn btn-secondary" data-dismiss="modal">修改</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
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
	<p class="text-center">- Wegether 2018 EEIT10202 -</p>
	</footer>
</body>
</html>