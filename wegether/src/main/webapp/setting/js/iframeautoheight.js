var buttonVal =['提出邀請','追蹤','加入黑名單'];
var posturl = ['friend/invite','trackmember/add', 'blacklist/add'];
var param = ['memberidf=','memberid=','blackid='];
var header = ['新增好友','新增追蹤','新增黑名單'];
$(document).ready(function() {
	iframe_auto_height(); // 當文件ready時才能正確取得iframe內容的高度
	
	$('body').click(function() {
		iframe_auto_height();
	});
	
	$('#search').submit(function(){
    	if(typeof window.inputSearch === "function"){
    		inputSearch($(this).serialize());
    	}
        
    	return false;
    });
	
	

});


function createTemp(type){
	return '<div><a href=""><div><div class="photoDiv"><img src = ""alt=""></div><span class="name"></span></div></a><div class="friendButton"><button type="button">'
		+buttonVal[type]
		+'</button></div></div>';
}


function iframe_auto_height() {
	var iframe;
	$(parent.document).find("iframe").map(function() { // 找到自己的iframe
		if ($(this).contents().get(0).location == window.location)
			iframe = this;
	});
	if (!iframe)
		return;// no parent
	var content_height = $("html").height();
	var content_width = $("body").width();
	content_height = typeof content_height == 'number' ? content_height + "px"
			: content_height;
	content_width = typeof content_width == 'number' ? content_width + "px"
			: content_width;
	iframe.style.height = content_height;
	iframe.style.width = content_width;
}

function searchServer(formData, url, type){
	var temp = createTemp(type);
	var searchbox = window.parent.$('#searchBox');
	var minbody = searchbox.find('.friendList');
	minbody.attr('url', '/wegether/'+posturl[type]).attr('param',param[type]);
	
	minbody.html("");
	var modalheader = searchbox.find('.modal-header');
	
	
    $.post({
        url: url,
        data: formData,
        success: function(data){
            $.each(data, function(i, e){
                var row = $(temp);
                console.log(e.memberid)
                row.find(".name").html(e.nickname);
                row.find('img').attr('src', '/wegether/member/photo/'+e.memberid);
                row.find('a').attr('href', e.memberid);
                row.find('.friendButton').attr('memberid', e.memberid);
                minbody.append(row);
                searchbox.modal();
            })
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
        	minbody.append($('<p/>').text("沒有查詢結果"));
        	searchbox.modal();
        },
        dataType:"json"
    })
    var pathname = window.location.pathname;
    searchbox.on('click','.friendButton button',function(){
    	var e = $(this);
    	var friendlist = e.parents('.friendList');
    	$.post(
    		friendlist.attr('url')
    		,friendlist.attr('param')+e.parent().attr('memberid')
    		,function(data){
    			if(data.state){
    				var iframe = e.parents('body').find('iframe')[0];	
    				iframe.src = friendlist.attr('param') == 'memberidf=' ?  '/wegether/setting/setting/4.html?page=2': iframe.src;
    				console.log(iframe.src);
    				e.parent().parent().remove();		
    			}
    		}
    		,'json'
    	)
    })
}