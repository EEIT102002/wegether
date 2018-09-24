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
	$.ajax({
		url:'/wegether/personal.controller',
		data:{
			memberId: "5",
			id: memberInfo[0][0].id
		},
		success:function(data){
//			console.log(data)
		}
	})
}