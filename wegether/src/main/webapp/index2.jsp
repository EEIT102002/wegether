<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="/wegether/css/style.css">
<link rel="stylesheet" href="/wegether/css/index.css">
<script type="text/javascript" src="/wegether/js/preindexFunction.js"></script>
<script type="text/javascript" src="/wegether/js/index.js"></script>
<script type="text/javascript" src="/wegether/js/indexlogin.js"></script>
</head>	
<body>
	<div class="banner">
			<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
			<div class="banner-info">
				<div class="from-group">
					<div class="AreaCon">
						<div id="search_for_act">活動</div>
						<div id="search_for_po">心得</div>
					</div>
						<form id="form_act">
								<input type="hidden" value="0" name="state"/>
								<div class="AreaCon">
								<label>地區 :</label>
								<select name="cityselect_name" id="CitySelect">
									<option value="">--請選擇--</option>
								</select>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動開始日期 :</span><br>
									<input type="date" class="form-control" id="start_date" name="start_date_name"><br>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動結束日期 :</span><br>
									<input type="date" class="form-control" id="end_date" name="end_date_name"><br>
								</div>
								<div class="AreaCon" id="typeArea">
									<span>活動類型 :</span><br>
									<div id="labelflexCon">
									<label class="labelflex">
										<input type="checkbox" value="運動" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>運動</p></input>
									</label >
									<label class="labelflex">
										<input type="checkbox" value="休閒" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>休閒</p></input>
									</label>
									<label class="labelflex">
										<input type="checkbox" value="音樂" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>音樂</p></input>
									</label>
									<label class="labelflex">
										<input type="checkbox" value="美食" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>美食</p></input>
									</label>
									<label class="labelflex">
										<input type="checkbox" value="聊天" name="type_select_name" class="hidden-input"><span class="your style about checkbox"></span><p>聊天</p></input>				
									</label>
									</div>
								</div>
								<div class="AreaCon" id="keyword_search">
									<span class="form-group">關鍵字搜尋 :</span><br>
									<input type="text" placeholder="搜尋-活動名稱" class="form-control" id="keyword_search_input" name="keyword_search_input_name"><br>
								</div>
								<div id="searchbarButton">	
									<input id="select_act_submit" type="submit" value="搜尋" class="btn btn-warning form-control">
									<input type="reset" value="清除" class="btn btn-warning form-control">
								</div>
							</form>
							
						<form id="form_po" >
							<input type="hidden" value="1" name="state_po" />
							<div class="AreaCon">
								<label>地區 :</label>
								<select name="cityselect_name_po" id="CitySelect_po" >
									<option value="">--請選擇--</option>
								</select>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動開始日期 :</span><br>
									<input type="date" class="form-control" id="start_date_po" name="start_date_name_po"><br>
								</div>
								<div class="AreaCon">
									<span class="form-group">活動結束日期 :</span><br>
									<input type="date" class="form-control" id="end_date_po" name="end_date_name_po"><br>
								</div>
								<div class="AreaCon" id="typeArea_po">
									<span>活動類型 :</span><br>
									<div id="labelflexCon">
									<label class="labelflex">
										<input type="checkbox" value="運動" name="type_select_name_po" class="hidden-input"><span class="your style about checkbox"></span><p>運動</p></input>
									</label >
									<label class="labelflex">
										<input type="checkbox" value="休閒" name="type_select_name_po" class="hidden-input"><span class="your style about checkbox"></span><p>休閒</p></input>
									</label>
									<label class="labelflex">
										<input type="checkbox" value="音樂" name="type_select_name_po" class="hidden-input"><span class="your style about checkbox"></span><p>音樂</p></input>
									</label>
									<label class="labelflex">
										<input type="checkbox" value="美食" name="type_select_name_po" class="hidden-input"><span class="your style about checkbox"></span><p>美食</p></input>
									</label>
									<label class="labelflex">
										<input type="checkbox" value="聊天" name="type_select_name_po" class="hidden-input"><span class="your style about checkbox"></span><p>聊天</p></input>				
									</label>
									</div>
								</div>
						<div class="AreaCon" id="keyword_search_po">
									<span class="form-group">關鍵字搜尋 :</span><br>
									<input type="search" placeholder="搜尋-活動名稱" class="form-control" name="keyword_search_input_name_po" id="keyword_search_input_po"/>
								</div>
								<div id="searchbarButton_po">	
									<input id="select_po_submit" type="submit" value="搜尋" class="btn btn-warning form-control">
									<input type="reset" value="清除" class="btn btn-warning form-control">
								</div>


						</form>
				</div>
			</div>
		</div>
	<div class="about" id="about">
			<div class="container">
				<h1>活動</h1>
				
				<ul id="about_ActRank">
					<li><a href="">熱門</a></li>
					<li><a href="">時間</a></li>
					<li><a href="">地區</a></li>
				</ul>
					<div class="row  masonry" id="show_act_area">
					</div>
				</div>
			<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
			<script>
			$(function(){
				$('.masonry').masonry({
					itemSelector: '.item',
					})
			})
			</script>
	</div>
	<!-- <footer>
		<div class="container">
			<p id="fw">Wegther 2018</p>
		</div>
	</footer> -->
	<i  id="upTop" class="fa fa-chevron-circle-up animated infinite bounce" aria-hidden="true"></i>
	<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
</body>
</html>