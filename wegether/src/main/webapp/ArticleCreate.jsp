<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//TW" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加心得</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>
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
		$('#preBotton').click(function() {
			$('#actDescription').empty().append($('#insertDes').val());
		})
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
</style>
</head>
<body>
	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
	<div class="container">
		<div id="small_con">
		增加心得
			<form id="ArticleCreateForm"
				action="<c:url value="/articleCreate.controller"/>" method="post"
				accept-charset="ISO-8859-1" enctype="multipart/form-data">
				<table>
					<tr>
						<td>聚會標題</td>
						<td>123</td>
					</tr>
					<tr>
						<td>心得</td>
						<td><textarea name="content" id="insertDes" cols="30" rows="10"></textarea>${content}</td>
					</tr>
					<tr>
						<td>上傳活動照片</td>
						<td>
						<input type="button" value="我要新增心得照片" id="addOtherPics"/>
						<div class="form-group" style="display:none">
						<input id="file-1" type="file" multiple class="file" data-overwrite-initial="false"
							   data-min-file-count="2" name="pics"></div>
						<script>
						$("#file-1").fileinput({
							//uploadExtraData: {'activitiId' : ${actid}},
        					uploadUrl: '/wegether/insertOtherPics.do', // you must set a valid URL here else you will get an error
        					allowedFileExtensions : ['jpg', 'png','gif'],
        					overwriteInitial: false,
        					showUpload: false, //是否显示上传按钮
        			        showCaption: false,
        			        fileActionSettings: {showUpload: false},
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
				<input type="button" name="" value="預覽" id="preBotton" data-target="#preview" data-toggle="modal" />
				<div class="modal fade" id="preview" tabindex="-1" role="dialog">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<!--白色遮罩層-->
							<div class="modal-body">
								<!--       // modal-body  有差padding -->
								<div class="modal-header">
									<h5 class="modal-title lead">
										<strong>預覽</strong>
									</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body" id="mid-body">
									<table>
										<tr>
											<td>聚會標題</td>
											<td id="actName"></td>
										</tr>
										<tr>
											<td>詳細描述</td>
											<td id="actDescription"></td>
										</tr>
										<tr>
											<td>上傳活動照片</td>
											<td id="artPic"></td>
										</tr>
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
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>
</html>