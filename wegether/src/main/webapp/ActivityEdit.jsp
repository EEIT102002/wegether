<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//TW" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改我揪的團</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="css/jquery.timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<!-- <script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
<script src="js/fileinput.js" type="text/javascript"></script>
<script src="js/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
<script>
	$(function() {
		$('#header_nav ul li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
		})
		
		var types = $('#hidecheckbox').val();

		var ss = types.split(',');
		for (i = 0; i < ss.length; i++) {
			$(':checkbox[value="' + ss[i] + '"]').attr('checked', 'checked');
		}

		var dade = $('#deathLine').val();
		
		$('.allPic').mouseover(function(){
			$(this).css('opacity', '0.4');
		})
		$('.allPic').mouseout(function(){
			$(this).css('opacity', '1');
		})
		
		var nameAr = $('.allPic').attr('name');
		alert(nameAr)
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
		
		$('#addOtherPics').click(function(){
			$('.form-group').css('display','block');
			$('#addOtherPics').css('display','none');
		})
	})

	$(document).ready(function() {
		$('input.timepicker').timepicker();
		
		var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
			,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖']			
		for(var i = 1; i <= array_for_city.length; i++) {
			var x1 ="<option value="+i+">" + array_for_city[i-1] + "</option>";	
			$('#selCity').append(x1);
		}
		
		var city1 = $('#hidecity').val();
		$('option[value=' + city1 + ']').attr('selected' , 'true');
	});
	
	$(function() {
		var now = new Date();
		if ((now.getMonth() + 1) < 10 && now.getDate() < 10) {
			var min = now.getFullYear() + '-0' + (now.getMonth() + 1) + '-0' + now.getDate();
			$('#startTime').attr('min', min);
			$('#deathLine').attr('min', min);
		}
		if ((now.getMonth() + 1) < 10 && now.getDate() >= 10) {
			var min = now.getFullYear() + '-0' + (now.getMonth() + 1) + '-' + now.getDate();
			$('#startTime').attr('min', min);
			$('#deathLine').attr('min', min);
		}
		if ((now.getMonth() + 1) >= 10 && now.getDate() < 10) {
			var min = now.getFullYear() + '-' + (now.getMonth() + 1) + '-0' + now.getDate();
			$('#startTime').attr('min', min);
			$('#deathLine').attr('min', min);
		}
		if ((now.getMonth() + 1) >= 10 && now.getDate() >= 10) {
			var min = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();
			$('#startTime').attr('min', min);
			$('#deathLine').attr('min', min);
		}

		$('#startTime').change(
				function() {
					var startTimeV = $('#startTime').val();
					var endTimeV = $('#endTime').val();
					$('#endTime').attr("min", startTimeV);
					$('#endTime').attr('max', '2200-08-31');

					var v1 = startTimeV.substring(0, 4);
					var v2 = startTimeV.substring(5, 7);
					var v3 = (startTimeV.substring(8, 10) - 1);

					if (v3 == 0) {
						v2 = v2 - 1;
						var d = new Date(v1, v2, v3);
						if (d.getDate() < 10 && (d.getMonth() + 1) > 10)
							$('#deathLine').attr('max', d.getFullYear() + '-' + (d.getMonth() + 1)
											+ '-0' + d.getDate())
						else if (d.getDate() > 10 && (d.getMonth() + 1) < 10)
							$('#deathLine').attr('max', d.getFullYear() + '-0' + (d.getMonth() + 1)
											+ '-' + d.getDate())
						else if (d.getDate() < 10 && (d.getMonth() + 1) < 10)
							$('#deathLine').attr('max', d.getFullYear() + '-0' + (d.getMonth() + 1)
											+ '-0' + d.getDate())
						else if (d.getDate() > 10 && (d.getMonth() + 1) > 10)
							$('#deathLine').attr('max', d.getFullYear() + '-' + (d.getMonth() + 1)
											+ '-' + d.getDate())
					} else {
						var d = new Date(v1, v2, v3);
						if (d.getDate() < 10 && d.getMonth() > 10)
							$('#deathLine').attr('max', d.getFullYear() + '-' + d.getMonth() + '-0'
											+ d.getDate())
						else if (d.getDate() > 10 && d.getMonth() < 10)
							$('#deathLine').attr('max', d.getFullYear() + '-0' + d.getMonth() + '-'
											+ d.getDate())
						else if (d.getDate() < 10 && d.getMonth() < 10)
							$('#deathLine').attr('max', d.getFullYear() + '-0' + d.getMonth()
											+ '-0' + d.getDate())
						else if (d.getDate() > 10 && d.getMonth() > 10)
							$('#deathLine').attr('max', d.getFullYear() + '-' + d.getMonth() + '-'
											+ d.getDate())
					}
				})
		$('#endTime').change(function() {
			var startTimeV = $('#startTime').val();
			var endTimeV = $('#endTime').val();
			$('#startTime').attr("max", endTimeV);
			$('#startTime').attr('min', '2000-08-31');
		})
	})
	
	$(function() {
		$('#preBotton').click(function() {
					var datas = [];
					$(':checked[name="classtype"]').each(function() {
						datas.push($(this).val())
					})

					var length = datas.length;
					var typeArr = '';

					if (length == 0)
						typeArr = '';
					else if (length == 1)
						typeArr = datas;
					else {
						for (var i = 0; i < (length - 1); i++)
							typeArr += datas[i] + ',';
						typeArr += datas[length - 1];
					}

					$('#actName').empty().append($('#insertActname').val());
					$('#actType').empty().append(typeArr);
					$('#actCity').empty().append($('#selCity').find('option:selected').text());
					$('#actWhere').empty().append($('#insertWhere').val());
					$('#actStarttime').empty().append($('#startTime').val() + ' ' + $('#startTime2').val());
					$('#actEndtime').empty().append($('#endTime').val() + ' ' + $('#endTime2').val());
					$('#actDescription').empty().append($('#insertDes').val());
					$('#actNumber').empty().append($('#selNum').val());
					$('#actBudget').empty().append($('#selBud').val());
					$('#actDeathline').empty().append($('#deathLine').val());
				})
	})
		document.addEventListener("DOMContentLoaded", function() {
		document.getElementById("actPic").addEventListener("change", fileViewer);
	});

	function fileViewer() {
		//取得使用者在檔案選擇標籤中選取檔案
		var theFiles = document.getElementById("actPic").files
		for (var i = 0; i < theFiles.length; i++) {
			//1. 建立FileReader物件
			var reader = new FileReader();
			//3.onload資料讀取成功完成時觸發
			reader.addEventListener("load", function(e) {
				//4. 將檔案內容暫存
				var fileContent = e.target.result
				// alert(fileContent)
				//5. 找到img標籤
				var imgobj = document.getElementById("picZone");
				var imgobj2 = document.getElementById("actPicture");
				//6. 設定img的src屬性
				imgobj.setAttribute("src", fileContent);
				imgobj2.setAttribute("src", fileContent);
			});
			//2. 使用readAsDataURL方法，讀取檔案內容
			reader.readAsDataURL(theFiles[i]);
		}
	}
</script>
<style>
.selPic , .allPic {
	height: 180px;
	border-radius: 15px;	
}
.actPic {
	height: 100px;
	border-radius: 10px;
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
		<form id="ActivityCreateForm"
				action="<c:url value="/actEdit.edit.controller"/>" method="post"
				accept-charset="ISO-8859-1" enctype="multipart/form-data">
				<table>
					<tr>
						<td>聚會封面</td>
						<td><img src="data:image/jpg;base64,${actOnePic}" id="picZone" class="selPic">
							<input type="file" name="picture" id="actPic" accept="image/*">
							<input type="hidden" value="${result.id}" name="id"></td>
					</tr>
					<tr>
						<td>其他照片</td>
						<td><div>
							<c:set var="temp" value="2" />
							<c:forEach var="i" items="${actAllPic}">
								<div class="picContainerEdit" id="actEditPic${temp}">
								<img src="data:image/jpg;base64,${i}" class="allPic" name='${actAllId}' id="${temp}">
								<div class="deleteEditPics" id="delEditPic${temp}">
								<img src="images/trash_can.png"></div></div>
								<c:set var="temp" value="${temp+1}" />
							</c:forEach>
							</div>
							<input type="button" value="我要新增其他照片" id="addOtherPics"/>
							<div class="form-group" style="display:none">
							<input id="file-1" type="file" multiple class="file" data-overwrite-initial="false"
									name="pics"></div>
							<script>
							$("#file-1").fileinput({
								uploadExtraData: {'activitiId': ${actid}},
        						uploadUrl: '/wegether/insertOtherPics.do', // you must set a valid URL here else you will get an error
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
            						return filename.replace('(', '_').replace(']', '_');
								}
							});    
							</script></td>
					</tr>
					<tr>
						<td>聚會標題</td>
						<td><input type="text" name="title" id="insertActname"
							value="${result.title}${param.title}">${errMsgs.title}</td>
					</tr>
					<tr><td>聚會類型</td>
						<td><input type="checkbox" name="classtype" value="運動">運動
							<input type="checkbox" name="classtype" value="休閒">休閒
							<input type="checkbox" name="classtype" value="音樂">音樂
							<input type="checkbox" name="classtype" value="美食">美食
							<input type="checkbox" name="classtype" value="聊天">聊天</td>
							<input type="hidden" name="hidecheckbox" id="hidecheckbox" value="${type}">
					</tr>
					<tr><td>城市/所在地</td>
						<td><select name="city" id="selCity">
							<option>請選擇</option></select>
							<input type="hidden" id="hidecity" value="${result.city}${param.city}"></td>
					</tr>
					<tr>
						<td>地點</td>
						<td><input type="text" name="addr" id="insertWhere" value="${result.addr}${param.addr}"></td>
					</tr>
					<tr>
						<td>開始時間</td>
						<td><input type="date" id="startTime" name="startTime" value="${startTime}${param.startTime}">
							<input type="text" id="startTime2" name="startTimepicker" value="${startTimepicker}${param.startTimepicker}"
							placeholder="例  01:25 pm" class="timepicker" />${errMsgs.starDateTime}
						</td>
					</tr>
					<tr>
						<td>結束時間</td>
						<td><input type="date" id="endTime" name="endTime"
							value="${endTime}${param.endTime}"> <input type="text" id="endTime2"
							name="endTimepicker" class="timepicker" value="${endTimepicker}${param.endTimepicker}"
							autocompelete="off" placeholder="例  01:25 pm" />${errMsgs.endDateTime}</td>
					</tr>
					<tr>
						<td>詳細描述</td>
						<td><textarea name="content" id="insertDes" cols="30" rows="10">
							${result.content}${param.content}</textarea>${errMsgs.content}</td>
					</tr>
					<tr>
						<td>聚會人數</td>
						<td><input step="1" type="number" id="selNum" name="numberlimit" ng-model="peoplemax"
							min="1" value="${result.numberlimit}${param.numberlimit}"></td>
					</tr>
					<tr>
						<td>聚會預算</td>
						<td><input step="50" type="number" id="selBud" name="feed" ng-model="fee" min="0" value="${result.feed}${param.feed}"></td>
					</tr>
					<tr>
						<td>報名截止日期</td>
						<td><input type="date" id="deathLine" name="dateline"
							value="${deathLine}${param.dateline}">${errMsgs.deathline}</td>
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
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body" id="mid-body">
									<table>
										<tr>
											<td>聚會封面</td>
											<img id="actPicture" src="data:image/jpg;base64,${actOnePic}"
												 class="selPic"/></td>
										</tr>
										<tr>
											<td>聚會標題</td>
											<td id="actName"></td>
										</tr>
										<tr>
											<td>聚會類型</td>
											<td id="actType"></td>
										</tr>
										<tr>
											<td>城市/所在地</td>
											<td id="actCity"></td>
										</tr>
										<tr>
											<td>地點</td>
											<td id="actWhere"></td>
										</tr>
										<tr>
											<td>開始時間</td>
											<td id="actStarttime"></td>
										</tr>
										<tr>
											<td>結束時間</td>
											<td id="actEndtime"></td>
										</tr>
										<tr>
											<td>詳細描述</td>
											<td id="actDescription"></td>
										</tr>
										<tr>
											<td>聚會人數</td>
											<td id="actNumber"></td>
										</tr>
										<tr>
											<td>聚會預算</td>
											<td id="actBudget"></td>
										</tr>
										<tr>
											<td>報名截止日期</td>
											<td id="actDeathline"></td>
										</tr>
									</table>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary">確認送出</button>
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">修改</button>
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