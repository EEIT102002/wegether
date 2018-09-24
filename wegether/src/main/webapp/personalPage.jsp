<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<script type="text/javascript" src="/wegether/js/personalLogin.js"></script>
<link rel="stylesheet" href="/wegether/css/Non-home.css">


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

	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
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
							width="280" height="280"/>
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
							<span id="memcity2"
								style="font-weight: bold; font-style: italic; padding-top: 4px; -shadow: rgba(255, 255, 255, 0.5) 0 5px 6px, rgba(255, 255, 255, 0.2) 1px 3px 3px;">${mem.city}
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
							</c:choose> <script>
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
<%-- 			${mem.content} --%>
<!-- 			</input> -->
			</div>
			<!-- core end -->

		</div>
	</div>
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
	
</body>
<script>
	
</script>
</html>