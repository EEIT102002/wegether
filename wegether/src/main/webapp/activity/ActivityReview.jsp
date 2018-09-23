<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Home</title>
<jsp:include page="/ShareTemp/headerjs.jsp"></jsp:include>
<link rel="stylesheet" href="/wegether/css/Non-home.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/raty/2.8.0/jquery.raty.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/raty/2.8.0/jquery.raty.min.css">
<script src="js/review.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/review.css">
<link rel="stylesheet" href="/wegether/css/friendSearchBox.css">
<script src="/wegether/js/friendSearchBox.js" type="text/javascript"></script>
	
<style>


</style>
<script>
</script>
</head>

<body>
	<jsp:include page="/ShareTemp/headertemp.jsp"></jsp:include>
	<div class="container">
		<div id="small_con">
			<!--       寫在這 -->
			<div>
				<div id="left" >
					<ul class="content_xx"
						style="font-family: Microsoft JhengHei; color: #0044BB;">
						<li type="button" class="btn btn-secondary changePage">主辦中的活動</li>
						<li type="button" class="btn btn-secondary changePage">參加中的活動</li>
						<li type="button" class="btn btn-secondary changePage">申請中的活動</li>
						<li type="button" class="btn btn-secondary changePage">活動邀請</li>
						<li type="button" class="btn btn-secondary changePage">過去主辦的活動</li>
						<li type="button" class="btn btn-secondary changePage">過去參加的活動</li>
					</ul>
				</div>
				<div id="right">
					<div align="center">
						<ul class="content_xx">
						</ul>
						<div class='showContent'>
								<div class="setting4">
										<div class="setting4list">
											<ul class="setting4ul">
												<li>參加名單</li>
												<li>申請名單</li>
												<li>邀請名單</li>
											</ul>
										</div>
										<div id="blocklist">
											<div class="friendsearch">
													<button id="friendsearchButton">邀請好友</button>
											</div>
											<div>
												<div class="panel-group" id="attendlist">
												</div>
											</div>
										</div>
								</div>
						</div>
					</div>
					<div align="center">
						<ul class="content_xx">
						</ul>
					</div>
					<div align="center">
						<ul class="content_xx">
						</ul>
					</div>
					<div align="center">
						<ul class="content_xx">
						</ul>
					</div>
					<div align="center">
						<ul class="content_xx">
						</ul>
					</div>
					<div align="center">
						<ul class="content_xx">
						</ul>
					</div>
					<!-- change_second end -->

					<!--change_third end -->

					<!--change_fourth end -->
				</div>

			</div>
			<!-- core end -->

		</div>
	</div>
    <div class="modal fade" id="friendsearchBox" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class= 'friendList'>
					
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-default">關閉</button>
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="/ShareTemp/footertemp.jsp"></jsp:include>
</body>
<script>
	
</script>

</html>