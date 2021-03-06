<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//TW" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加心得</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/fileinput.js" type="text/javascript"></script>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
<script>
	$(function() {
		$('#header_nav ul li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
		})
	})
	$(function() {
		$('#addOtherPics').click(function(){
			$('.form-group').css('display','block');
			$('#addOtherPics').css('display','none');
		})
	})
</script>
<style>
.multi{
	height:150px;
}
textarea {
    width: 100%;
    height: 100px;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
table {
	margin-left:auto; 
	margin-right:auto;
}.table-left {
	border-right: solid 5px rgba(0, 44, 95, 0.3);
	border-bottom: solid 5px rgba(0, 44, 95, 0.3);
	padding-right: 10px;
}
.table-right {
	padding: 10px;
	padding-left: 20px;
	border-bottom: solid 5px rgba(0, 44, 95, 0.3);
	
}
.table-top {
	border-top: solid 5px rgba(0, 44, 95, 0.3);
}
input[type=submit], input[type=reset] {
    width: 10%;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    position: relative;
    left: 39%;
    top: 10px;
    padding: 14px 20px;
}
</style>
</head>
<body>
	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
	<div class="container">
		<div id="small_con">
			<form id="ArticleCreateForm"
				action="<c:url value="/articleCreate.controller"/>" method="post"
				accept-charset="ISO-8859-1" enctype="multipart/form-data">
				<table>
					<tr style="border:none"><td><h1>增加心得</h1></td><td></td></tr>
					<tr class="table-top">
						<td class="table-left">聚會標題</td>
						<td class="table-right">${param.actname}${actname}<input type="hidden" value="${param.actid}" name="actid"/></td>
					</tr>
					<tr>
						<td class="table-left">心得</td>
						<td class="table-right"><textarea name="content" id="insertDes" cols="30" rows="10"></textarea>${content}</td>
					</tr>
					<tr>
						<td class="table-left">上傳活動照片</td>
						<td class="table-right">
						<input type="button" value="我要上傳心得照片" id="addOtherPics"/>
						<div class="form-group" style="display:none">
						<input id="file-1" type="file" multiple class="file" data-overwrite-initial="false"
							   name="multipicture"></div>
						<script>
						$("#file-1").fileinput({
							//uploadExtraData: {'activitiId' : ${actid}},
        					uploadUrl: '/wegether/articleCreate.controller', // you must set a valid URL here else you will get an error
        					allowedFileExtensions : ['jpg', 'png','gif'],
        					overwriteInitial: false,
        					showUpload: false,
        			        showCaption: false,
        			        fileActionSettings: {showUpload: false, showZoom: false,
        			        					 removeIcon: '<img src="images/trash_can_red.png">'},
        			        removeIcon: '<img src="images/trash_can.png" style="height:20px">',
        			        browseIcon: '<img src="images/open_folder2.png"  style="height:20px">',
        					maxFileSize: 1000,
        					maxFilesNum: 10,
        					//allowedFileTypes: ['image', 'video', 'flash'],
        					slugCallback: function(filename) {
            					return filename.replace('(', '_').replace(']', '_');
							}
						});    
						</script></td>
					</tr>
				</table>
				<input type="submit" name="" value="送出" id="preBotton" data-target="#preview" data-toggle="modal" />
				<input type="reset" value="清除"/>
			</form>
		</div>
	</div>
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>
</html>