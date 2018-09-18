<%-- <%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <title>Home</title> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
<!-- <link rel="stylesheet"  href="css/bootstrap.css" /> -->
<!-- <link rel="stylesheet"  href="css/style.css"/> -->
<!-- <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script> -->
<!-- <link href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'> -->
<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'> -->
<!-- <script src="js/bootstrap.js"></script> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"/> -->
<!-- <script type="text/javascript" src="js/index.js"></script> -->
<!-- <script src="js/jquery.cookie.js" type="text/javascript"></script> -->
<!-- <script src="js/indexlogin.js"></script> -->
<!-- <script src="js/noticeWebStocket.js" type="text/javascript"></script> -->
<!-- <script src="js/logMethod.js" type="text/javascript"></script> -->
<!-- <style> -->
/*  	#form_po{ */
/*  		display: none; */
/*  	} */
/* 	#search_for_act , #search_for_po{ */
/* /* 		background-color: yellow; */ */
/* 		width: 100px; */
/* 		height: 50px; */
/* 		line-height: 50px; */
/* 		text-align: center; */
/* 		cursor: pointer; */
/* 		letter-spacing: 5px; */
/* 	} */
/* 	#search_for_act{ */
/* 		color: #FFB445; */
/* 	} */
/* 	#search_for_po{ */
/* 		color: white; */
/* 	} */
/* 	#search_for_act:hover , #search_for_po:hover{  */
/* 		background-color: rgba(255, 255, 255, 0.46); */
/* 	} */

	
	
/* 	#dropdownMenuButton{ */
/* 		display: flex; */
/* 		cursor: pointer; */
/* /*   		border: 2px solid green;   */ */
/* 		width: 160px; */
/* 		height:50px; */
		
/* 	} */
/* 	#dropdownMenuButton img{ */
/* 		flex:0.3; */
/* 		margin-left: 15px; */
/* 	} */
/* 	#dropdownMenuButton #userinfo{ */
/* /* 	border:2px solid;   */ */
/* 		flex:0.7; */
/* 		text-align: center; */
/* 	} */
/* 	.header_nav li{ */
/* /*  		border:2px solid blue;  */ */
/* 		height: 50px; */
/* 		line-height: 50px */
/* 	} */
/* 	.header_nav a span{ */
/* /* 		border:2px dotted; */ */
/* 		height: 50px; */
/* 		line-height: 50px; */
/* 	} */
/* 	#userinfo{ */
/* 		color:white; */
/* 	} */
/* 	.dropdown-menu> a{ */
/* 		width: 80%; */
/* 		height: 42px; */
/* 		display:block; */
/* /* 		border: 2px solid; */ */
/* 		margin: auto; */
/* 		text-align: center; */
	
/* 	} */
/* 	.dropdown-menu> a:hover{ */
/* 		background-color: #ccc; */
/* 	} */
/* 	.dropdown-menu> a , .dropdown-menu> a:hover , .dropdown-menu> a :active,.dropdown-menu> a :checked { */
/* 	text-decoration: none; */
/* 	list-style: none; */
/* 	color:black; */
/* } */
/* 	#liRing i{ */
/* 		font-size:1.5em; */
/* 		color:white; */
/* /*  		border:2px solid yellow;  */ */
/* /* 		background-color:green; */ */
/* 		line-height:50px; */
/* 		height: 50px; */
/* 		cursor: pointer; */
/* 		margin-right: 20px; */
/* 	} */
/* 	#supremind{ */
/* /*  	border:2px solid green;  */ */
/* 	font-size:0.4em; */
/* 	border-radius:50%; */
/* 	background-color: red; */
/* 	color:white; */
/* 	font-weight: bold; */
/* 	width: 30px; */
/* 	height:20px; */
/* 	line-height: 20px; */
/* 	text-align: center; */
/* 	position:relative; */
/* 	top:-42px; */
/* 	left:15px; */
/* 	z-index:9999999999; */

/* 	} */
/* 	.hidden-input { */
/*     opacity: 0; */
/*     position: absolute; */
/*     z-index: -1; */
/* 	} */
/* 	input[type=checkbox]+span { */
/*     display: inline-block; */
/*     height: 2em; */
/*     width: 2em; */
/*     border-radius: 4px; */
/*     background-color: gray;         */
/* 	} */

/* 	input[type=checkbox]:checked+span { */
/* 	    background-color: #FFB445; */
/* 	} */
/* 	.labelflex{ */
/* 		display: flex; */
/* 		width:5em; */
/* 	} */
/* 	.labelflex p{ */
/* 		position:relative; */
/* 		top:12px; */
/* 		left:2px; */
/* 	} */
/*  	#labelflexCon{ */
/*  		margin-top: -14px; */
/*  		margin-left: -40px;  */
/* 		display: flex; */
/*  	} */
/*  	#contentRemind{ */
/*  		min-width: 320px; */
/*  		position: absolute; */
/*  		left:-260px; */
/*  	} */
<!-- </style> -->
<!-- </head>	 -->
<!-- <body> -->
<!-- 	<div class="banner"> -->
<!-- 		<div class="container"> -->
<!-- 			<nav class="navbar navbar-default"> -->
<!-- 				<div class="navbar-header"> -->
<!-- 				  <button type="button" class=" -->
<!-- 				   collapsed" data-toggle="collapse" data-target="#dropdown_munu" id="hum"> -->
<!-- 					<span><i class="fa fa-bars" aria-hidden="true"></i></span> -->
<!-- 				  </button> -->
<!-- 					<div class="logo"> -->
<!-- 						<h1><a class="navbar-brand" href="index2.jsp">Wegether</a></h1> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="collapse navbar-collapse nav-wil" id="dropdown_munu"> -->
<!-- 					<nav class="header_nav">  -->
<!-- 						<ul class="nav navbar-nav"> -->
<!-- 							<li class="active"><a href="#"><span data-hover="活動">活動</span></a></li> -->
<!-- <!-- 							<li><a href="#" class="scroll"><span data-hover="活動地圖">活動地圖</span></a></li> --> -->
<!-- 							<li><a href="#" class="scroll"><span data-hover="發起活動">發起活動</span></a></li> -->
<!-- 							<li><a href="#" class="scroll"><span data-hover="發起心得">發起心得</span></a></li> -->
<!-- 							<li id="loginSpan"><a  href="#" class="scroll" data-toggle="modal" data-target="#ActPageBox"><span data-hover="登入">登入</span></a></li> -->
<!-- 							<li id="liRing" style="display:none;"> -->
<!-- 								<div class="dropdown"> -->
<!-- 								<div class="dropdown-toggle" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 								<i class="fa fa-bell" aria-hidden="true"><div id="supremind">999</div></i> -->
<!-- 								</div> -->
<!-- 								<div class="dropdown-menu" id="contentRemind" aria-labelledby="dropdownMenuButton2"> -->
								
<!-- 										<a class="dropdown-item">1</a> -->
<!-- 										<a class="dropdown-item">2</a> -->
<!-- 										<a class="dropdown-item">3</a> -->
<!-- 										<a class="dropdown-item">4</a> -->
<!-- 										<a class="dropdown-item">5</a> -->
<!-- 										<a class="dropdown-item">6</a> -->
<!-- 										<a class="dropdown-item">閱讀更多</a> -->
									
<!-- 								</div>		 -->
<!-- 							</li> -->
<!-- 							<li id="logoutSpan" style="display:none;"> -->
<!-- 							<div class="dropdown"> -->
<!-- 								<div class="dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 									<img src='images/01.jpg' alt=''class='img-circle' style='width:50px;height: 50px'>  -->
									
<!-- 									<div id="userinfo">XXX</div> -->
<!-- 								</div> -->
<!-- 								<div class="dropdown-menu" id="membergroup"  aria-labelledby="dropdownMenuButton"> -->
<!-- 								    <a class="dropdown-item" href="#">會員資料</a> -->
<!-- 								    <a class="dropdown-item" href="#">好友名單</a> -->
<!-- 								    <a class="dropdown-item" href="#">個人設定</a> -->
<!-- 								    <a class="dropdown-item" href="#">我的活動</a> -->
<!-- 								    <a class="dropdown-item" href="#">我的心得</a> -->
<!-- 								    <a class="dropdown-item" id="logoutSubSpanA" class="scrol dropdown-item" href="#">登出</a> -->
<!-- 								 </div> -->
<!-- 							</div> -->
<!--                             </li> -->
<!-- 						</ul> -->
<!-- 					</nav> -->
<!-- 				</div> -->
<!-- 			</nav> -->
<!-- 			<div class="banner-info"> -->
<!-- 				<div class="from-group"> -->
<!-- 					<div class="AreaCon"> -->
<!-- 						<div id="search_for_act">活動</div> -->
<!-- 						<div id="search_for_po">心得</div> -->
<!-- 					</div> -->
<!-- 						<form id="form_act"> -->
<!-- 								<input type="hidden" value="0" name="state"/> -->
<!-- 								<div class="AreaCon"> -->
<!-- 								<label>地區 :</label> -->
<!-- 								<select name="cityselect_name" id="CitySelect"> -->
<!-- 									<option value="">--請選擇--</option> -->
<!-- 								</select> -->
<!-- 								</div> -->
<!-- 								<div class="AreaCon"> -->
<!-- 									<span class="form-group">活動開始日期 :</span><br> -->
<!-- 									<input type="date" class="form-control" id="start_date" name="start_date_name"><br> -->
<!-- 								</div> -->
<!-- 								<div class="AreaCon"> -->
<!-- 									<span class="form-group">活動結束日期 :</span><br> -->
<!-- 									<input type="date" class="form-control" id="end_date" name="end_date_name"><br> -->
<!-- 								</div> -->
<!-- 								<div class="AreaCon" id="typeArea"> -->
<!-- 									<span>活動類型 :</span><br> -->
<!-- 									<div id="labelflexCon"> -->
<!-- 									<label class="labelflex"> -->
<!-- 										<input type="checkbox" value="運動" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>運動</p></input> -->
<!-- 									</label > -->
<!-- 									<label class="labelflex"> -->
<!-- 										<input type="checkbox" value="休閒" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>休閒</p></input> -->
<!-- 									</label> -->
<!-- 									<label class="labelflex"> -->
<!-- 										<input type="checkbox" value="音樂" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>音樂</p></input> -->
<!-- 									</label> -->
<!-- 									<label class="labelflex"> -->
<!-- 										<input type="checkbox" value="美食" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>美食</p></input> -->
<!-- 									</label> -->
<!-- 									<label class="labelflex"> -->
<!-- 										<input type="checkbox" value="聊天" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>聊天</p></input>				 -->
<!-- 									</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="AreaCon" id="keyword_search"> -->
<!-- 									<span class="form-group">關鍵字搜尋 :</span><br> -->
<!-- 									<input type="text" placeholder="搜尋-活動名稱" class="form-control" id="keyword_search_input" name="keyword_search_input_name"><br> -->
<!-- 								</div> -->
<!-- 								<div id="searchbarButton">	 -->
<!-- 									<input id="select_act_submit" type="submit" value="搜尋" class="btn btn-warning form-control"> -->
<!-- 									<input type="reset" value="清除" class="btn btn-warning form-control"> -->
<!-- 								</div> -->
<!-- 							</form> -->
							
<!-- 						<form id="form_po" > -->
<!-- 							<div class="AreaCon"> -->
<!-- 								<label>地區 :</label> -->
<!-- 								<select name="cityselect_name_po" id="CitySelect_po" > -->
<!-- 									<option value="">--請選擇--</option> -->
<!-- 								</select> -->
<!-- 								</div> -->
<!-- 								<div class="AreaCon"> -->
<!-- 									<span class="form-group">活動開始日期 :</span><br> -->
<!-- 									<input type="date" class="form-control" id="start_date_po" name="start_date_name_po"><br> -->
<!-- 								</div> -->
<!-- 								<div class="AreaCon"> -->
<!-- 									<span class="form-group">活動結束日期 :</span><br> -->
<!-- 									<input type="date" class="form-control" id="end_date_po" name="end_date_name_po"><br> -->
<!-- 								</div> -->
<!-- 								<div class="AreaCon"> -->
<!-- 									<span>活動類型 :</span><br> -->
<!-- 									<input type="checkbox" value="運動" name="type_select_name_po">運動</input> -->
<!-- 									<input type="checkbox" value="休閒" name="type_select_name_po">休閒</input> -->
<!-- 									<input type="checkbox" value="音樂" name="type_select_name_po">音樂</input> -->
<!-- 									<input type="checkbox" value="美食" name="type_select_name_po">美食</input> -->
<!-- 									<input type="checkbox" value="聊天" name="type_select_name_po">聊天</input>				 -->
<!-- 								</div> -->
						
<!-- 							<input type="hidden" value="1" name="state_po" /> -->
<!-- 							<input type="search" name="keyword_search_input_name_po" id="keyword_search_input_po"/> -->
<!-- 							<input id="select_po_submit" type="submit" value="搜尋"/> -->
							
<!-- 							<input type="reset" value="清除"/> -->
<!-- 						</form> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	    <div class="modal fade" id="ActPageBox" tabindex="-1" role="dialog"> -->
<!--         <div class="modal-dialog modal-lg" role="document"> -->
<!--             <div class="modal-content"> -->
<!--                 白色遮罩層 -->
<!--                 <div class="modal-body"> -->
<!--                           // modal-body  有差padding -->
<!--                     <div class="modal-header"> -->

<!--                         <h5 class="modal-title lead"> -->
<!--                             <strong>請選擇登入方式</strong> -->
<!--                         </h5> -->
<!--                         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--                             <span aria-hidden="true">&times;</span> -->
<!--                         </button> -->
<!--                     </div> -->
<!--                     <div class="modal-body" id="mid-body"> -->
<!--                         <form id="loginform"> -->
<!--                         	<p class="loginerror" ></p> -->
<!--                             <div class="form-group" id="ACT"> -->
<!--                                 <label for="recipient-name" class="col-form-label">帳號:</label> -->
<!--                                 <input type="text" class="form-control" id="account" name="account"> -->
<!--                             </div> -->
<!--                             <div class="form-group" id="PWD"> -->
<!--                                 <label for="recipient-name" class="col-form-label">密碼:</label> -->
<!--                                 <input type="password" class="form-control" id="pwd" name="pwd"> -->
<!--                             </div> -->
<!--                             <button type="button" class="btn btn-primary" id="login">登入</button> -->
<!--                             <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button> -->
<!--                         </form> -->
<!--                         <div id="or" class="bg-primary text-white"> -->
<!--                             <h4>or</h4> -->
<!--                         </div> -->
<!--                         <button type="button" class="btn big_use Google_i"> -->
<!--                             <i class="fa fa-google" aria-hidden="true"></i>Google 登入</button> -->
<!--                         <button type="button" class="btn big_use Fb_i"> -->
<!--                             <i class="fa fa-facebook-official" aria-hidden="true"></i>FB 登入</button> -->
<!--                         <div class="AreaCon"> -->
<!--                             <button type="button" class="btn small_use Google_i"> -->
<!--                                 <i class="fa fa-google" aria-hidden="true"></i> -->
<!--                             </button> -->
<!--                             <button type="button" class="btn small_use Fb_i"> -->
<!--                                 <i class="fa fa-facebook-official" aria-hidden="true"></i> -->
<!--                             </button> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="modal-footer"> -->
<!--                         <p class="small text-left">還沒註冊嗎?趕緊註冊一個帳號吧! -->
<!--                             <a href="javascript:void(0)">點我註冊</a> -->
<!--                         </p> -->

<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!--     <div class="modal fade" id="searchBox" tabindex="-1" role="dialog"> -->
<!--         <div class="modal-dialog modal-lg" role="document"> -->
<!--             <div class="modal-content"> -->
<!--                 白色遮罩層 -->
<!--                 <div class="modal-body"> -->
<!--                           // modal-body  有差padding -->
<!--                     <div class="modal-header"> -->
<!--                         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--                             <span aria-hidden="true">&times;</span> -->
<!--                         </button> -->
<!--                     </div> -->
<!--                     <div class="modal-body" id="mid-body"> -->
<!--                     </div> -->
<!--                     <div class="modal-footer"> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- 	<div class="about" id="about"> -->
<!-- 			<div class="container"> -->
<!-- 				<h1>活動</h1> -->
				
<!-- 				<ul id="about_ActRank"> -->
<!-- 					<li><a href="">熱門</a></li> -->
<!-- 					<li><a href="">時間</a></li> -->
<!-- 					<li><a href="">地區</a></li> -->
<!-- 				</ul> -->
<!-- 					<div class="row  masonry" id="show_act_area"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script> -->
<!-- 			<script> -->
// 			$(function(){
// 				$('.masonry').masonry({
// 					itemSelector: '.item',
// 					})
// 			})
<!-- 			</script> -->
<!-- 	</div> -->
<!-- 	<!-- <footer> -->
<!-- 		<div class="container"> -->
<!-- 			<p id="fw">Wegther 2018</p> -->
<!-- 		</div> -->
<!-- 	</footer> --> -->
<!-- 	<i  id="upTop" class="fa fa-chevron-circle-up animated infinite bounce" aria-hidden="true"></i> -->
<%-- 	<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" /> --%>
<!-- </body> -->
<!-- </html> -->