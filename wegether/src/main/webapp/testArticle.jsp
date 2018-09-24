<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TestPage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/wegether/css/activityPage.css">
<!-- 推薦活動給好友 -->
<link rel="stylesheet" href="/wegether/css/friendSearchBox.css">
<script src="/wegether/js/friendSearchBox.js" type="text/javascript"></script>

<title>test</title>
<script>
var friendbuttonText = '推薦'

$(function() {
	var articleid ;
	var articleMsgsID;
	 getArticles(1); //載入心得
	 
	
		$('#demoArticle').click(function(event){
			//刪除心得
	        if (event.target.className == "btn btn-danger"){
	        	articleid = $(event.target).attr("articleid")
			 	deleteArticles(articleid);
	        }
	        
			//回覆心得分享
	        if (event.target.className == "btn btn-primary"){
	        	var searchbox = $('#friendsearchBox');
				searchbox.modal();
				articleid = $(event.target).attr("articleid")
				
				
	        	console.log("articleid:"+articleid);
				
				
	        }
	        
	    });
	 
	 
		$('#dialogBut').click(function(){
			var articleMsg = $('#dialogTxt').val();
		 	 responseArticles(articleid,6,articleMsg);
			
		})
		
		
		
})
//載入心得   
 function getArticles(activityid){
	 var divElem1 = null ;
	 var divElem = null ;
	 var temp="";
	 var tempMsg="";
	 var getArticlesid =null;
	 
	 $.get("article.controller/"+activityid,
			 function(result){	
	 			$.each(result, function(i,item){
	 				
	 				divElem1 =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
					'<img src="/wegether/member/photo/'+item[0]+'" class="img-circle" width="70" height="70">  </a>' +
					'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[2]+'</span>'+
					'<button id="responseId" class="btn btn-primary" articleid='+item[4]+'>回覆</button>'+
// 					'<button id="deleteId" class="btn btn-danger" articleid='+item[4]+'>刪除</button>'+
			 		'</br>'+item[3]+'</br>'
			 		);
	 				 var divElem2 = null ;
	 				 var temp1="";
	 				$.each(item[5], function(i,item){
	 					divElem2 =(
	 					 '<img src="/wegether/picture/'+item+'" width="350" height="250" style=" border: 2px solid #272727; margin: 10px;">' 
	 					);
	 					temp1 = temp1 + divElem2;
	 				});	
	 				temp = divElem1 + temp1 + '<hr><div id=articleMsgs'+item[4]+'></div></div>' + temp ;
	 				
	 				
	 				
	 			});	
	 			$('#demoArticle').html(temp);
	 			getArticlesid = $('#responseId').attr("articleid");
 				console.log("zongyu:"+getArticlesid)
	 			
	 });	
	 
	 
 $.get('articlemsgs.controller/'+getArticlesid,
			 
			 function(result){	
				 console.log("responsemsg:");
		 			$.each(result, function(i,item){	
		 				divElem =("<div id='msgid'>" +
						'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
						'<img src="/wegether/member/photo/'+item[0]+'" width="50" class="img-circle">  </a>' +
						'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
						'<span style="font-size: small;">'+item[2]+'</span>'+
//							'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除</button>'+			
				 		'</br>'+item[3]+'</br>'+
						"</div>");
		 				tempMsg = tempMsg + divElem;
			 				console.log("temp:"+$('#articleMsgs123').attr("id"));
			 			//	$('#articleMsgs123').html("123");
		 			});	
		 			
// 		 			$('#articleMsgs2'+articleid).html("123");
		 			
		 });	
	 
	 
	 
 }

//刪除心得功能
function deleteArticles(articleid){
	 var divElem = null ;
	 var temp="";
	 $.ajax({
		  url: 'article.controller/'+articleid,
		  type: 'DELETE',
	      success:function(result){	
	 			$.each(result, function(i,item){	
	 				divElem1 =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
					'<img src="/wegether/member/photo/'+item[0]+'"class="img-circle" width="70" height="70"> </a>' +
					'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[2]+'</span>'+
					'<button id="responseId" class="btn btn-primary" articleid='+item[4]+'>回覆</button>'+
// 					'<button id="deleteId" class="btn btn-danger" articleid='+item[4]+'>刪除</button>'+			
			 		'</br>'+item[3]+'</br>'
			 		);
	 				 var divElem2 = null ;
	 				 var temp1="";
	 				$.each(item[5], function(i,item){
	 					divElem2 =(
	 					 '<img src="/wegether/picture/'+item+'" width="350" height="250" style=" border: 2px solid #272727; margin: 10px;">' 
	 					);
	 					temp1 = temp1 + divElem2;
	 				});	
	 				temp = divElem1 + temp1 + '<hr><div id="articleMsgs"></div></div>' + temp ;
	 			});	
	 			$('#demoArticle').html(temp);
	 }
		});
	 
	}
	
	//回覆心得分享
	function  responseArticles(articleid,memberid,articleMsg){
		console.log("articleid:"+articleid +" memberid:"+memberid+" articleMsg:"+articleMsg);
		 var divElem = null ;
		 var temp="";
		 $('#dialogTxt').val('');
		 $.post('articlemsgs.controller',
				 {
			 		articleid:articleid,
			 		memberid:memberid,
			 		content:articleMsg,
				 },
				 function(result){	
					 console.log("responsemsg:");
			 			$.each(result, function(i,item){	
			 				divElem =("<div id='msgid'>" +
							'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
							'<img src="/wegether/member/photo/'+item[0]+'" width="50" class="img-circle">  </a>' +
							'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
							'<span style="font-size: small;">'+item[2]+'</span>'+
// 							'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除</button>'+			
					 		'</br>'+item[3]+'</br>'+
							"</div>");
			 				temp = temp + divElem;
// 			 				console.log("temp:"+item[4]);
			 			});	
			 			
			 			$('#articleMsgs'+articleid).html(temp);
			 });	
		}

</script>
</head>
<body>


	
									
<div id="demoArticle" ></div>
<!-- 推薦活動給好友 -->
	  <div class="modal fade" id="friendsearchBox" role="dialog">
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
	<!-- 推薦活動給好友 END-->
</body>
</html>
