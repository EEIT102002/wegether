var reviewUrl = ['/member/activity/host/now'
	, '/member/activity/attend/now'
	, '/member/activity/applying'
	, '/member/activity/beInvited'
	, '/member/activity/host/history'
	, '/member/activity/attend/history'
];

var reviewButton = [
	'申請與參加者'
	, '取消參加'
	, '取消申請'
]

var friendbuttonText= "邀請";

var attendTemp = '<div class="panel panel-default"><div class="panel-heading"><a data-toggle="collapse" data-parent="#attendlist" href="" class="attend"><div class="memberPhoto"><div><img src="#" alt=""></div></div><div class="nickname"></div></a></div></div>';

var attendcollapse = '<div id="" class="panel-collapse collapse"><div class="panel-body"></div></div>';

var liTemp = '<li class="btn btn-secondary activityli"><a>' +
	'<div class="title"></div>' +
	'<div class="info">' +
	'<div class="host">主辦人: </div>' +
	'<div class="actbegin">活動時間: </div>' +
	'<div class="accepts">參加人數: </div>' +
	'<div class="unresponds">未回應報名: </div>' +
	'</div></a></li>';
var respondTemp = '<div class="respondAttend"><button type="button" value="accept">接受</button><button type="button" value="reject">拒絕</button></div>';

var activityid;

$(function () {
	
	$('#blocklist').on('click','.panel',function(){
		var e = $(this);
		e.addClass('panel-choose').siblings(".panel-choose").removeClass('panel-choose');
	})
	
	var lis = $('.setting4ul li');
	var friendsearch = $('.friendsearch');
	
	//check url 有無 params
	var showattend = new URL(window.location.href).searchParams.get("attendid");
	$('#left .content_xx li:eq(0)').addClass('choose').siblings(".choose").removeClass('choose');
	
	if( showattend != null && showattend != ""){
		$('#right>div:first-child').children('.content_xx').hide().next().show();
		friendsearch.hide();
		lis.eq(1).addClass('choose').siblings(".choose").removeClass('choose');
		$.get(
			'/wegether/attend/'+showattend
			,function(data){
				var div = pushAttend(data, 1);
				div.addClass('panel-choose')
				divList.append(div);
				div.find('.collapse').collapse();
			}
			,'json')
	}else{
		clickContent_xx(0);
	}
	
	
	//左邊選單
	$('#left .content_xx').on('click', '.changePage', function () {
		console.log(123);
		var li = $(this);
		var i = li.index();
		li.addClass('choose').siblings(".choose").removeClass('choose');
		clickContent_xx(i);
	})
	

	var lis = $('.setting4ul li');

	$('#right .content_xx:eq(0)').on('click', 'li button', function () {
		var e = $(this);
		e.parents('ul').eq(0).hide().next().show();
		activityid = e.attr('activityid');
		switchContent(0);
	})

	
	
	$('.setting4ul li').click(function () {
		var li = $(this);
		var i = li.index();
		switchContent(i);
	})

	function switchContent(i) {
		lis.eq(i).addClass('choose').siblings(".choose").removeClass('choose');
		switch (i) {
			case 0:
				friendsearch.hide();
				readAttend('/wegether/attend/accepted/activity/' + activityid, 0);
				break;
			case 1:
				friendsearch.hide();
				readAttend('/wegether/attend/unrespond/activity/' + activityid, 1);
				break;
			case 2:
				friendsearch.show();
				readAttend('/wegether/attend/invite/activity/' + activityid, 2);
				break;
		}
	}
	var divList = $('#attendlist');

	///attend詳細
	function readAttend(url, type) {
		divList.html("");
		$.post(
			url
			, function (data) {
				$.each(data, function (i, e) {
					
					divList.append(pushAttend(e, type));
				})
			}
			, 'json'
		);
	}
	
	function pushAttend(e, type){
		var attenddiv = $(attendTemp);
		attenddiv.find('.memberPhoto img').attr('scr', '/wegether/member/photo/' + e.memberid);
		attenddiv.find('.nickname').html(
			$('<a/>').text(e.nickname).attr('href', '/wegether/personal.controller?memberId=' + e.memberid).bind('click', function (event) {
				event.stopPropagation();
			}));
		var attcoll = $(attendcollapse);
		if (e.applyForm != 'default') {
			var formjson = jQuery.parseJSON(e.applyForm);
			var panelbody = attcoll.find('.panel-body');
			$.each(formjson, function (j, a) {
				var div = $('<div/>').append($('<h3/>').text(a.title));
				var answer = $('<div/>')
				if (a.answer != null && a.answer != "") {
					if (Array.isArray(a.answer)) {
						if (a.answer.length != 0) {
							$.each(a.answer, function (i, e) {
								answer.append($('<p/>').text(e));
							})
						} else {
							answer.append($('<p/>').text('無回答'));
						}
					} else {
						answer.append($('<p/>').text(a.answer));
					}
				} else {
					answer.append($('<p/>').text('無回答'));
				}
				div.append(answer);
				panelbody.append(div);
			})
			
		}
		
		if(type == 1) {
			 attcoll.find('.panel-body').append( $(respondTemp).attr('attendid', e.attendid));
		}
		if (e.applyForm != 'default' || type == 1){
			var attendid = 'attendlistid' + e.attendid;
			attcoll.attr('id', attendid);
			attenddiv.find('.attend').attr('href', '#' + attendid);
			attenddiv.append(attcoll);
		}
		
		if(type == 2){
			attenddiv.find('.attend').append($('<button value="cancel"/>').text('取消邀請')).attr('attendid',e.attendid);
		}
		return attenddiv;
		
	}

	divList.on('click','.panel button',function(){
		e = $(this);
		$.post('/wegether/attend/' + e.val() + '/'
				+ e.parent().attr('attendid'), function() {
			e.parents('.panel-default').remove();
		}, 'json')
	})


	function clickContent_xx(i) {

		var ul = $('#right').children().hide().eq(i).show().find('.content_xx').show();
		if (i == 0)
			ul.next().hide();

		ul.html("");
		$.post(
			'/wegether' + reviewUrl[i]
			, function (data) {
				$.each(data, function (index, e) {
					var li = $(liTemp);
					li.children('a').attr('href', '/wegether/activityPage.controller?actid=' + e.activityid)
					.bind('click', function (event) {
						event.stopPropagation();
					});
					li.find('.title').text( e.title
//						$('<a/>').text(e.title)
//							.attr('href', '/wegether/activityPage.controller?actid=' + e.activityid)
//							.bind('click', function (event) {
//								event.stopPropagation();
//							})
							);
					if (i != 0 && i != 4) {
						li.find('.host').append(document.createTextNode(e.hostname));
					} else {
						li.find('.host').remove();
					}
					var date = new Date(e.actbegin);
					li.find('.actbegin').append(
						document.createTextNode(
							date.customFormat('#MM#/#DD# #hhh#:#mm# #AMPM#')));
					li.find('.accepts').append(document.createTextNode(e.accepts));
					if (i == 0) {
						li.find('.unresponds').append(document.createTextNode(e.unresponds));
					} else {
						li.find('.unresponds').remove();
					}
					switch (i) {
						case 0:
							li.append(
									$('<button>').text(reviewButton[i]).attr('activityid', e.activityid)
									);
							break;
						case 1:
						case 2:
							li.append(
									$('<button>').text(reviewButton[i]).val('cancel')
									).attr('activityid', e.activityid);
							break;
						case 3:
							li.append(
									$('<button>').text("接受").val('invite/accept')
								).append(
									$('<button>').text("拒絕").val('invite/reject')
								).attr('activityid', e.activityid);
					}
					ul.append(li);
				})
			}
			, 'json'
		)
	}
	
	$('#right .content_xx').slice( 1, 4 ).on('click', 'li>button', function () {
		var e = $(this);
		$.post(
			'/wegether/attend/' + e.val() + '/activity/'+ e.parent().attr('activityid')
			, function() {
				e.parent().remove();
			}
			, 'json'
		)
	})
	
	//好友列表
	



})


	function friendbuttonDo(e){
		e=$(e)
		var url = '/wegether/activity/'+activityid+'/'+e.val()+'/'+e.parent().attr('friendid');
		console.log(url)
//		$.post(
//			url
//			,function(data){
//				e.parent().parent().remove();
//				friendList.css('min-height', (count* listhight) + "px");
//			}
//		)	
	}