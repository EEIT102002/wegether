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
						<a class="navbar-brand" href="index2.jsp">Wegether</a>
					</h1>
				</div>
			</div>
			<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
				<nav class="header_nav">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#"> <span data-hover="活動">活動</span>
					</a></li>
					<!-- 							<li><a href="#" class="scroll"><span data-hover="活動地圖">活動地圖</span></a></li> -->
					<li><a href="#" class="scroll"> <span data-hover="發起活動">發起活動</span>
					</a></li>
					<li><a href="#" class="scroll"> <span data-hover="發起心得">發起心得</span>
					</a></li>
					<li id="loginSpan"><a href="#" class="scroll"
						data-toggle="modal" data-target="#ActPageBox"> <span
							data-hover="登入">登入</span>
					</a></li>
					<li id="liRing" style="display: none;">
						<div class="dropdown" auto-close="disabled">
							<div class="dropdown-toggle" id="dropdownMenuButton2"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
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
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<img src='images/01.jpg' alt='' class='img-circle'
									style='width: 50px; height: 50px'>

								<div id="userinfo">XXX</div>
							</div>
							<div class="dropdown-menu" id="membergroup"
								aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="#">會員資料</a> <a
									class="dropdown-item" href="#">好友名單</a> <a
									class="dropdown-item" href="#">個人設定</a> <a
									class="dropdown-item" href="#">我的活動</a> <a
									class="dropdown-item" href="#">我的心得</a> <a
									class="dropdown-item" id="logoutSubSpanA"
									class="scrol dropdown-item" href="#">登出</a>
							</div>
						</div>
					</li>
				</ul>
				</nav>
			</div>
			</nav>
			