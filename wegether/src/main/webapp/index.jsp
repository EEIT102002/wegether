<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"  href="css/bootstrap.css" />
<link rel="stylesheet"  href="css/style.css"/>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script>
	$(function(){
		$.ajax("${pageContext.request.contextPath}/activity",{
			method:"GET",
			success:function(jsonArray){
// ===========================單筆 測試
// 				console.log(jsonArray);
// 				console.log(data[0]);
// 				console.log(data[6][1]);
// 				console.log(jsonArray[6][0].id);
// 				console.log(jsonArray[6][2])
				
// 				var str1 = "<img alt='' class='img-responsive' src=\""+data[6][1]+"\"/>";
// 				str1+="<div>";
				
// 				str1+="id:"+data[6][0].id+"title:"+data[6][0].title;
// 				str1+="</div>";
// 				alert("A")
// 				if($("#show_act_area").html!=null){
// 					alert("A-1");
// 				}else{
// 					alert("A-2");
// 				}

// 				$("#show_act_area").append(str1);
// 				alert('A的append');
// ===========================單筆 測試
				var i = 0;
		        $.each(jsonArray, function() {
		        var date = new Date(jsonArray[i][0].actbegin);
					Y = date.getFullYear() + '-';
					M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
					D = date.getDate() + ' ';
	        	var str1="<div class='col-xs-12 col-sm-6 col-md-4 item'><figure>";
	        	 str1 += "<img alt='' class='img-responsive' src=\""+jsonArray[i][1]+"\"/>";
	        	str1+="<figcaption>";
        		str1+="活動名稱:"+jsonArray[i][0].title+"<br>"+"活動地點:"+jsonArray[i][0].city+"<br>"+"活動時間:"+(Y+M+D)+"<br>"+"活動ID:"+jsonArray[i][0].id+"<br>"+"主揪:"+jsonArray[i][2]+"<br>";
        		str1+="</figcaption><figure></div>";
	        	$("#show_act_area").append(str1);
		        i++;
		        });
			}
		})//ajax end
		$('#header_nav ul li').click(function(){
			$(this).addClass('active').siblings().removeClass('active');
		})
		$('#upTop').click(function(){
			var body = $("html, body");
			body.stop().animate({scrollTop:0}, 500, 'swing');
			}).mouseover(function(){
				$(this).removeClass('animated');
			}).mouseout(function(){
				$(this).addClass('animated');
			})
		$('#CitySelect').change(function(){
			alert($(this).val().substr(7,3));
		})
		$('#start_date').change(function(){
			alert($(this).val());
		})
		$('#end_date').change(function(){
			alert($(this).val());
		})
		$('#type_select').change(function(){
			alert($(this).val());

		})
		$('#keyword_search_input').blur(function(){
			alert($(this).val());
		})
		$('#searchbarButton').change(function(){
	
		})
	})

</script>
<style>
</style>
</head>	
<body>
	<div class="banner">
		<div class="container">
			<nav class="navbar navbar-default">
				<div class="navbar-header">
				  <button type="button" class="
				   collapsed" data-toggle="collapse" data-target="#dropdown_munu" id="hum">
					<span><i class="fa fa-bars" aria-hidden="true"></i></span>
				  </button>
					<div class="logo">
						<h1><a class="navbar-brand" href="index.html">Wegther</a></h1>
					</div>
				</div>
				<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
					<nav class="header_nav" id="header_nav"> 
						<ul class="nav navbar-nav">
							<li class="active"><a href="#"><span data-hover="活動">活動</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="心得">心得</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="發起活動">發起活動</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="發起心得">發起心得</span></a></li>
							<li><a href="#" class="scroll" data-toggle="modal" data-target="#ActPageBox"><span data-hover="登入">登入</span></a></li>
						</ul>
					</nav>
				</div>
			</nav>
			<div class="banner-info">
				<div class="from-group">
						<form action="" ng-app="myApp" ng-controller="myCtrl">
								<div class="AreaCon">
								<label>地區 :</label>
								<select name="" id="CitySelect" ng-model="selectedName" ng-options="x for x in names">
									<option value="">--請選擇--</option>
									<script>
											var app = angular.module('myApp', []);
											app.controller('myCtrl', function($scope) {
												$scope.names = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
												,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖'];
											});
									</script>
								</select>
								<select name="" id="AreaSelect">
									<option value="">--請選擇--</option>
									<option value="">A</option>
									<option value="">B</option>
									<option value="">C</option>
									<option value="">D</option>
								</select>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動開始日期 :</span><br>
									<input type="date" class="form-control" id="start_date"><br>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動結束日期 :</span><br>
									<input type="date" class="form-control" id="end_date"><br>
								</div>
								<div class="AreaCon">
									<span>活動類型 :</span><br>
									<select class="form-control" id="type_select">
										<option value="運動">運動</option>
										<option value="休閒">休閒</option>
										<option value="音樂" >音樂</option>
										<option value="美食">美食</option>
										<option value="商業">商業</option>
										<option value="聊天">聊天</option>
									</select><br>
								</div>
								<div class="AreaCon" id="keyword_search">
									<span class="form-group">關鍵字搜尋 :</span><br>
									<input type="text" placeholder="搜尋-活動名稱" class="form-control" id="keyword_search_input"><br>
								</div>
								<div id="searchbarButton">	
									<input type="submit" value="搜尋" class="btn btn-warning form-control ">
									<input type="reset" value="清除" class="btn btn-warning form-control">
								</div>
							</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="ActPageBox" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-lg" role="document">
			  <div class="modal-content"><!--白色遮罩層-->
				  <div class="modal-body">
		  <!--       // modal-body  有差padding -->
					  <div class="modal-header">
						  
						  <h5 class="modal-title lead"><strong>請選擇登入方式</strong></h5>
						  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
						  </button>
					  </div>
					  <div class="modal-body" id="mid-body">
						  <div class="form-group" id="ACT">
							  <label for="recipient-name" class="col-form-label">帳號:</label>
							  <input type="text" class="form-control" id="account">
						  </div>
						  <div class="form-group" id="PWD">
							  <label for="recipient-name" class="col-form-label">密碼:</label>
							  <input type="text" class="form-control" id="password">
						  </div>
						  <div id="or" class="bg-primary text-white"><h4>or</h4></div>
						  <button type="button" class="btn big_use Google_i"><i class="fa fa-google" aria-hidden="true"></i>Google 登入</button>
						  <button type="button" class="btn big_use Fb_i"><i class="fa fa-facebook-official" aria-hidden="true"></i>FB 登入</button>
						  <div class="AreaCon">
						  <button type="button" class="btn small_use Google_i"><i class="fa fa-google" aria-hidden="true"></i></button>
						  <button type="button" class="btn small_use Fb_i"><i class="fa fa-facebook-official" aria-hidden="true"></i></button>
						  </div>
					  </div>
					  <div class="modal-footer">
						  <p class="small text-left" >還沒註冊嗎?趕緊註冊一個帳號吧! <a href="javascript:void(0)">點我註冊</a></p>
						  <button type="button" class="btn btn-primary">登入</button>
						  <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
					  </div>
				  </div>
			  </div>
			</div>
		  </div>
	<div class="about" id="about">
			<div class="container">
				<h1>活動</h1>
				
				<ul id="about_ActRank">
					<li><a href="">熱門</a></li>
					<li><a href="">時間</a></li>
					<li><a href="">地區</a></li>
				</ul>
					<div class="row  masonry" id="show_act_area">
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/01.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption>join us</figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/02.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption>that's go </figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/03.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption>party</figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/04.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/05.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/06.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/07.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/08.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/09.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/010.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/013.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/014.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/011.jpg" alt="" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 item"> -->
<!-- 							<figure> -->
<!-- 							<img src="images/012.jpg" alt="12" class="img-responsive"> -->
<!-- 							<figcaption></figcaption> -->
<!-- 							</figure> -->
<!-- 						</div> -->
					</div>
				</div>
			<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
			<script>
			$(function(){
				$('.masonry').masonry({
					itemSelector: '.item',
					})
			})
			</script>
	</div>
	<!-- <footer>
		<div class="container">
			<p id="fw">Wegther 2018</p>
		</div>
	</footer> -->
	<i  id="upTop" class="fa fa-chevron-circle-up animated infinite bounce" aria-hidden="true"></i>
</body>
</html>