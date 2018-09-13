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
	// 當下層iframe自身完成高度調整後, 再通知上層去調整高度, 直到每一層都完成高度調整.
}

function searchServer(formData, url, rowtemp){
	var searchbox = window.parent.$('#searchBox');
	var minbody = searchbox.find('.friendList');
    $.post({
        url: url,
        data: formData,
        success: function(data){
            minbody.html("");
            $.each(data, function(i, e){
                var row = $(rowtemp);
                row.find(".name").html(e.nickname);
                if (e.photo != null)
                    row.find('img').attr('src', e.photo);
                row.attr('href', e.memberid);
                minbody.append(row);
                searchbox.modal();
            })
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
        	minbody.html("");
        	minbody.append($('<p/>').text("沒有查詢結果"));
        	searchbox.modal();
        },
        dataType:"json"
    })
}