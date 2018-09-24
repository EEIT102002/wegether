function loginDo(){
	$.ajax("/wegether/member/Info/index",{
		method:"get",
		success:function(memberInfo){
//			$('#dropdownMenuButton>#userID').val(data[0][0].id);
			connectTo(memberInfo)
		}
	})
}

function logoutDo() {

}
function connectTo(memberInfo){
	alert('connectTo:'+memberInfo[0][0].id)
	$.ajax({
		url:'/wegether/personal.controller',
		data:{
			memberId: "5",
			id: "5"
		},
		success:function(data){
//			console.log(data)
		}
	})
}