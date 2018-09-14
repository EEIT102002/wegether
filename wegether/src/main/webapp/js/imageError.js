$(document).ajaxComplete(function(){
    $("img").on('error',imageRemove);
});

$(function(){
	 $("img").on('error',imageRemove);
})

function imageRemove(){
	$(this).remove();
}