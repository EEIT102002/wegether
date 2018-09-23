var listhight = 60;
var noticeRowTemp = '<div class="panel panel-default"/>';
var noticeRowMainTemp = ' <div class="dropdown-item"><div><span class="spanRemove">&times;</span></div></div>';
var noticePhotoTemp = '<div><a><div><img src="1"></div></a></div>';
var divTemp = '<div/>';
var CollapsButtomTemp = '<a data-toggle="collapse" data-parent="#noticeCollapses" href=""><i class="fa fa-angle-down" aria-hidden="true"></i></a>';
var noticeCollapseTemp = '<div id="" class="panel-collapse collapse"><div class="dropdown-item"><button type="button" value="accept">接受</button><button type="button" value="reject">拒絕</button></div></div>';
var scrolltimes = 0;
var nwebSocket;
var num = 1;
$(function(){
	// 按menu內元素時menu不消失
	$(document).on('click', '#liRing .dropdown-menu', function(e) {
		e.stopPropagation();
	});
	
	
})


function connectNotice(token) {
	// 提醒所需變數
	var unread;
	var first = 0;
	var uncheck = true;
	var count = null;
	var scrollHeight;

	// 開始WebSocket連線
	var loc = window.location;
	var url = "ws://" + loc.host + "/wegether/noticeHandler?token=";

	nwebSocket = new WebSocket(url + token);
	// 以下開始偵測WebSocket的各種事件

	// onerror , 連線錯誤時觸發
	nwebSocket.onopen = function() {
		console.log("connection opened");
	};
	nwebSocket.onclose = function() {
		clearNoticeDiv();
		console.log("connection closed");
		//初始
		noticediv.off('scroll');
		$('#dropdownMenuButton2').off('click');
		$(document).off('click', '.spanRemove');
		$(document).off('click', '.panel:first a[data-toggle="collapse"]');
		$('#removeAllNotice').off('click');
		noticediv.off('click', '.panel-collapse button');
	};
	nwebSocket.onerror = function wserror(message) {
		console.log("error: " + message);
	};
	// onmessage , 接收到來自Server的訊息時觸發
	nwebSocket.onmessage = function(message) {
		console.log(message.data)
		if(message.data == "1"){
			unread += 1;
			$('#supremind').text(unread).show();
		}
	};

	// 讀取為查看提醒數
	$.get('/wegether/noitce/unread', function(data) {
		unread = data.count;
		if (unread != 0) {
			$('#supremind').text(unread).show();
		}
	}, 'json')

	

	var noticediv = $('#contentRemind');
	var noticedivdiv = noticediv.children('div');
	// 當轉動卷軸時 讀取下10的訊息
	noticediv.scroll(function() {
		readNextNoticeScroll();
	})
	
	function readNextNoticeScroll(){
		if (scrolltimes == 0 && first != 0
				&& noticediv.scrollTop() <= scrollHeight - (noticedivdiv.children('div').length * listhight)+100
				&& first % 10 == 0) {
			scrolltimes = 1;
			selectNotices(function() {
			}, function() {
				scrolltimes = 0;
			});
		}
	}

	// 打開menu時 如果unread>0或是先前還未開啟menu讀取前10條提醒
	$('#dropdownMenuButton2').click(function() {
		if ($(this).attr('aria-expanded') == 'false') {
			if (unread > 0) {
				selectNotices(clearNoticeDiv, clearUnread);
			} else if (uncheck) {
				selectNotices(clearNoticeDiv, clearUnread);
			}
		}
	})

	// 清空menu
	function clearNoticeDiv() {
		first = 0;
		noticedivdiv.html("");
		noticedivdiv.css('min-height', '50px');
	}

	// 讀取通知
	function selectNotices(fn1, fn2) {
		fn1();
		$.post('/wegether/noitce/select/' + first, function(data) {
			if (true) {
				first += data.notices!= null ? data.notices.length:0;
				if (data.count != null) {
					count = data.count;
					console.log(count);
					if (count == 0) {
						noticedivdiv.text('沒有通知');
					} else {
						noticedivdiv.css('min-height', (count * listhight)
								+ "px");
					}
				}
				$.each(data.notices, function(i, e) {
					var noticeRowDiv = $(noticeRowTemp);
					noticedivdiv.append(noticeRowDiv);
					if(e.state == 1){
						noticeRowDiv.css('background-color','#f0f1e6');
					}

					var noticeRowMain = $(noticeRowMainTemp);
					noticeRowDiv.append(noticeRowMain);
					noticeRowMain.find('span').attr('noticeid', e.id);

					var noticeContentDiv = $(divTemp);
					noticeRowMain.prepend(noticeContentDiv);

					noticeContentDiv.append($(divTemp).html(noticeContent(e)));
					var date = new Date(e.noticetime);
					var noticeContentTimeDiv = $(divTemp).append(
							$(divTemp).text(
									date.customFormat('#MM#/#DD#')
									));
					noticeContentDiv.append(noticeContentTimeDiv);

					switch (e.ntype) {
					case 6:
					case 7:
					case 12:
					case 13:
					case 14:
					case 15:
						break;
					default:
						var noticePhotoDiv = $(noticePhotoTemp);
						noticeRowMain.prepend(noticePhotoDiv);
						noticePhotoDiv.find('img').attr('src',
								'/wegether/member/photo/' + e.cmemberid)
						noticePhotoDiv.find('a').attr('href',
								'/wegether/personal.controller?memberId='+ e.cmemberid);
					}

					switch (e.ntype) {
					case 1:
					case 4:
					case 5:
						var collapseId = 'noticeCollapse' + e.id;
						var collapseButtom = $(CollapsButtomTemp).attr('href',
								'#' + collapseId);
						noticeContentTimeDiv.prepend(collapseButtom);
						var noticeCollapseDiv = $(noticeCollapseTemp).attr(
								'id', collapseId);
						noticeRowDiv.append(noticeCollapseDiv);
						if (e.ntype == 1) {
							noticeCollapseDiv.children('.dropdown-item').attr(
									'ntype', e.ntype).attr('friendid',
									e.friendid);
						} else {
							if(e.ntype == 4 ){
								noticeCollapseDiv.prepend($('<a class="attendsrc"/>').attr('href','/wegether/activity/ActivityReview.jsp?attendid='+e.attendid).text('查看詳細'))
							}
							noticeCollapseDiv.children('.dropdown-item').attr(
									'ntype', e.ntype).attr('attendid',
									e.attendid);
						}
						break;
					default:
						break;
					}
				})
				scrollHeight = noticediv.prop("scrollHeight");
				fn2();
			}
		})
	}

	$(document).on('click', '.spanRemove', function() {
		var e = $(this);
		$.post('/wegether/notice/delete/' + e.attr('noticeid'), function() {
			removeNotice(e);
			readNextNoticeScroll();
			if (noticedivdiv.children('div').length == 0) {
				clearNoticeDiv();
				noticedivdiv.html("沒有通知");
			}
		}, 'json')
	})

	$(document).on('click', '.panel:first a[data-toggle="collapse"]',
			function() {
				// noticediv.scrollTop(+300)
				noticediv.animate({
					scrollTop : noticediv.scrollTop()
				}, 500);
				// noticediv.scrollTop(noticediv.scrollTop()+100).delay(1000 );
			})

	$('#removeAllNotice').click(function() {
		if (count != 0) {
			$.post('/wegether/notice/delete', function() {
				clearNoticeDiv();
				noticedivdiv.html("沒有通知");
			}, 'json')
		}

	})

	noticediv.on('click', '.panel-collapse button', function() {
		var e = $(this);
		var parent = e.parent('.dropdown-item');
		var ntype = parent.attr('ntype');
		if (ntype == 1) {
			$.post('/wegether/friend/' + e.val(), 'id='
					+ parent.attr('friendid'), function(data) {
				if (data.state) {
					removeNotice(parent);
				}
			}, 'json')
		}
		if (ntype == 4) {
			$.post('/wegether/attend/' + e.val() + '/'
					+ parent.attr('attendid'), function() {
				removeNotice(parent);

			}, 'json')
		}
		if (ntype == 5) {
			$.post('/wegether/attend/invite/' + e.val() + '/'
					+ parent.attr('attendid'), function(data) {
				removeNotice(parent);
			}, 'json')
		}
	})

	function removeNotice(e) {
		var noticeRow = $(e).parents('.panel');
		var rowheight = noticeRow[0].offsetHeight;
		noticeRow.remove();
		noticedivdiv.css('min-height',
				(noticedivdiv[0].offsetHeight - rowheight) + "px");
		scrollHeight -= rowheight;
	}
	// 未讀通知歸0
	function clearUnread() {
		unread = 0;
		noticediv.scrollTop(scrollHeight);
		uncheck = false;
		$('#supremind').text(0).hide();
		$.get(
				'/wegether/noitce/unread/clear'
				, function (data) {
					if (data.state) {
						unread = 0;
						$('#supremind').text(0).hide();
					}
				}
				, 'json'
		 )
	}

	// 通知內容
	function noticeContent(e) {
		switch (e.ntype) {
		case 1:
			return noticeMT(e, ' 提出好友邀請 ');
		case 2:
			return noticeMT(e, ' 接受好友邀請 ');
		case 3:
			return noticeMTA(e, ' 向你推薦 ');
		case 4:
			return noticeMTA(e, ' 申請參加 ');
		case 5:
			return noticeMTA(e, ' 邀請你參加 ');
		case 6:
			return noticeAT(e, ' 報名成功 ');
		case 7:
			return noticeAT(e, ' 報名失敗 ');
		case 8:
			return noticeMTA(e, ' 同意參加 ');
		case 9:
			return noticeMTA(e, ' 拒絕參加 ');
		case 10:
			return noticeMTA(e, ' 發表參加 ').append(textToSpan(' 心得'));
		case 11:
			return noticeMTA(e, ' 發起 ');
		case 12:
			return $(divTemp).text(textToSpan('活動:' + e.ctitle + '取消'));
		case 13:
			return noticeAT(e, ' 內容變更');
		case 14:
			return noticeAT(e, ' 有新的留言');
		case 15:
			return noticeAT(e, ' 的心得有新的留言').prepend(textToSpan('在 '));

		}
		return e.id;
	}

	function noticeMT(e, text) {
		var div = $(divTemp);
		return div.append(memberAtage(e), textToSpan(text));
	}

	function textToSpan(text) {
		return $('<span/>').text(text);
	}

	function noticeMTA(e, text) {
		var div = $(divTemp);
		return div.append(memberAtage(e), textToSpan(text),
				activityAtage(e));
	}

	function noticeAT(e, text) {
		var div = $(divTemp);
		return div.append(activityAtage(e), textToSpan(text));
	}

	function memberAtage(e) {
		var memberUrl = '/wegether/personal.controller?memberId=';
		return Atage(e.cnickname, memberUrl + e.cmemberid);
	}

	function activityAtage(e) {
		var activityUrl = '/wegether/activityPage.controller?actid=';
		return Atage('活動:' + e.ctitle, activityUrl + e.cactivityid);
	}

	function Atage(name, url) {
		return $('<a/>').attr('href', url).text(name);
	}
};

function noticeClose() {
	nwebSocket.close();
}

Date.prototype.customFormat = function(formatString) {
	var YYYY, YY, MMMM, MMM, MM, M, DDDD, DDD, DD, D, hhhh, hhh, hh, h, mm, m, ss, s, ampm, AMPM, dMod, th;
	var dateObject = this;
	YY = ((YYYY = dateObject.getFullYear()) + "").slice(-2);
	MM = (M = dateObject.getMonth() + 1) < 10 ? M : M;
	MMM = (MMMM = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ][M - 1])
			.substring(0, 3);
	DD = (D = dateObject.getDate()) < 10 ? ('0' + D) : D;
	DDD = (DDDD = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday" ][dateObject.getDay()]).substring(0, 3);
	th = (D >= 10 && D <= 20) ? 'th' : ((dMod = D % 10) == 1) ? 'st'
			: (dMod == 2) ? 'nd' : (dMod == 3) ? 'rd' : 'th';
	formatString = formatString.replace("#YYYY#", YYYY).replace("#YY#", YY)
			.replace("#MMMM#", MMMM).replace("#MMM#", MMM).replace("#MM#", MM)
			.replace("#M#", M).replace("#DDDD#", DDDD).replace("#DDD#", DDD)
			.replace("#DD#", DD).replace("#D#", D).replace("#th#", th);

	h = (hhh = dateObject.getHours());
	if (h == 0)
		h = 24;
	if (h > 12)
		h -= 12;
	hh = h < 10 ? ('0' + h) : h;
	hhhh = hhh < 10 ? ('0' + hhh) : hhh;
	AMPM = (ampm = hhh < 12 ? 'am' : 'pm').toUpperCase();
	mm = (m = dateObject.getMinutes()) < 10 ? ('0' + m) : m;
	ss = (s = dateObject.getSeconds()) < 10 ? ('0' + s) : s;
	return formatString.replace("#hhhh#", hhhh).replace("#hhh#", hhh).replace(
			"#hh#", hh).replace("#h#", h).replace("#mm#", mm).replace("#m#", m)
			.replace("#ss#", ss).replace("#s#", s).replace("#ampm#", ampm)
			.replace("#AMPM#", AMPM);
}
