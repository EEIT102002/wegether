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
<script type="text/javascript" src="js/batch.js"></script>
<script>
	$(function(){
		var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
			,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖']			
		for(var i = 1; i <= array_for_city.length; i++) {
//	            console.log(array_for_city[i-1]);
			var x1 ="<option value="+i+">"+array_for_city[i-1]+"</option>"		
			$('#CitySelect').append(x1);
		}
		$.ajax("${pageContext.request.contextPath}/activity",{
			method:"GET",
			success:function(jsonArray){
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
		var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
			,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖']			
		for(var i = 1; i <= array_for_city.length; i++) {
//	            console.log(array_for_city[i-1]);
			var x1 ="<option value="+i+">"+array_for_city[i-1]+"</option>"		
			$('#CitySelect').append(x1);
		}
		
		$('#header_nav ul li').click(function(){
			$(this).addClass('active').siblings().removeClass('active');
		})

		$('#CitySelect').change(function(){
// 			alert($(this).val().substr(7,3));
			var x = $('#CitySelect').find('option:selected').index();
// 			alert(x);
			var y =$('#CitySelect').find('option:selected').attr("value",x);
			alert("此選項的VAL:"+$('#CitySelect').find('option:selected').val());
			})

		$('#form_act').submit(function(){
// 			alert('A');
			//ajax 待定
			$.ajax("${pageContext.request.contextPath}/select_activity",{
				method:"GET",
				data:$('#form_act').serialize(),
				success:function(jsonArray){
// 					alert("Act")
					console.log(jsonArray[0]);
					if(jsonArray[0]=="查無符合資料"){
// 						alert("查無符合資料");
						var str2 ="<div class='col-xs-12 col-sm-6 col-md-4 item'><figure><img alt='' class='img-responsive'src='images/noresult.jpg'/><figcaption>";
						str2 += jsonArray[0]+"</figcaption></figure></div>";
						$("#show_act_area").html(str2);
					}else{
						var i=0;
						$("#show_act_area").empty();
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
					        i++;
					        $("#show_act_area").append(str1);
					        });//each end
					}//else end
				}//success end
			})//ajax end
			return false;
		})//#form_act 提交

		$('#search_for_act').click(function(){
			$('#form_po').css({
				"display":"none"
			})
			$('#form_act').css({
				"display":"block"
			})
		})
		$('#search_for_po').click(function(){
			var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
				,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖']			
			for(var i = 1; i <= array_for_city.length; i++) {
// 	            console.log(array_for_city[i-1]);
				var x1 ="<option value="+i+">"+array_for_city[i-1]+"</option>"		
				$('#CitySelect_po').append(x1);
			}
			$('#form_po').css({
				"display":"block"
			})
			$('#form_act').css({
				"display":"none"
			})
		})
		
		$('#form_po').submit(function(){
			$.ajax("${pageContext.request.contextPath}/select_po",{
				method:"GET",
				data:	$('#form_po').serialize(),
				success:function(jsonArray){
					if(jsonArray[0]=="查無符合資料"){
// 						alert("查無符合資料");
						var str2 ="<div class='col-xs-12 col-sm-6 col-md-4 item'><figure><img alt='' class='img-responsive'src='images/noresult.jpg'/><figcaption>";
						str2 += jsonArray[0]+"</figcaption></figure></div>";
						$("#show_act_area").html(str2);
					}else{
						var i=0;
						$("#show_act_area").empty();
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
					        i++;
					        $("#show_act_area").append(str1);
					        });//each end
					}//else end
				}//success end
			})//ajax end
			return false;
		})//form_po end
		$('#upTop').click(function(){
			var body = $("html, body");
			body.stop().animate({scrollTop:0}, 500, 'swing');
			}).mouseover(function(){
				$(this).removeClass('animated');
			}).mouseout(function(){
				$(this).addClass('animated');
			})
			//日期限制
			$('#start_date').change(function() {
				var startTimeV = $('#start_date').val();
				var endTimeV = $('#end_date').val();
				$('#end_date').attr("min", startTimeV);
				$('#end_date').attr('max', '2200-08-31');
			 })
			$('#end_date').change(function() {
				var startTimeV = $('#start_date').val();
				var endTimeV = $('#end_date').val();
				$('#start_date').attr("max", endTimeV);
				$('#start_date').attr('min', '2000-08-31');
					  })
	})// script end
</script>
<style>
 	#form_po{
 		display: none;
 	}
	#search_for_act{
		background-color: yellow;
		width: 200px;
		height: 50px;
	}
	#search_for_po{
		background-color: green;
		width: 200px;
		height: 50px;
	}
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
						<h1><a class="navbar-brand" href="/index.jsp">Wegther</a></h1>
					</div>
				</div>
				<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
					<nav class="header_nav" id="header_nav"> 
						<ul class="nav navbar-nav">
							<li class="active"><a href="#"><span data-hover="活動">活動</span></a></li>
<!-- 							<li><a href="#" class="scroll"><span data-hover="活動地圖">活動地圖</span></a></li> -->
							<li><a href="#" class="scroll"><span data-hover="發起活動">發起活動</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="發起心得">發起心得</span></a></li>
							<li><a href="#" class="scroll" data-toggle="modal" data-target="#ActPageBox"><span data-hover="登入">登入</span></a></li>
						</ul>
					</nav>
				</div>
			</nav>
			<div class="banner-info">
				<div class="from-group">
						<div id="search_for_act">活動</div>
						<div id="search_for_po">心得</div>
						<form id="form_act">
								<input type="hidden" value="0" name="state"/>
								<div class="AreaCon">
								<label>地區 :</label>
								<select name="cityselect_name" id="CitySelect">
									<option value="">--請選擇--</option>
								</select>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動開始日期 :</span><br>
									<input type="date" class="form-control" id="start_date" name="start_date_name"><br>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動結束日期 :</span><br>
									<input type="date" class="form-control" id="end_date" name="end_date_name"><br>
								</div>
								<div class="AreaCon">
									<span>活動類型 :</span><br>
									<input type="checkbox" value="運動" name="type_select_name">運動</input>
									<input type="checkbox" value="休閒" name="type_select_name">休閒</input>
									<input type="checkbox" value="音樂" name="type_select_name">音樂</input>
									<input type="checkbox" value="美食" name="type_select_name">美食</input>
									<input type="checkbox" value="聊天" name="type_select_name">聊天</input>				
								</div>
								<div class="AreaCon" id="keyword_search">
									<span class="form-group">關鍵字搜尋 :</span><br>
									<input type="text" placeholder="搜尋-活動名稱" class="form-control" id="keyword_search_input" name="keyword_search_input_name"><br>
								</div>
								<div id="searchbarButton">	
									<input id="select_act_submit" type="submit" value="搜尋" class="btn btn-warning form-control">
									<input type="reset" value="清除" class="btn btn-warning form-control">
								</div>
							</form>
							
						<form id="form_po" >
							<div class="AreaCon">
								<label>地區 :</label>
								<select name="cityselect_name_po" id="CitySelect_po" >
									<option value="">--請選擇--</option>
								</select>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動開始日期 :</span><br>
									<input type="date" class="form-control" id="start_date_po" name="start_date_name_po"><br>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動結束日期 :</span><br>
									<input type="date" class="form-control" id="end_date_po" name="end_date_name_po"><br>
								</div>
								<div class="AreaCon">
									<span>活動類型 :</span><br>
									<input type="checkbox" value="運動" name="type_select_name_po">運動</input>
									<input type="checkbox" value="休閒" name="type_select_name_po">休閒</input>
									<input type="checkbox" value="音樂" name="type_select_name_po">音樂</input>
									<input type="checkbox" value="美食" name="type_select_name_po">美食</input>
									<input type="checkbox" value="聊天" name="type_select_name_po">聊天</input>				
								</div>
						
							<input type="hidden" value="1" name="state_po" />
							<input type="search" name="keyword_search_input_name_po" id="keyword_search_input_po"/>
							<input id="select_po_submit" type="submit" value="搜尋"/>
							
							<input type="reset" value="清除"/>
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