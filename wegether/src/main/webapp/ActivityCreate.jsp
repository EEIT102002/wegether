<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"/>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.5/angular.min.js"></script>
<link rel="stylesheet" href="css/jquery.timepicker.min.css"/>
<script src="js/jquery.timepicker.min.js"></script>
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
 	$(function(){
 				var now = new Date();
 				if((now.getMonth()+1)<10 && now.getDate()<10){
 					var min = now.getFullYear()+'-0'+(now.getMonth()+1)+'-0'+now.getDate();
 	 				$('#startTime').attr('min',min);
 	 				$('#deathLine').attr('min',min);
 				}
 				if((now.getMonth()+1)<10 && now.getDate()>10){
 					var min = now.getFullYear()+'-0'+(now.getMonth()+1)+'-'+now.getDate();
 	 				$('#startTime').attr('min',min);
 	 				$('#deathLine').attr('min',min);
 				}
 				if((now.getMonth()+1)>10 && now.getDate()<10){
 					var min = now.getFullYear()+'-'+(now.getMonth()+1)+'-0'+now.getDate();
 	 				$('#startTime').attr('min',min);
 	 				$('#deathLine').attr('min',min);
 				}
 				if((now.getMonth()+1)>10 && now.getDate()>10){
 					var min = now.getFullYear()+'-'+(now.getMonth()+1)+'-'+now.getDate();
 	 				$('#startTime').attr('min',min);
 	 				$('#deathLine').attr('min',min);
 				}
 				
                $('#startTime').change(function(){
                    var startTimeV = $('#startTime').val();
                    var endTimeV = $('#endTime').val(); 
                    $('#endTime').attr("min",startTimeV);
                    $('#endTime').attr('max','2200-08-31');
                    
                  	var v1 = startTimeV.substring(0,4);
                    var v2 = startTimeV.substring(5,7);
                    var v3 = (startTimeV.substring(8,10)-1);
                    
                    if(v3==0){
                    	v2 = v2-1;
                    	var d = new Date(v1,v2,v3);
                        if(d.getDate()<10 && (d.getMonth()+1)>10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-'+(d.getMonth()+1)+'-0'+d.getDate())
                        else if(d.getDate()>10 && (d.getMonth()+1)<10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-0'+(d.getMonth()+1)+'-'+d.getDate())
                        else if(d.getDate()<10 && (d.getMonth()+1)<10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-0'+(d.getMonth()+1)+'-0'+d.getDate())
                        else  if(d.getDate()>10 && (d.getMonth()+1)>10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate())
                    }
                    else{
                    	var d = new Date(v1,v2,v3);
                        if(d.getDate()<10 && d.getMonth()>10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-'+d.getMonth()+'-0'+d.getDate())
                        else if(d.getDate()>10 && d.getMonth()<10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-0'+d.getMonth()+'-'+d.getDate())
                        else if(d.getDate()<10 && d.getMonth()<10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-0'+d.getMonth()+'-0'+d.getDate())
                        else  if(d.getDate()>10 && d.getMonth()>10)
                        	$('#deathLine').attr('max',d.getFullYear()+'-'+d.getMonth()+'-'+d.getDate())
                    }
                    
                })
                 $('#endTime').change(function(){
                    var startTimeV = $('#startTime').val();
                    var endTimeV = $('#endTime').val(); 
                    $('#startTime').attr("max",endTimeV);
                    $('#startTime').attr('min','2000-08-31');
                }) 
                var date = new Date();
                if((date.getMonth()+1)<10){
                    var x = date.getFullYear()+'-0'+(date.getMonth()+1)+'-'+date.getDate();
                    $('#startTime').val(x);
                    $('#endTime').val(x);
                }else{
                    var x = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()
                    $('#startTime').val(x);
                    $('#endTime').val(x);
                }
        })
        
        document.addEventListener("DOMContentLoaded", function () {
            document.getElementById("actPic").addEventListener("change", fileViewer);
        });
        
        function fileViewer() {
            //取得使用者在檔案選擇標籤中選取檔案
            var theFiles = document.getElementById("actPic").files
            for(var i=0;i<theFiles.length;i++){
            //1. 建立FileReader物件
                var reader = new FileReader();
            //3.onload資料讀取成功完成時觸發
                reader.addEventListener("load", function (e) {
            //4. 將檔案內容暫存
                    var fileContent=e.target.result
                // alert(fileContent)
            //5. 找到img標籤
                    var imgobj=document.getElementById("picZone");
                    var imgobj2=document.getElementById("actPiczone");
            //6. 設定img的src屬性
                  imgobj.setAttribute("src",fileContent);
                  imgobj2.setAttribute("src",fileContent);
            });
            //2. 使用readAsDataURL方法，讀取檔案內容
                reader.readAsDataURL(theFiles[i]);
        }}
        
        $(document).ready(function(){
            $('input.timepicker').timepicker({});
        });
        
        $(function(){
        	$('#preBotton').click(function(){
        		var selCityVal = $('#selCity').val();
        		if(selCityVal == 0)
        			var city = '台北市';
        		if(selCityVal == 1)
        			var city = '新北市';
        		
        		var datas = [];
				$(':checked[name="classtype"]').each(function(){
					datas.push($(this).val())
				})
				
				var length = datas.length;
				var typeArr = '';
				
				if(length == 0)
   					typeArr = '';
   				else if(length == 1)
					typeArr = datas;
   				else{
   					for (var i = 0; i < (length-1); i++)
   						typeArr += datas[i] + ',';
   					typeArr += datas[length-1];
   				}
				
            	$('#actName').empty().append($('#insertActname').val());
            	$('#actType').empty().append(typeArr);
            	$('#actCity').empty().append(city);
            	$('#actWhere').empty().append($('#insertWhere').val());
            	$('#actStarttime').empty().append($('#startTime').val() + ' ' + $('#startTime2').val());
            	$('#actEndtime').empty().append($('#endTime').val() + ' ' + $('#endTime2').val());
            	$('#actDescription').empty().append($('#insertDes').val());
            	$('#actNumber').empty().append($('#selNum').val());
            	$('#actBudget').empty().append($('#selBud').val());
            	$('#actDeathline').empty().append($('#deathLine').val());
            })
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
    .selPic{
    height:180px;
    border-radius:15px;
    }
    .actPic{
    height:100px;
    border-radius:10px;
    }
</style>
<title>我要揪團</title>
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
			<div>
				<form action="<c:url value="/actCreate.controller"/>" method="get" enctype="multipart/form-data">
					<table>
						<tr>
							<td>聚會封面</td>
							<td>
								<img src="images/actcreate.jpg" id="picZone" class="selPic">
								<input type="file" name="picture" id="actPic" accept="image/*">
							</td>
						</tr>
						<tr>
							<td>聚會標題</td>
							<td>
								<input type="text" name="title" id="insertActname">
							</td>
						</tr>
						<tr>
							<td>聚會類型</td>
							<td>
							<input type="checkbox" name="classtype" value="輕鬆聊">輕鬆聊
							<input type="checkbox" name="classtype" value="浪漫約會">浪漫約會
							<input type="checkbox" name="classtype" value="寵物">寵物
							<input type="checkbox" name="classtype" value="桌遊">桌遊
							<input type="checkbox" name="classtype" value="郊遊踏青">郊遊踏青
							<input type="checkbox" name="classtype" value="電影">電影
							</td>
						</tr>
						<tr>
							<td>城市/所在地</td>
							<td>
								<select name="city" id="selCity">
									<option value="0">台北市</option>
									<option value="1">新北市</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>地點</td>
							<td>
								<input type="text" name="addr" id="insertWhere" value="館前路36號">
							</td>
						</tr>
						<tr>
							<td>開始時間</td>
							<td>
								<input type="date" id="startTime" name="startTime">
								<input type="text" id="startTime2" name="startTimepicker" class="timepicker" autocomplete="off"/>
							</td>
						</tr>
						<tr>
							<td>結束時間</td>
							<td>
								<input type="date" id="endTime" name="endTime">
								<input type="text" id="endTime2" name="endTimepicker" class="timepicker" autocomplete="off"/>
							</td>
						</tr>
						<tr>
							<td>詳細描述</td>
							<td>
								<textarea name="content" id="insertDes" cols="30" rows="10"></textarea>
							</td>
						</tr>
						<tr>
							<td>聚會人數</td>
							<td>
								<input step="1" type="number" id="selNum" name="numberlimit" ng-model="peoplemax" ng-init="peoplemax = 5">
							</td>
						</tr>
						<tr>
							<td>聚會預算</td>
							<td>
								<input step="50" type="number" id="selBud" name="feed" ng-model="fee" ng-init="fee = 100">
							</td>
						</tr>
						<tr>
							<td>報名截止日期</td>
							<td>
								<input type="date" id="deathLine" name="dateline">
							</td>
						</tr>
		
					</table>
					<input type="botton" name="" value="預覽" id="preBotton" data-target="#preview" data-toggle="modal" />
		  <div class="modal fade" id="preview" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-lg" role="document">
			  <div class="modal-content"><!--白色遮罩層-->
				  <div class="modal-body">
		  <!--       // modal-body  有差padding -->
					  <div class="modal-header">
						  
						  <h5 class="modal-title lead"><strong>預覽</strong></h5>
						  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
						  </button>
					  </div>
					  <div class="modal-body" id="mid-body">
					  	<table>
					  	<tr><td>聚會封面</td><td id="actPicture"><img src="images/actcreate.jpg" id="actPiczone" class="actPic"></td></tr>
					  	<tr><td>聚會標題</td><td id="actName" value="${errMsgs.nameErr}"></td></tr>
					  	<tr><td>聚會類型</td><td id="actType"></td></tr>
					  	<tr><td>城市/所在地</td><td id="actCity"></td></tr>
					  	<tr><td>地點</td><td id="actWhere"></td></tr>
					  	<tr><td>開始時間</td><td id="actStarttime"></td></tr>
					  	<tr><td>結束時間</td><td id="actEndtime"></td></tr>
					  	<tr><td>詳細描述</td><td id="actDescription"></td></tr>
					  	<tr><td>聚會人數</td><td id="actNumber"></td></tr>
					  	<tr><td>聚會預算</td><td id="actBudget"></td></tr>
					  	<tr><td>報名截止日期</td><td id="actDeathline"></td></tr>
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
            <p class="text-center">- Wegther 2018 EEIT10202 -</p>
    </footer>

</body>
</html>