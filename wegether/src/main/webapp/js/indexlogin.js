function loginDo(){
	
	alert('loginDo');
	$.ajax("/wegether/activity/login",{
			method:"GET",
			success:function(jsonArray){
				indexSelectFromDB(jsonArray);
			}
		})
}

function logoutDo() {
//	$('.header_nav>ul>li:eq(0)').addClass('active');
	alert('logouDo inner')
}
