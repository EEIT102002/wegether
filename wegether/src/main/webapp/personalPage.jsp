<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/raty/2.8.0/jquery.raty.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/raty/2.8.0/jquery.raty.min.css

">
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
		$('.changePage:eq(0)').click(function() {
			$('#change_one').css({
				'display' : 'block'
			})

			// 			$.ajax({
			// 					method:'POST',
			// 				  	url: 'personalPage.jsp',
			// 				  	success: function(data){
			// 					$('#second').html(data);
			// 				  },
			// 				});
			$('#second').css({
				'display' : 'none'
			});
			$('#third').css({
				'display' : 'none'
			});
			$('#fourth').css({
				'display' : 'none'
			});
			// 			$('#change_area').html("A");//改區塊內容 之後要修改成你要變換的 資料庫資料 
		})
		$('.changePage:eq(1)').click(function() {
			$('#second').css({
				'display' : 'block'
			})
			// 			$.ajax({
			// 					method:'POST',
			// 				  	url: 'personalJugePage.jsp',
			// 				  	success: function(data){
			// 					$('#second').html(data);
			// 				  },
			// 				});

			$('#change_one').css({
				'display' : 'none'
			});
			$('#third').css({
				'display' : 'none'
			});
			$('#fourth').css({
				'display' : 'none'
			});

			var ck = "${mem.rank1}";//要帶入的分數
			for (var i = 1; i <= 5; i++) {
				if (i <= ck) {
					document.getElementById("idstar" + i).className = "n";
				} else {
					document.getElementById("idstar" + i).className = "s";
				}
			}

			var ck = "${mem.rank2}";//要帶入的分數
			for (var i = 1; i <= 5; i++) {
				if (i <= ck) {
					document.getElementById("1dstar" + i).className = "n";
				} else {
					document.getElementById("1dstar" + i).className = "s";
				}
			}

			var ck = "${mem.rank3}";//要帶入的分數
			for (var i = 1; i <= 5; i++) {
				if (i <= ck) {
					document.getElementById("2dstar" + i).className = "n";
				} else {
					document.getElementById("2dstar" + i).className = "s";
				}
			}

		})

		$('.changePage:eq(2)').click(function() {
			$('#third').css({
				'display' : 'block'
			})

			$('#change_one').css({
				'display' : 'none'
			});
			$('#second').css({
				'display' : 'none'
			});
			$('#fourth').css({
				'display' : 'none'
			});
		})

		$('.changePage:eq(3)').click(function() {
			$('#fourth').css({
				'display' : 'block'
			})

			$('#change_one').css({
				'display' : 'none'
			});
			$('#second').css({
				'display' : 'none'
			});
			$('#third').css({
				'display' : 'none'
			});
		})
	})

	//星級評分

	// http://blog.shihshih.com/css-filter/
	//         document.addEventListener("DOMContentLoaded", function () {
	//             var areas=document.querySelectorAll("img")
	//             var a=areas.length;
	//             var ck= "${mem.rank1}";//要帶入的分數
	//           //  var x = parseFloat('${mem.rank1}');
	//             alert(ck);

	// 			for(var i=1;i<=a;i++){
	//                 if(i<=ck){
	//                 document.getElementById("idstar"+i).className = "n";
	//                 }else{
	//                 document.getElementById("idstar"+i).className = "s";
	//                 }
	//             }
	//         });
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

#core1 {
	overflow: auto;
	width: 100%;
	/* border: 1px solid gray; */
}

#left {
	width: 50%;
	float: left;
}

#right {
	width: 50%;
	float: right;
}

#txtup {
	text-align: left;
}

#txtcenter {
	text-align: center;
}

#change_area {
	border: 2px solid;
}

#second, #third, #fourth {
	display: none;
}

/* 星級評分 */
.s {
	-webkit-filter: grayscale(1); /*沒有任何色彩的黑白影像*/
	width: 50px;
}

.n {
	-webkit-filter: grayscale(0); /*顏色不變*/
	width: 50px;
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
					<a class="navbar-brand" href="/wegether/index.jsp">Wegther</a>
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
			<!--       寫在這 -->
			<div id="core">
				<div id="left">
					<div style="text-align: center">
						<!-- 						<img class="img-circle" -->
						<%-- 							src="data:image/jpg;base64,${picbean.get(0)}" width="280" --%>
						<!-- 							style="position: relative; top: 20px;"> -->
						<img src="/wegether/member/photo/${pic}" class="img-circle"
							width="280" height="280">
					</div>
					<div style="text-align: center">
						<br> <br>
						<table style="margin-left: 135px" border="0" align="center">
							<tr>
								<td
									style="padding: 20px; font-weight: bold; font-family: 微軟正黑體; color: #1E90FF; text-shadow: 2px 8px 6px rgba(0, 0, 0, 0.2), 0px -5px 35px rgba(255, 255, 255, 0.3);">
									追蹤人數</td>
								<td
									style="padding: 20px; font-weight: bold; font-family: 微軟正黑體; color: #1E90FF; text-shadow: 2px 8px 6px rgba(0, 0, 0, 0.2), 0px -5px 35px rgba(255, 255, 255, 0.3);">
									參加活動</td>
								<td
									style="padding: 20px; font-weight: bold; font-family: 微軟正黑體; color: #1E90FF; text-shadow: 2px 8px 6px rgba(0, 0, 0, 0.2), 0px -5px 35px rgba(255, 255, 255, 0.3);">
									我的好友</td>
							</tr>
							<tr>
								<td style="padding: 10px;">${trackbean}</td>
								<td style="padding: 10px;">${attbean}</td>
								<td style="padding: 10px;">${fribean}</td>
							</tr>
						</table>
					</div>


				</div>
				<div id="right">
					<div id="core1">
						<div id="left" style="padding-top: 6px;">
							<span
								id="memcity2" style="font-weight: bold; font-style: italic; padding-top: 4px; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">${mem.city}
								<script>
							$(function(){
								var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
								      ,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖']
// 								var x = $('#memcity').val();
// 								alert(x);
								$('#memcity2').html(array_for_city[${mem.city}]);
								
							})
							</script>
							</span>
						</div>

						<table border="0">
							<tr>
								<td type="button" class="btn btn-secondary "
									style="padding: 4px; font-weight: bold; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">
									<c:if test="${fc[0]==1}">
										<a href="activityPage.controller?actid=1" class="scroll">編輯</a>
									</c:if>

								</td>
								<td type="button" class="btn btn-secondary "
									style="padding: 4px; font-weight: bold; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">
									<c:if test="${fc[1]==1}">
										<a href="activityPage.controller?actid=1" class="scroll">追蹤</a>
									</c:if>

								</td>
								<td type="button" class="btn btn-secondary "
									style="padding: 4px; font-weight: bold; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">
									<c:if test="${fc[2]==1}">
										<a href="activityPage.controller?actid=1" class="scroll">加入好友</a>
									</c:if>

								</td>
								<td type="button" class="btn btn-secondary "
									style="padding: 4px; font-weight: bold; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">
									<c:if test="${fc[3]==1}">
										<a href="activityPage.controller?actid=1" class="scroll">黑名單</a>
									</c:if>

								</td>
							</tr>
						</table>
					</div>
					<div>
						<table style="border-bottom: 3px #87cefa solid;" border="0">
							<tr>

								<td type="button" class="btn btn-secondary changePage"
									style="padding: 20px; font-weight: bold; font-size: 16px; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">個人資料</td>
								<td type="button" class="btn btn-secondary changePage"
									style="padding: 20px; font-weight: bold; font-size: 16px; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">活動評價</td>
								<td type="button" class="btn btn-secondary changePage"
									style="padding: 20px; font-weight: bold; font-size: 16px; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">主辦活動</td>
								<td type="button" class="btn btn-secondary changePage"
									style="padding: 20px; font-weight: bold; font-size: 16px; font-style: italic; text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">參加活動</td>
							</tr>
						</table>
					</div>
					<div id="change_one">

						<h4 style="font-weight: bold">暱稱:</h4>
						<span style="font-size: 15px;">${mem.nickname}</span>
						<h4 style="font-weight: bold">出生日期:</h4>
						<span style="font-size: 15px;"> <c:choose>
								<c:when test="${mem.birthday==null}">
									<span style="font-size: 15px;">對方未公開</span>
								</c:when>
								<c:otherwise>
									<fmt:formatDate value="${mem.birthday}" pattern=" MM 月 dd 日" />
								</c:otherwise>
							</c:choose>
						</span>
						<h4 style="font-weight: bold">性別:</h4>
						<span style="font-size: 15px;"> <%--                               <c:if test="${mem.sex==0}" value="boy"/> --%>
							<%--                               <c:out value="boy" /> --%> <%--                               <c:if test="${mem.sex==1}" value="girl"/> --%>
							<%--                               <c:out value="girl" /> --%> <c:if
								test="${mem.sex==0}">男生 </c:if> <c:if test="${mem.sex==1}">女生 </c:if>
							<c:if test="${mem.sex==null}">對方未公開 </c:if>

						</span>

						<h4 style="font-weight: bold">職業:</h4>
						<span style="font-size: 15px;"> <c:choose>
								<c:when test="${mem.job==null}">
									<span style="font-size: 15px;">對方未公開</span>
								</c:when>
								<c:otherwise>
									<span style="font-size: 15px;">${mem.job}</span>
								</c:otherwise>
							</c:choose>
						</span>
						<h4 style="font-weight: bold">居住縣市:</h4>
						<span style="font-size: 15px;"> <c:choose>
								<c:when test="${mem.city==null}">
									<span style="font-size: 15px;">對方未公開</span>
								</c:when>
								<c:otherwise>
									<span style="font-size: 15px;" id="memcity">${mem.city}</span>
								</c:otherwise>
							</c:choose>
							<script>
							$(function(){
								var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
								      ,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖']
// 								var x = $('#memcity').val();
// 								alert(x);
								$('#memcity').html(array_for_city[${mem.city}]);
								
							})
							</script>
						</span>
						<h4 style="font-weight: bold">喜好活動類型:</h4>
						<span style="font-size: 15px;"> <c:choose>
								<c:when test="${mem.favorite==null}">
									<span style="font-size: 15px;">對方未公開</span>
								</c:when>
								<c:otherwise>
									<span style="font-size: 15px;">${mem.favorite}</span>
								</c:otherwise>
							</c:choose>
						</span>
						<h4 style="font-weight: bold">自我簡介:</h4>
						<span style="font-size: 15px;"> <c:choose>
								<c:when test="${mem.content==null}">
									<span style="font-size: 15px;">對方未公開</span>
								</c:when>
								<c:otherwise>
									<span style="font-size: 15px;">${mem.content}</span>
								</c:otherwise>
							</c:choose>
						</span>
					</div>
					<!--                               change_one end -->
					<div id="second">
						<br> <br>
						<table border="0">
							<tr>
								<td style="padding: 20px; font-weight: bold">活動滿意度:</td>
								<td style="padding: 20px;"><img id="idstar1" class="s"
									src="images/star.png" /> <img id="idstar2" class="s"
									src="images/star.png" /> <img id="idstar3" class="s"
									src="images/star.png" /> <img id="idstar4" class="s"
									src="images/star.png" /> <img id="idstar5" class="s"
									src="images/star.png" /></td>
							</tr>
							<tr>
								<td style="padding: 20px; font-weight: bold">溝通安排:</td>
								<td style="padding: 20px;"><img id="1dstar1" class="s"
									src="images/star.png" /> <img id="1dstar2" class="s"
									src="images/star.png" /> <img id="1dstar3" class="s"
									src="images/star.png" /> <img id="1dstar4" class="s"
									src="images/star.png" /> <img id="1dstar5" class="s"
									src="images/star.png" /></td>
							</tr>
							<tr>
								<td style="padding: 20px; font-weight: bold">時間地點選擇:</td>
								<td style="padding: 20px;"><img id="2dstar1" class="s"
									src="images/star.png" /> <img id="2dstar2" class="s"
									src="images/star.png" /> <img id="2dstar3" class="s"
									src="images/star.png" /> <img id="2dstar4" class="s"
									src="images/star.png" /> <img id="2dstar5" class="s"
									src="images/star.png" /></td>
							</tr>
						</table>

					</div>
					<!-- change_second end -->
					<div id="third">
						<br>
						<table style="margin-left: 10px;" border="0" RULES=ROWS
							frame="below">
							<c:forEach var="obj3" items="${hostsum}">
							 	     ${obj3}
							       </c:forEach>
						</table>
					</div>
					<!--change_third end -->
					<div id="fourth">
						<br>
						<table style="margin-left: 10px;" border="0" RULES=ROWS
							frame="below">
							<c:forEach var="obj2" items="${attsum}">
							 	     ${obj2}
							       </c:forEach>
						</table>


					</div>
					<!--change_fourth end -->
				</div>

			</div>
			<!-- core end -->

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
<script>
	
</script>
</html>