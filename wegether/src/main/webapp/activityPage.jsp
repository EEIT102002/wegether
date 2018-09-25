<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ActivityPage</title>

<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="/wegether/css/Non-home.css">

<!-- applyForm -->
<script src="/wegether/js/applyForm.js" type="text/javascript"></script>
<link rel="stylesheet" href="/wegether/css/applyForm.css">

<!-- 推薦活動給好友 -->
<!-- <link rel="stylesheet" href="/wegether/css/friendSearchBox.css"> -->
<script src="/wegether/js/friendSearchBox.js" type="text/javascript"></script>
<!-- 活動頁面使用  -->
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/wegether/css/activityPage.css">
<script type="text/javascript" src="/wegether/js/headerfootertest.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/activityPage.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/msgPage.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/articlePage.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/idCheck.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/starPage.js"></script>

<script>


var activityid = ${actBean.id};

var memberid ;
if("${memberid}"!=null && "${memberid}"!=0){
	console.log("123");
  memberid = ${memberid};
}else{
	 memberid =null;
}

var actState = ${state};

var friendbuttonText = '推薦'
var actPicListSize;

if("${actPicListSize}"!=null && "${actPicListSize}"!=0){
	 actPicListSize = ${actPicListSize} ;
 }else{
	 actPicListSize =0;
 }

// var flag=0;
	$(function() {
		var articleid ;
		
		
		$('#header_nav ul li').click(function() {
			$(this).addClass('active')
				.siblings().removeClass('active');
		})
			
		// 推薦 /點閱率 /收藏 文字提示效果
		$("[data-toggle='tooltip']").tooltip();
		
		//收藏 按鍵圖示
// 		$('#favorButton').click(function(){
// 			if (flag == 0) {
// 				flag = 1; $("#favorButton").attr("src","images/activityPageImages/favorites.png");
// 	 		} else {
// 	 			flag = 0; $("#favorButton").attr("src","images/activityPageImages/favoritesOff.png");}
// 		})
		
		//切換留言版面 
		$('#msgButId').click(function(){
			 $("#msgButId").attr("class","btn btn-warning");	//留言按鍵
			 $("#articleButId").attr("class","btn btn-secondary"); //心得分享按鍵   
			 $("#demoArticle").hide();
			 $("#demo").show();
			 getMsgs("${actBean.id}");
		})
		
		// 切換心得分享版面
		$('#articleButId').click(function(){
			 $("#msgButId").attr("class","btn btn-secondary");	//留言按鍵
			 $("#articleButId").attr("class","btn btn-warning"); //心得分享按鍵   
			 $("#demo").hide();
			 $("#demoArticle").show();
			 getArticles("${actBean.id}"); //載入心得
			 
		})
		
				 
		
		
		
		 //載入留言 
// 		 console.log(${actBean.id});
		 getMsgs("${actBean.id}");
		 
		//清除留言預設文字
		 $('#txt').click(function(){
			 $("#txt").val('');
		});
		 
		//新增留言
		 $('#txtbut').click(function(){
			 postMsgs(activityid,memberid,"${actBean.state}",$("#txt").val()); 
			 $("#txt").val('');
		});
			
		//刪除留言
			$('#demo').click(function(event){
		        if (event.target.className == "btn btn-danger"){
					var temp = $(event.target).attr("msgid")
				 	 deleteMsgs(activityid,memberid,"${actBean.state}",temp);
		    		 $("#txt").val('');
		        }
		    });
		
			$('#demoArticle').click(function(event){
				//刪除心得
		        if (event.target.className == "btn btn-danger"){
		        	articleid = $(event.target).attr("articleid")
				 	deleteArticles(articleid);
		        }
		        
				//回覆心得分享
		        if (event.target.className == "btn btn-primary"){
		        	
		        	
		        	if(memberid!=null){
			        	var searchbox = $('#frBox');
						searchbox.modal();
						articleid = $(event.target).attr("articleid")
			        	console.log("articleid:"+articleid);
		        	}else{
// 					 <a href="#" class="tooltip-test"  data-toggle="modal" data-target="#ActPageBox">  </a>
					 
						
		        	}
		        }
		        
		    });
		 
		 
			$('#dialogBut').click(function(){
				var articleMsg = $('#dialogTxt').val();
			 	 responseArticles(articleid,memberid,articleMsg);
				
			})
			
			
		 
		
		
		//star
			$('#stardiv1 img').click(click1)
	        .hover(over1,out1);
		
			$('#stardiv2 img').click(click2)
	        .hover(over2,out2);
	
			
			$('#stardiv3 img').click(click3)
	        .hover(over3,out3);
	
			//我要分享心得
			$('#attendShare').click(function() {
				document.location.href = "ArticleCreate.jsp?actid="+activityid+"&actname="+$('#gettitle').val();
			})
		
	})
	
	
	
	
	function loginDo(){
		
		 $.get("wegether/activity/attend/check/"+activityid,
				  function(data){	
			 		idCheck(data);
			 		
		 			},'json');	
		 
		 $('input[value=留言]').removeAttr('data-toggle');
		 $('a[title=推薦給好友]').removeAttr('data-toggle');
	
	
	}
	


	function logoutDo() {
		console.log(" Member logout");
	
		}
	
	

	  

</script>


<style>


#applyForm .modal-header .close,
#applyCheck .modal-header .close,
#applyState .modal-header .close{
	margin-top: -10px;
	margin-right: -5px;
	font-size: 20px;
}

#applyCheck .modal-body h4,
#applyState .modal-body h4{
	    text-align: center;
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
			<!--       寫在這以下 -->
			<div id="core">
				<!-- 上面區塊 -->
				<div id="up">
					<!-- 上左區塊 -->
					<div id="left">

						<div id="up">
							<div id="left" style="width: auto;margin-right: 15px;">
								<a href="personal.controller?memberId=${hostBean.id}"  style="text-decoration:none;">
								<img 	src="/wegether/member/photo/${hostBean.id}"							
									class="img-circle" width="70" height="70"> </a>
							</div>
							<div id="left" style="width: auto;">
								<p id="txtup" style="font-weight:bold;color:#842b00;">${hostBean.nickname}</p>
								<p id="txtup" style="font-weight:bold;color:#c6a300;">${hostBean.job}</p>
							</div>
							<div id="right">
								<c:if test="${empty memberid}">
									<a href="#" class="tooltip-test" title="推薦給好友" data-toggle="modal" data-target="#ActPageBox" style="text-decoration:none;">  
									 	<img src="images/activityPageImages/invite.png" width="50">
									</a>&emsp;
								</c:if>
								<c:if test="${not empty memberid}">
									<a href="#" class="tooltip-test" title="推薦給好友" id="friendsearchButton"  style="text-decoration:none;">  
									 	<img src="images/activityPageImages/invite.png" width="50">
									</a>&emsp;
								</c:if>
								
								<span class="tooltip-test"  title="活動點閱率">
									<img src="images/activityPageImages/click2.png" width="50">
								</span>${actBean.click}&emsp; 
								
<!-- 								<a href="#" class="tooltip-test" -->
<!-- 									data-toggle="tooltip" title="收藏活動資訊"> <img id="favorButton" -->
<!-- 									src="images/activityPageImages/favoritesOff.png" width="50"></a> -->
							</div>
						</div>

						<!-- 	照片輪播 -->
						<div class="content">
							<div class="div1">
								<img id="imd0" class="img-thumbnail" style="display:none;">
									
							</div>
							<div class="div2" >
							
							
							<c:if test="${not empty actPicList}">
								<c:set var="temp" value="1" />
								<c:forEach var="obj" items="${actPicList}">
									<img id="imd${temp}" src="/wegether/picture/${obj}" class="img-thumbnail">
									<c:set var="temp" value="${temp+1}" />
								</c:forEach>
							</c:if>
								
								
								
							</div>
						</div>
						<!-- 	照片輪播  end-->
					</div>
					<!-- 上左區塊 end -->

					<!-- 上右區塊  -->
					<div id="right">
						<h3 class="getTitle">${actBean.title}</h3>
						<!-- right1 end -->
						<p style="font-weight:bold;color:#ce0000;">${actbegin}</p>
						<!-- right2 end -->
						<p style="font-weight:bold;color:#ff2d2d;">${actBean.addr}</p>
						
<!-- 						<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3615.2197094634926!2d121.54709331488881!3d25.026616844741607!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442aa32657c4a79%3A0x3d7f7c44e7d85df7!2zMTA25Y-w5YyX5biC5aSn5a6J5Y2A5pWm5YyW5Y2X6Lev5LqM5q61MjAx6Jmf!5e0!3m2!1szh-TW!2stw!4v1536913293480" width="400" height="250" frameborder="0" style="border: 3px solid #FFBB00;margin：10px;" allowfullscreen></iframe> -->
						
						<br><br>
						<div>
							<img src="images/activityPageImages/people.png" width="20">&nbsp;${actBean.numberlimit}人
							&emsp; <img src="images/activityPageImages/fees.png" width="20">&nbsp;${actBean.feed}元
							&emsp; <img src="images/activityPageImages/deadline.png"
								width="40">&nbsp;${dateline}
						</div>
						<br>
						<!-- 報名者大頭貼-->
						<div>
							<c:forEach var="obj" items="${attBeans}">
								<a href="personal.controller?memberId=${obj}"  style="text-decoration:none;" >
									<img src="/wegether/member/photo/${obj}" class="img-circle" width="70"  height="70">
								</a>
								&emsp;
							</c:forEach>
						</div>
						
						<!-- 報名申請人數 -->
						<p style="text-align: center;color:#28004d;" >${attedNumber}</p>
						
						<!-- 報名按鍵   //0:未登入  1:主辦人  2:已報名者  3:未報名者--> 
						<div style="text-align: center" id = "memBut">
						<c:if test="${empty memberid}">
							<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#ActPageBox">請 先 登 入 才 能 報 名</button>
						</c:if>
						<c:if test="${not empty memberid}">
							<button type="button" class="btn btn-warning" >請 先 登 入 才 能 報 名</button>
						</c:if>
							
						</div>
						<!-- 活動評價 -->
						<div id="starBlock">
						
								<!-- 活動滿意度-->
								<div id="stardiv1">
									<span style="font-weight: bold">活動滿意度:</span>
									<span id="spa1"  style="font-weight: bold;color:#FF0000;"></span>
									<br>
									<img id="idstar11" class="s" src="images/star.png" /> 
									<img id="idstar12" class="s" src="images/star.png" /> 
									<img id="idstar13" class="s" src="images/star.png" /> 
									<img id="idstar14" class="s" src="images/star.png" /> 
									<img id="idstar15" class="s" src="images/star.png" />
									<hr>
								</div>
								
								<!-- 溝通安排-->
								<div id="stardiv2">
									<span style="font-weight: bold">溝通安排:</span>
									<span id="spa2"  style="font-weight: bold;color:#FF0000;"></span>
									<br>
									<img id="idstar21" class="s" src="images/star.png" /> 
									<img id="idstar22" class="s" src="images/star.png" /> 
									<img id="idstar23" class="s" src="images/star.png" /> 
									<img id="idstar24" class="s" src="images/star.png" /> 
									<img id="idstar25" class="s" src="images/star.png" />
									<hr>
								</div>
								
								<!-- 時間地點選擇 -->
								<div id="stardiv3">
									<span style="font-weight: bold">時間地點選擇:</span>
									<span id="spa3"  style="font-weight: bold;color:#FF0000;"></span>
									<br>
									<img id="idstar31" class="s" src="images/star.png" /> 
									<img id="idstar32" class="s" src="images/star.png" /> 
									<img id="idstar33" class="s" src="images/star.png" /> 
									<img id="idstar34" class="s" src="images/star.png" /> 
									<img id="idstar35" class="s" src="images/star.png" />
									<hr>
								</div>
								  <input id="startSumit" type="submit" value="送出"  class="btn btn-warning" onclick="sendRank()" >
								  <span id="output1" style="display:none;font-weight: bold;color:#FF0000;"></span>
								
								  <input id="attendShare" type="button" value="我要分享心得"  class="btn btn-warning" >
						</div>
					<!-- 活動評價 END-->
						
			</div>
			<!-- 上右區塊 end -->

				</div>
				<!-- 上面區 end -->
				<!-- 下面區塊 -->
				<div id="down">
					<h2>${actBean.title}</h2><input type="hidden" value="${actBean.title}" id="gettitle">
					<p style="font-weight:bold;color:#28004d;">${actBean.content}</p>
					</br>

					<div id="msgBlock" >
						<h4>留下意見:</h4>
						<textarea id="txt" cols="50" rows="2">對聚會有任何疑問嘛？留個言吧！</textarea>
						</br> 
						<c:if test="${empty memberid}">
							<input type="button" class="btn btn-primary" value="留言" data-toggle="modal" data-target="#ActPageBox" />
						</c:if>
						<c:if test="${not empty memberid}">
							<input id="txtbut" type="button" class="btn btn-primary" value="留言" />
						</c:if>
						</br> </br>
					</div>
					<!--  留言、心得分享  切換按鍵-->
					<button id="msgButId" type="button" class="btn btn-warning" >留言</button>
					<button id="articleButId" type="button" class="btn btn-secondary" >心得	分享</button>
					</br> </br>

					<!--  留言、心得分享 切換按鍵  end-->
					
					<!-- msg begin -->
					<div id="demo"></div>
					<div id="demoArticle"></div>
					<!-- msg end -->

				</div>
				<!-- 下面區塊  end-->

			</div>
			<!--       寫在這以上 -->
		</div>
	</div>
	
	
<!-- 	報名表單 -->
	<div class="modal fade" id="applyForm" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">報名表單</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" id='sendApply' class="btn btn-default">報名</button>

				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="applyCheck" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
				<h4>確定報名?</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default">確定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="applyState" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-default">關閉</button>
				</div>
			</div>

		</div>
	</div>


<!-- 推薦活動給好友 -->
	  <div class="modal fade" id="friendsearchBox" role="dialog" style="border:2px solid;">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div>
					
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-default">關閉</button>
				</div>
			</div>

		</div>
	</div>
	<!-- 推薦活動給好友 END-->
	
	<!-- 心得分享 -->
	  <div class="modal fade" id="frBox" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
					<textarea  id="dialogTxt" ></textarea>
				<div class="modal-footer">
					<button  id="dialogBut" type="button" data-dismiss="modal" class="btn btn-primary">送出</button>
				</div>
			</div>
		</div>
	</div>
	<!--  心得分享 END-->
	
	
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>
<script type="text/javascript" src="/wegether/js/activityPage/autoPlay.js"></script>
</html>