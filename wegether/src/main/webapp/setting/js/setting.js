var iframe;
$(document).ready(function () {
	iframe = $('iframe');

	var page = new URL(window.location.href).searchParams.get("page");
	
	if( page != null && page != ""){
		$('#list li').eq(page).addClass('choose').siblings(".choose").removeClass('choose');
		loadIframe('./setting/' + (parseInt(page)+1) + '.html')
	}

	$("#list").on('click', 'li', function () {
		var div = $(this);
		div.siblings(".choose").removeClass('choose');
		div.addClass('choose');
		var i = div.index() + 1;
		loadIframe('./setting/' + i + '.html')
	});
	
	$('#setting').bind('resize', function () {
		console.log('Height changed to' + $(this).height());
	});
	
	$('#searchBox').on('click','.friendButton button',function(){		
    	var e = $(this);
    	var friendlist = e.parents('.friendList');
    	$.post(
    		friendlist.attr('url')
    		,friendlist.attr('param')+e.parent().attr('memberid')
    		,function(data){
    			if(data.state){
    				var iframe = e.parents('body').find('iframe')[0];	
    				iframe.src = friendlist.attr('param') == 'memberidf=' ?  '/wegether/setting/setting/4.html?page=2': iframe.src;
    				e.parent().parent().remove();		
    			}
    		}
    		,'json'
    	)
    })

})

function loginDo(){
	goPage();
}

function loadIframe(url) {
	iframe.attr('src', url);
}

function logoutDo() {
	location.replace("/wegether/index.jsp");
}


function goPage(){
	var page = new URL(window.location.href).searchParams.get("page");
	
	if( page != null && page != ""){
		$('#list li').eq(page).addClass('choose').siblings(".choose").removeClass('choose');
		loadIframe('./setting/' + (parseInt(page)+1) + '.html')
	}else{
		loadIframe('./setting/1.html');
	}
}
function loginfail(){
	location.replace("/wegether/index.jsp");
}

