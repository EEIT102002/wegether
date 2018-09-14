function loginDo(){
	var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
			,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖']
	var PageContext = $("#PageContext").val();
	var str1="";
	$.ajax(PageContext+"/activity.login",{
			method:"GET",
			success:function(jsonArray){
				var i = 0;
		        $.each(jsonArray, function() {
		        var date = new Date(jsonArray[i][0].actbegin);
					Y = date.getFullYear() + '-';
					M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
					D = date.getDate() + ' ';
				
					str1+="<div class='col-xs-12 col-sm-6 col-md-4 item'><figure>";
	        	 str1 += "<img alt='' class='img-responsive' src=\""+jsonArray[i][1]+"\"/>";
	        	str1+="<figcaption>";
        		str1+="活動名稱:"+jsonArray[i][0].title+"<br>"+"活動地點:"+array_for_city[jsonArray[i][0].city]+"<br>"+"活動時間:"+(Y+M+D)+"<br>"+"活動ID:"+jsonArray[i][0].id+"<br>"+"主揪:"+jsonArray[i][2]+"<br>";
        		str1+="</figcaption><figure></div>";
	        	
		        i++;
		        });
		        $("#show_act_area").empty();
		        $("#show_act_area").append(str1);
			}
		})//ajax end
	$('.header_nav>ul>li:eq(0)').addClass('active');
	$.ajax(PageContext+"/member/Info/index",{
		method:"get",
		success:function(data){
//			alert('A');
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

}

function logoutDo() {
	alert(123);
}
