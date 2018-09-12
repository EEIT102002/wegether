<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="org.apache.commons.codec.binary.StringUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ActivityPage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/activityPage.css">
<script src="js/activityPage.js"></script>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"/>
<!-- 登入使用 -->
<script src="/wegether/js/jquery.cookie.js" type="text/javascript"></script>
<script src="setting/js/setting.js"></script>
<script src="/wegether/js/noticeWebStocket.js" type="text/javascript"></script>
<script src="/wegether/js/logMethod.js" type="text/javascript"></script>
<!-- 登入使用 END -->
<!-- 留言 /心得心享 視窗  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- 留言 /心得心享 視窗 END -->
<script>
	$(function(){
		$('#header_nav ul li').click(function(){
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
	 var flag=0;
	document.addEventListener("DOMContentLoaded", function () {
              document.getElementById("favorButton").addEventListener("click",click); 
              });
    
	function click(){
		if(flag==0){
				document.getElementById("favorButton").src="images/activityPageImages/favorites.png";   
				flag=1;
		}else{
				document.getElementById("favorButton").src="images/activityPageImages/favoritesOff.png";   
				flag=0;
			}
          }
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
//activityPage 輪播使用
var picNo=1;
document.addEventListener("DOMContentLoaded", function () {  
     selectPic();                
     });
var timerObj=setInterval(autoPlay,1500);
 function autoPlay(){
     picNo++;  if(picNo>"${actPicList.size()}") picNo=1;                    
     selectPic();  
 }

 function selectPic(){
	     for(var i=1;i<="${actPicList.size()}";i++)    	 
	             document.getElementById("imd"+i).style="border:2px solid #FFBB00"; 
	     document.getElementById("imd"+picNo).style="border:3px solid red";     	
	 	document.getElementById("imd0").src= $("#imd"+picNo).attr("src");     
	}
//activityPage 輪播使用  end	
//留言、心得分享
  $( function() {
    $( "#tabs" ).tabs();
  } );
//留言、心得分享 end
</script>
<style>
    *{
        list-style: none;
        margin: 0;
        padding: 0;

    }
    body{
        /* background-color: rgb(145, 145, 145);
         */
         background: url(images/v6.jpg) no-repeat;
         background-size: cover;
         background-attachment: fixed;
    }
    #small_con{
        width: 100%;
        min-height: 800px;
        background-color: rgba(255, 255, 255, 0.363);
    }
    footer{
        margin-top: 20px;
    }
    footer ul{
        text-align: center;
        /* border: 2px solid red; */
        display: flex;
        width: 70%;
        margin: auto;

    }
    footer>ul>li{
        /* border: 2px solid green; */
        flex: 1;

    }
    footer>ul>li a{
        font-size: 1.2em;
    }
    footer>ul>li ul{
        display: flex;
        flex-direction: column;
        width: 80%;
        color: rgb(255, 153, 0);
    }
    .container p{
        color: white;
    }
   
</style>
</head>	
<body>
  <div class="container">
        <nav class="navbar navbar-default" id="stickytop">
            <div class="navbar-header">
                <button type="button" class="
				   collapsed" data-toggle="collapse" data-target="#dropdown_munu" id="hum">
                    <span>
                        <i class="fa fa-bars" aria-hidden="true"></i>
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
                        <li class="active">
                            <a href="#">
                                <span data-hover="活動">活動</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="scroll">
                                <span data-hover="心得">心得</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="scroll">
                                <span data-hover="發起活動">發起活動</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="scroll">
                                <span data-hover="發起心得">發起心得</span>
                            </a>
                        </li>
                        <li>
                            <a id="loginSpan" href="#" class="scrol" data-toggle="modal" data-target="#ActPageBox">
                                <span data-hover="登入">登入</span>
                            </a>
                            <a id="logoutSpan" href="#" class="scrol" style="display:none">
                            	<span data-hover="登出">登出</span>
                            </a>
                        </li>
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
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="mid-body">
                        <form id="loginform">
                            <div class="form-group" id="ACT">
                                <label for="recipient-name" class="col-form-label">帳號:</label>
                                <input type="text" class="form-control" id="account" name="account">
                            </div>
                            <div class="form-group" id="PWD">
                                <label for="recipient-name" class="col-form-label">密碼:</label>
                                <input type="password" class="form-control" id="pwd" name="pwd">
                            </div>
                            <button type="button" class="btn btn-primary" id="login">登入</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        </form>
                        <div id="or" class="bg-primary text-white">
                            <h4>or</h4>
                        </div>
                        <button type="button" class="btn big_use Google_i">
                            <i class="fa fa-google" aria-hidden="true"></i>Google 登入</button>
                        <button type="button" class="btn big_use Fb_i">
                            <i class="fa fa-facebook-official" aria-hidden="true"></i>FB 登入</button>
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
                        <p class="small text-left">還沒註冊嗎?趕緊註冊一個帳號吧!
                            <a href="javascript:void(0)">點我註冊</a>
                        </p>
                    
                    </div>
                </div>
            </div>
        </div>
    </div>
	<div class="container">
        <div id="small_con">
<!--       寫在這以下 -->
				<div id="core">
               <!-- 上面區塊 -->
                    <div id="up" >
                     <!-- 上左區塊 -->
                        <div id="left" >
                                
                            <div id="up" >
                                <div id="left" style="width: auto;">
									<a href="personal.controller?memberId=${hostBean.id}"><img src="data:image/jpg;base64,${hostPicList.get(0)}"  class="img-circle" width="70" > </a>
								</div>
                                <div  id="left" style="width: auto;">
                                    <p id="txtup" style="background-color:#FFBB73" >${hostBean.nickname}</p>
                                    <p id="txtup" >${hostBean.job}</p>
                                </div>   	
                                <div id="right" >                  
									<a href="#" class="tooltip-test" data-toggle="tooltip" title="推薦給好友"> 
									<img src="images/activityPageImages/invite.png" width="50" ></a>&emsp; 
					                <span  class="tooltip-test" data-toggle="tooltip" title="活動點閱率">
                                    <img src="images/activityPageImages/click2.png" width="50" > </span>${actBean.click}&emsp; 
                                    <a  href="#" class="tooltip-test" data-toggle="tooltip" title="收藏活動資訊">
                             		 <img id="favorButton"  src="images/activityPageImages/favoritesOff.png" width="50" ></a>
                             	</div>                            
                            </div>
                    
					 <!-- 	照片輪播 -->				   
						<div class="content">
						    	<div class="div1">                             
						        		<img id="imd0" src="data:image/jpg;base64,${actPicList.get(0)}"  class="img-thumbnail"  >
						        </div>
						        <div class="div2">
										<c:set var="salary"  value="1"/>		
										<c:forEach var="obj" items="${actPicList}"> 
									 			<img id="imd${salary}" src="data:image/jpg;base64,${obj}"   class="img-thumbnail" >
									 				<c:set var="salary"  value="${salary+1}"/>
										</c:forEach>
								</div>
						    </div>
  
	   	 			<!-- 	照片輪播  end-->		
	   
  
 </div>  
 <!-- 上左區塊 end -->

                         <!-- 上右區塊  -->
                        <div id="right">
                            <h3>${actBean.title}</h3><!-- right1 end -->
                            <p>${actbegin}</p><!-- right2 end -->
                            <p>${actBean.addr}</p><!-- right3 end -->
                            <div>
                                    <img src="images/activityPageImages/people.png" width="20" >&nbsp;${actBean.numberlimit}人 &emsp;
                                    <img src="images/activityPageImages/fees.png" width="20" >&nbsp;${actBean.feed}元 &emsp;
                                    <img src="images/activityPageImages/deadline.png" width="40" >&nbsp;${dateline} 
                            </div><!-- right4 end -->
                            <div>
                            	<c:forEach var="obj" items="${memPicList}">
							 		<a href="personal.controller?memberId=${obj.memberId}"><img  src="data:image/jpg;base64,${obj.memberPic}" class="img-circle" width="50" ></a> &emsp; 
								</c:forEach>
                           

                            </div><!-- right5 end -->
                            <p style="text-align: center" >${attedNumber} </p><!-- right6 end -->
                            <div style="text-align: center">

                            <button type="button" class="btn btn-warning"  > <a href="<c:url value="/actEdit.getBean.controller?actid=71"/>"> &emsp; 編  &emsp;  輯  &emsp; 活  &emsp; 動 &emsp;</a></button>

                            </div><!-- right7 end -->
                            
                        </div> 
					 <!-- 上右區塊 end -->
					 
                   </div>   
                   <!-- 上面區 end -->
 				 <!-- 下面區塊 -->
                     <div id="down">
                          <h2>${actBean.title}</h2>
                          <p > ${actBean.content} </p>
                          </br>
                          
                          
                          <h4>留下意見:</h4>
                          <textarea cols="50" rows="2">對聚會有任何疑問嘛？留個言吧！</textarea>    </br> </br>   
                          <button type="button" class="btn btn-warning"  >  &emsp; 留  &emsp;  &emsp; 言 &emsp;</button>
                             <button type="button" class="btn btn-warning"  >  &emsp; 心&emsp;  得  &emsp; 分&emsp;享 &emsp;</button> </br> </br>     
                            
						<!--  留言、心得分享 -->
                            <div id="tabs">
								  <ul>
								    <li><a href="#tabs-1">Nunc tincidunt</a></li>
								    <li><a href="#tabs-2">Proin dolor</a></li>
								  </ul>
								  <div id="tabs-1">
								    <p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
								  </div>
								  <div id="tabs-2">
								    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
								  </div>
								</div>
                            <!--  留言、心得分享  end-->
                          <!-- msg begin -->                 
       				   <c:forEach var="obj" items="${msgsList}">
                        <div id="msgid" class="well" >
							 	<a href="personal.controller?memberId=${obj.memberId}"><img  src="data:image/jpg;base64,${obj.picMem}"  width="50" ></a> 	
		                       <span style="color:blue;">${obj.nickname} </span> &emsp; <span style="font-size:small;">${obj.msgtime}</span></br>
		                        ${obj.content}</br>
                        </div>
						</c:forEach>
                         <!-- msg end -->
                         
                    </div> 
                   <!-- 下面區塊  end-->
                    
                </div>
                <!--       寫在這以上 -->
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
                    <li>
                        <a href="" title="">關於Wegether</a>
                        <ul>
                            <li>-我們的出發</li>
                            <li>-我們的團隊</li>
                            <li>-我們的服務</li>
                        </ul>
                    </li>
                    <li>
                        <a href="" title="">聯絡我們</a>
                        <ul>
                                <li>-聯絡我們</li>
                        </ul>
                    </li>
            </ul>
            <p class="text-center">- Wegether 2018 EEIT10202 -</p>
    </footer>
</body>
</html>