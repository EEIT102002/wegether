<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="org.apache.commons.codec.binary.StringUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/activityPage.css">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"/>

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
					<span><i class="fa fa-bars" aria-hidden="true"></i></span>
				  </button>
					<div class="logo">
						<h1><a class="navbar-brand" href="index.html">Wegether</a></h1>
					</div>
				</div>
				<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
					<nav class="header_nav" id="header_nav"> 
						<ul class="nav navbar-nav">
							<li class="active"><a href="#"><span data-hover="活動">活動</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="心得">心得</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="發起活動">發起活動</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="發起心得">發起心得</span></a></li>
							<li><a href="#" class="scrol" data-toggle="modal" data-target="#ActPageBox"><span data-hover="登入">登入</span></a></li>
						</ul>
					</nav>
				</div>
			</nav>
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
	<div class="container">
        <div id="small_con">
<!--       寫在這以下 -->
				<div id="core">
            
                    <div id="up" >
                        <div id="left" >
                                
                            <div id="up" >
                                <div id="left" style="width: auto;">
                                        <a href="#"><img src="data:image/jpg;base64,${hostPicList.get(0)}"  width="70" > </a>
                                </div>
                                <div  id="left" style="width: auto;">
                                    <p id="txtup" style="background-color:#FFBB73" >${hostBean.nickname}</p>
                                    <p id="txtup" >${hostBean.job}</p>
                                </div>   
                                

                                   
                                  
								
								
                                <div id="right" >                  
					                <a href="activityPage.controller?actid=1"> <img src="images/activityPageImages/invite.png" width="50" ></a>&emsp; 
                                    <img src="images/activityPageImages/click2.png" width="50" > ${actBean.click}
                                   
                                </div>                            
                            </div>
                            <div>
                               <img src="data:image/jpg;base64,${actPicList.get(0)}"  width="400" > 
                            </div>
                            <div style="padding-top: 10px;">
                            
                             	<c:forEach var="obj" items="${actPicList}">
							 		<a href="#"><img  src="data:image/jpg;base64,${obj}"  width="100"  height="80"></a>
								</c:forEach>
                                   
                            </div>
                        </div>  <!-- left end -->

                        
                        <div id="right">
                            <h3>${actBean.title}</h3><!-- right1 end -->
                            <p>${actBean.actbegin}</p><!-- right2 end -->
                            <p>${actBean.addr}</p><!-- right3 end -->
                            <div>
                                    <img src="images/activityPageImages/people.png" width="20" > ${actBean.numberlimit}人 &emsp;
                                    <img src="images/activityPageImages/fees.png" width="20" >${actBean.feed}元 &emsp;
                                    <img src="images/activityPageImages/deadline.png" width="40" >${actBean.dateline} 
                            </div><!-- right4 end -->
                            <div>
                            	<c:forEach var="obj" items="${memPicList}">
							 		<a href="#"><img  src="data:image/jpg;base64,${obj}"  width="50" ></a> &emsp; 
								</c:forEach>
                           

                            </div><!-- right5 end -->
                            <p style="text-align: center" >${attedNumber} </p><!-- right6 end -->
                            <div style="text-align: center">
                                <input   type="button" value="      報名     " >
                            </div><!-- right7 end -->
                            
                        </div>  <!-- right end -->
                   </div>   <!-- up end -->


                     <div id="down">
                            <h2>${actBean.title}</h2>
                            <p > ${actBean.content} </p>
                            <textarea cols="50" rows="2">對聚會有任何疑問嘛？留個言吧！</textarea>                       
                            <div id="msgid">
                               <img src="images/activityPageImages/msgs.png" width="600" >
                             </div>
                    </div> <!-- down end -->
                    
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