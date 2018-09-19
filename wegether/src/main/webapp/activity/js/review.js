var reviewUrl=['/member/activity/host/now'
	,'/member/activity/attend/now'
	,'/member/activity/applying'
	,'/member/activity/beInvited'
	,'/member/activity/host/history'
	,'/member/activity/attend/history'
];

var reviewButton=[
	'申請與參加者'
	,'取消參加'
	,'取消申請'
]

var liTemp = '<li class="btn btn-secondary activityli"><div>'+
	'<div class="title"></div>'+
		'<div class="info">'+
			'<div class="host">主辦人: </div>'+
			'<div class="actbegin">活動時間:</div>'+
			'<div class="accepts">參加人數:</div>'+
			'<div class="unresponds">未回應報名:</div>'+
	'</div></div></li>';
$(function() {
	$('#header_nav ul li').click(function() {
		$(this).addClass('active').siblings().removeClass('active');
	})
	clickContent_xx(0);

	$('#left .content_xx').on('click', '.changePage', function() {
		var i = $(this).index();
		clickContent_xx(i);
	})
	var activityid;
	
	var lis = $('.setting4ul li');
	
	$('#right .content_xx:eq(0)').on('click', 'li button', function() {
		var e = $(this);
		e.parents('ul').eq(0).hide().next().show();
		activityid= e.attr('activityid');
		switchContent(0);
	})
	
	friendsearch = $('.friendsearch');
	$('.setting4ul li').click(function () {
		var li = $(this);
		var i = li.index();
		switchContent(i);
	})
	
	function switchContent(i){
		lis.eq(i).addClass('choose').siblings(".choose").removeClass('choose');
		switch (i) {
		case 0:
			friendsearch.hide();
			readAttend('/wegether/attend/accepted/activity/'+activityid,0);
			break;
		case 1:
			friendsearch.hide();
			readAttend('/wegether/attend/unrespond/activity/'+activityid,1);
			break;
		case 2:
			friendsearch.show();
			readAttend('/wegether/attend/invite/activity/'+activityid,2);
			break;
		}
	}
	var friendList = $('.friendList');
	
	function readAttend(url,type){
		friendList.html("");
		$.post(
			url
			,function(data){
				$.each(data, function(i,e){
					friendList.append($('<div/>').text(e.nickname));
				})
			}
			,'json'
			);
	}
	

	function clickContent_xx(i) {
		
		var ul = $('#right').children().hide().eq(i).show().find('.content_xx').show();
		if(i == 0)
			ul.next().hide();
		
		ul.html("");
		$.post(
			'/wegether'+reviewUrl[i]
			,function(data){
				$.each(data,function(index,e){
					var li = $(liTemp);
					li.find('.title').text(e.title);
					if(i != 0 && i != 4){
						li.find('.host').append(document.createTextNode(e.hostname));
					}else{
						li.find('.host').remove();
					}
					var date = new Date(e.actbegin);
					li.find('.actbegin').append(
							document.createTextNode(
									date.customFormat('#MM#/#DD# #hhh#:#mm# #AMPM#')));
					li.find('.accepts').append(document.createTextNode(e.accepts));
					if(i== 0){
						li.find('.unresponds').append(document.createTextNode(e.unresponds));
					}else{
						li.find('.unresponds').remove();
					}
					switch(i){
						case 0:
						case 1:
						case 2:
							li.append($('<button>').text(reviewButton[i]).attr('activityid',e.activityid));
							break;
						case 3:
							li.append($('<button>').text("接受")).append($('<button>').text("拒絕"));
					}
					ul.append(li);
				})
			}
			,'json'
		)
	}

})