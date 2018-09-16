<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>ActivityPage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/wegether/css/activityPage.css">
<script src="/wegether/js/activityPage.js"></script>

<link href="/wegether/css/bootstrap.css" rel="stylesheet" type="text/css"	media="all" />
<link href="/wegether/css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="/wegether/js/jquery-2.1.4.min.js"></script>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'>
<script src="/wegether/js/bootstrap.js"></script>
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
<!-- 登入使用 -->
<script src="/wegether/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/wegether/js/noticeWebStocket.js" type="text/javascript"></script>
<script src="/wegether/js/logMethod.js" type="text/javascript"></script>
<!-- 登入使用 END -->
<!-- 留言 /心得心享 視窗  -->
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- 留言 /心得心享 視窗 END -->
<!-- applyForm -->
<script src="/wegether/js/applyForm.js" type="text/javascript"></script>
<link rel="stylesheet" href="/wegether/css/applyForm.css">
 <script>
 var queryStr;
 $(function(){
	 //載入留言
	 getMsgs();
	 
	//新增留言
	 $('#txtbut').click(function(){
		 getMsgs($("#txt").val());
		 $("#txt").val('');
	});
		
	//刪除留言
		$('#demo').click(function(event){
	        if (event.target.className == "btn btn-danger"){
				var temp = $(event.target).attr("msgid")
			 	 getMsgs('deleteMsgId='+temp);
	    		 $("#txt").val('');
	        }
	    });
		
	
})
	

 function getMsgs(msg){
	 var divElem = null ;
	 var temp="";
	 console.log("msg="+msg);
	 $.getJSON("msgs.controller",
			 { activityid:1, 
		 		memberid:2,
		 		state:0,
		 		content:msg
			 },
			 function(result){	
	 			$.each(result, function(i,item){	
	 				divElem =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'">'+
					'<img src="data:image/jpg;base64,'+item[1]+'" width="50">  </a>' +
					'<span style="color: blue;">'+item[2]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[3]+'</span>'+
					'<a id="deleteId" href="#" class="btn btn-danger" msgid='+item[5]+'>刪除</a>'+			
			 		'</br>'+item[4]+'</br>'+
					"</div>");
	 				temp = temp + divElem;
	 			});	
	 			$('#demo').html(temp);
	 });	
	 
 }

	
 </script>
</head>
<body>
	<input id="txt" type="text"><br>
	<input id="txtbut" type="button" class="btn btn-primary" value="留言" />
	<div id="demo"></div>
</body>
</html>