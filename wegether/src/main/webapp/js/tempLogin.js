function loginheaderDO(){
	$('.header_nav>ul>li:eq(0)').addClass('active');
	$.ajax("/wegether/member/Info/index",{
		method:"get",
		success:function(data){
		
			$('#dropdownMenuButton>#userinfo').html(data[0][0].nickname);
			$('#dropdownMenuButton>img').attr('src',data[0][1]);

		}
	})
	$('.header_nav>ul li:eq(1)>a').removeAttr('data-toggle');
	$('.header_nav>ul li:eq(1)>a').attr('href','/wegether/ActivityCreate.jsp')
	$('.header_nav>ul li:eq(2)>a').removeAttr('data-toggle');
	$('.header_nav>ul li:eq(2)>a').attr('href','/wegether/ArticleCreate.jsp')
	// 把正確的連結放進去
}

function logoutheaderDo() {
	alert("期待下次見到你/妳");
//	$('.header_nav>ul>li:eq(0)').addClass('active');
}
