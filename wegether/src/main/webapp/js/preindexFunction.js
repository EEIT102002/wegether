function indexSelectFromDB(jsonArray){
	var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
		,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖'];
	var i = 0;
	var str1="";
    $.each(jsonArray, function() {
	var date = new Date(jsonArray[i][0].actbegin);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	str1 +="<div class='col-xs-12 col-sm-6 col-md-4 item'><a href='personal.controller?memberId='"+jsonArray[i][3]+"'><figure style='display:flex;margin:5px 0;' ><img alt='' class='img-circle img-responsive' style='height: 50px;width:50px;' src=\""+jsonArray[i][3]+"\"/><figcaption style='line-height:50px;flex:0.8;padding-left:20px;'>"+jsonArray[i][2]+"<input type='hidden' value='"+jsonArray[i][4]+"' class='hidememid'/></figcaption></figure></a><figure><a href='activityPage.controller?actid="+jsonArray[i][0].id+"'>";
	str1 += "<img alt='' class='img-responsive' style='border-radius:5px;' src=\""+jsonArray[i][1]+"\"/></a>";
	str1+="<figcaption style='margin-top:5px;'>";
	str1+="活動名稱:"+jsonArray[i][0].title+"<br>"+"活動地點:"+array_for_city[jsonArray[i][0].city-1]+"<br>"+"活動時間:"+(Y+M+D)+"<br>"+"<p style='text-overflow:ellipsis;white-space : nowrap; overflow : hidden;'>活動內容:"+jsonArray[i][0].content+"</p>";
	str1+="</figcaption></figure><a href='activityPage.controller?actid='"+jsonArray[i][0].id+"'><button class='btnAct btn btn-primary pull-right'>詳細資訊</button></a></div>";
	i++;
    });
    $("#show_act_area").empty();
    $("#show_act_area").append(str1);
}
