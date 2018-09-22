var iframe;
$(document).ready(function () {
	iframe = $('iframe');
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

})

function loginDo(){
	loadIframe('./setting/1.html');
}

function loadIframe(url) {
	iframe.attr('src', url);
}

function logoutDo() {
	location.replace("/wegether/index.jsp");
}

//function loginfail(){
//	location.replace("/wegether/index.jsp");
//}

