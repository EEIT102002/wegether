$(document).ready(function() {
	$("#list").on('click', 'li', function() {
		var div = $(this);
		div.siblings(".choose").removeClass('choose');
		div.addClass('choose');
		var i = div.index() + 1;
		$('iframe').attr('src', './setting/' + i + '.html');

	});
	$('iframe').attr('src', './setting/1.html');
	$('#setting').bind('resize', function() {
		console.log('Height changed to' + $(this).height());
	});
})
