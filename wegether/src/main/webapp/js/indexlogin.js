function loginDo(){
	var PageContext = $("#PageContext").val();
	var str1="";
	$.ajax(PageContext+"/activity/login",{
			method:"GET",
			success:function(jsonArray){
				indexSelectFromDB(jsonArray);
			}
		})//ajax end
	$('.header_nav>ul>li:eq(0)').addClass('active');
	$.ajax(PageContext+"/member/Info/index",{
		method:"get",
		success:function(data){
		
//			console.log(data);
//			console.log(data[0]);
//			console.log(data[0][1]);
//			console.log(data[0][0].nickname);
//			console.log(data[1]);
//			console.log(data[2]);
//			console.log(data.nickname);
			$('#dropdownMenuButton>#userinfo').html(data[0][0].nickname);
			$('#dropdownMenuButton>img').attr('src',data[0][1]);
		}
	})
	$('.header_nav>ul li:eq(1)>a').removeAttr('data-toggle');
	$('.header_nav>ul li:eq(1)>a').attr('href')
	$('.header_nav>ul li:eq(2)>a').removeAttr('data-toggle');
	// 把正確的連結放進去
	
}

function logoutDo() {
	alert("期待下次見到你/妳");
//	$('.header_nav>ul>li:eq(0)').addClass('active');
}
