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
		
		$('.allPic').mouseover(function(){
			$(this).css('opacity', '0.4');
		})
		$('.allPic').mouseout(function(){
			$(this).css('opacity', '1');
		})
		
		var nameAr = $('.allPic').attr('name');
		var thisName = nameAr.substring(1,(nameAr.length-1))
		var ids = thisName.split(',');
		for(i = 0; i < ids.length; i++){
			$('img[id=' + (i+2) + ']').attr('id', ids[i]);
		}
		
		$('.deleteEditPics').click(function(){
			var picId = $(this).parent().children().attr('id');
			var thisNum = $(this).attr('id').charAt(10);

			$.ajax("/wegether/pictureD/" + picId,{
				method:"DELETE",
				success:function(a){
 					$('#actEditPic' + thisNum).remove();
				}
			})
		})
	})
</script>
<style>
.allPic {
	height: 180px;
	border-radius: 15px;	
}
 .picContainerEdit{ 
 	position:relative;
 	display:inline;
 } 
.deleteEditPics{
	position: absolute;
	bottom:-70px;
	left:5px;
}
</style>
</head>
<body>
	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
	<div class="container">
		<div id="small_con">
		修改心得
			<form id="ArticleEditForm"
				action="<c:url value="/artEdit.edit.controller"/>" method="post"
				accept-charset="ISO-8859-1" enctype="multipart/form-data">
				<table>
					<tr>
						<td>聚會標題</td>
						<td>${param.actname}<input type="hidden" value="${param.artid}" name="artid"/></td>
					</tr>
					<tr>
						<td>心得</td>
						<td><textarea name="content" id="insertDes" cols="30" rows="10">${param.content}${articleResult.content}</textarea>${content}</td>
					</tr>
					<tr>
						<td>上傳活動照片</td>
						<td><div>
							<c:set var="temp" value="2" />
							<c:forEach var="i" items="${articlePics}">
								<div class="picContainerEdit" id="actEditPic${temp}">
								<img src="data:image/jpg;base64,${i}" class="allPic" name='${picIds}' id="${temp}">
								<div class="deleteEditPics" id="delEditPic${temp}">
								<img src="images/trash_can.png"></div></div>
								<c:set var="temp" value="${temp+1}" />
							</c:forEach>
							</div>
						<input type="button" value="我要上傳其他照片" id="addOtherPics"/>
						<div class="form-group" style="display:none">
						<input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" name="multipicture"></div>
						<script>
						$("#file-1").fileinput({
							uploadExtraData: {'articleId' : ${artid}},
        					uploadUrl: '/wegether/insertArtOtherPics.do', // you must set a valid URL here else you will get an error
        					allowedFileExtensions : ['jpg', 'png','gif'],
        					overwriteInitial: false,
        			        showCaption: false,
        			        fileActionSettings: {showZoom: false,
        			        					 uploadIcon: '<img src="images/upload_16.png">',
        			        					 removeIcon: '<img src="images/trash_can_red.png">'},
        			        removeIcon: '<img src="images/trash_can.png" style="height:20px">',
        			        browseIcon: '<img src="images/open_folder2.png"  style="height:20px">',
        			        uploadIcon: '<img src="images/upload.png"  style="height:20px">',
        					maxFileSize: 1000,
        					maxFilesNum: 10,
        					//allowedFileTypes: ['image', 'video', 'flash'],
        					slugCallback: function(filename) {
            					return filename.replace('(', '_').replace(']', '_');}
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