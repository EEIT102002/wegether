<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="container">
		<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="
				   collapsed"
				data-toggle="collapse" data-target="#dropdown_munu" id="hum">
				<span> <i class="fa fa-bars" aria-hidden="true"></i>
				</span>
			</button>
			<div class="logo">
				<h1>
					<a class="navbar-brand" href="/wegether/index2.jsp">Wegether</a>
				</h1>
			</div>
		</div>
		<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
			<nav class="header_nav">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/wegether/index2.jsp"> <span data-hover="活動">活動</span>
				</a></li>
				<!-- 							<li><a href="#" class="scroll"><span data-hover="活動地圖">活動地圖</span></a></li> -->
				<li><a href="javascript:;" class="scroll" data-toggle="modal" data-target="#ActPageBox"> <span data-hover="發起活動">發起活動</span>
				</a></li>
				<li><a href="javascript:;" class="scroll" data-toggle="modal" data-target="#ActPageBox"> <span data-hover="發起心得">發起心得</span>
				</a></li>
				<li id="loginSpan"><a href="#" class="scroll"
					data-toggle="modal" data-target="#ActPageBox"> <span
						data-hover="登入">登入</span>
				</a></li>
				<li id="liRing" style="display: none;">
					<div class="dropdown" auto-close="disabled">
						<div class="dropdown-toggle" id="dropdownMenuButton2"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fa fa-bell" aria-hidden="true">
								<div id="supremind" hidden>0</div>
							</i>
						</div>
						<div class="dropdown-menu noticemenu"
							aria-labelledby="dropdownMenuButton2">
							<div>通知</div>
							<div id="contentRemind">
								<div class="dropdown-menu panel-group" id="noticeCollapses">
								</div>
							</div>
							<div>
								<button id="removeAllNotice">清除所有通知</button>
								<button class='dropdown-toggle' data-toggle='dropdown'>關閉</button>
							</div>

						</div>
				</li>
				<li id="logoutSpan" style="display: none;">
					<div class="dropdown">
						<div class="dropdown-toggle" id="dropdownMenuButton"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<img src='/wegether/images/01.jpg' alt='' class='img-circle'
								style='width: 50px; height: 50px'>

							<div id="userinfo">XXX</div>
						</div>
						<div class="dropdown-menu" id="membergroup"
							aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">會員資料</a> <a
								class="dropdown-item" href="/wegether/setting/setting.jsp?page=3">好友名單</a> <a class="dropdown-item"
								href="/wegether/setting/setting.jsp">個人設定</a> <a class="dropdown-item" href="/wegether/activity/ActivityReview.jsp">我的活動</a> <a
								class="dropdown-item" href="#">我的心得</a> <a class="dropdown-item"
								id="logoutSubSpanA" class="scrol dropdown-item" href="#">登出</a>
						</div>
					</div>
				</li>
			</ul>
			</nav>
		</div>
		</nav>
	</div>
	<div class="modal fade" id="ActPageBox" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<!--白色遮罩層-->
				<div class="modal-body">
					<!--       // modal-body  有差padding -->
					<div class="modal-header">

						<h5 class="modal-title lead">
							<strong>請選擇登入方式</strong>
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" id="mid-body">
						<form id="loginform">
							<p class="loginerror"></p>
							<div class="form-group" id="ACT">
								<label for="recipient-name" class="col-form-label">帳號:</label> <input
									type="text" class="form-control" id="account" name="account">
							</div>
							<div class="form-group" id="PWD">
								<label for="recipient-name" class="col-form-label">密碼:</label> <input
									type="password" class="form-control" id="pwd" name="pwd">
							</div>
							<button type="button" class="btn btn-primary" id="login">登入</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">取消</button>
						</form>
						<div id="or" class="bg-primary text-white">
							<h4>or</h4>
						</div>
						<button type="button" class="btn big_use Google_i">
							<i class="fa fa-google" aria-hidden="true"></i>Google 登入
						</button>
						<button type="button" class="btn big_use Fb_i">
							<i class="fa fa-facebook-official" aria-hidden="true"></i>FB 登入
						</button>
						<div class="AreaCon">
							<button type="button" class="btn small_use Google_i">
								<i class="fa fa-google" aria-hidden="true"></i>
							</button>
							<button type="button" class="btn small_use Fb_i">
								<i class="fa fa-facebook-official" aria-hidden="true"></i>
							</button>
						</div>
					</div>
					<div class="modal-footer">
						<p class="small text-left">
							還沒註冊嗎?趕緊註冊一個帳號吧! <a href="/wegether/RegisterPage.jsp">點我註冊</a>
						</p>

					</div>
				</div>
			</div>
		</div>
	</div>