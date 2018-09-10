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
	loadIframe('./setting/1.html');
	$('#setting').bind('resize', function () {
		console.log('Height changed to' + $(this).height());
	});
})

function loadIframe(url) {
	iframe.attr('src', url);
}

function logoutDo() {
	alert(123);
}
