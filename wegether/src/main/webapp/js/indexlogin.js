function loginDo(){
	$.ajax("/wegether/activity/login",{
			method:"GET",
			success:function(jsonArray){
				indexSelectFromDB(jsonArray);
			}
		})
}

function logoutDo() {
}
