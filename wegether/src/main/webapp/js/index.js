$(function(){
		var array_for_city = ['基隆市', '台北市', '新北市','宜蘭縣','桃園市','新竹市'
			,'新竹縣','苗栗縣','台中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','台南市','高雄市','屏東縣','花蓮縣','台東縣','澎湖','金門','馬祖'];
		var PageContext = $("#PageContext").val();//因為JSP中可以使用EL語法 但 單純JS黨不行 所以當東西改成外部 必須 先取得當前路徑 
//		alert(PageContext);
		$.ajax(PageContext+"/activity",{
			method:"GET",
			success:function(jsonArray){
				indexSelectFromDB(jsonArray);
			}
		})//ajax end			
		for(var i = 1; i <= array_for_city.length; i++) {
//	            console.log(array_for_city[i-1]);
			var x1 ="<option value="+i+">"+array_for_city[i-1]+"</option>"		
			$('#CitySelect').append(x1);
		}
		$('.header_nav>ul li').click(function(){
			$(this).addClass('active').siblings().removeClass('active');
		})
		$('#CitySelect').change(function(){
//			var x = $('#CitySelect').find('option:selected').index();
//			$('#CitySelect').find('option:selected').attr("value",x);
//			alert("此選項的中文:"+$('#CitySelect').find('option:selected').html());
			})

		$('#form_act').submit(function(){
			$('html,body').animate({scrollTop:$('#about').offset().top},800);
			var str1="";
			$.ajax(PageContext+"/select_activity",{
				method:"GET",
				data:$('#form_act').serialize(),
				success:function(jsonArray){
// 					alert("Act")
//					console.log(jsonArray[0]);
					if(jsonArray[0]=="查無符合資料"){
// 						alert("查無符合資料");
						var str2 ="<div class='col-xs-12 col-sm-6 col-md-4 item'><figure><img alt='' class='img-responsive'src='images/noresult.jpg'/><figcaption>";
						str2 += jsonArray[0]+"</figcaption></figure></div>";
						$("#show_act_area").html(str2);
					}else{
						indexSelectFromDB(jsonArray);
					}//else end
				}//success end
			})//ajax end
			return false;	
			
		})//#form_act 提交

		$('#search_for_act').click(function(){
			$(this).css({
				"color": "#FFB445"
			})
//			var acthv = $('#acth').val();
////			alert(acthv);
//			$('#rankArea').val(acthv);
//			alert("rankArea"+$('#rankArea').val());
			$('#search_for_po').css({
				"color": "white"
			})
			$('#form_po').css({
				"display":"none"
			})
			$('#form_act').css({
				"display":"block"
			})

		})
		$('#search_for_po').click(function(){
			$(this).css({
				"color": "#FFB445"
			})
//			var pohv = $('#poh').val();
//			$('#rankArea').val(pohv);
//			alert("rankArea"+$('#rankArea').val());
			$('#search_for_act').css({
				"color": "white"
			})
			for(var i = 1; i <= array_for_city.length; i++) {
//		            console.log(array_for_city[i-1]);
				var x1 ="<option value="+i+">"+array_for_city[i-1]+"</option>"		
				$('#CitySelect_po').append(x1);
			}
			$('#form_po').css({
				"display":"block"
			})
			$('#form_act').css({
				"display":"none"
			})
		})
		
		$('#form_po').submit(function(){
			$('html,body').animate({scrollTop:$('#about').offset().top},800);
			var str1="";
			$.ajax(PageContext+"/select_po",{
				method:"GET",
				data:	$('#form_po').serialize(),
				success:function(jsonArray){
					if(jsonArray[0]=="查無符合資料"){
// 						alert("查無符合資料");
						var str2 ="<div class='col-xs-12 col-sm-6 col-md-4 item'><figure><img alt='' class='img-responsive'src='images/noresult.jpg'/><figcaption>";
						str2 += jsonArray[0]+"</figcaption></figure></div>";
						$("#show_act_area").html(str2);
					}else{
						indexSelectFromDB(jsonArray);
					}//else end
				}//success end
			})//ajax end
			return false;
		})//form_po end
		$('#upTop').click(function(){
			var body = $("html, body");
			body.stop().animate({scrollTop:0}, 500, 'swing');
			}).mouseover(function(){
				$(this).removeClass('animated');
			}).mouseout(function(){
				$(this).addClass('animated');
			})
			//日期限制
			$('#start_date').change(function() {
				var startTimeV = $('#start_date').val();
				var endTimeV = $('#end_date').val();
				$('#end_date').attr("min", startTimeV);
				$('#end_date').attr('max', '2200-08-31');
			 })
			$('#end_date').change(function() {
				var startTimeV = $('#start_date').val();
				var endTimeV = $('#end_date').val();
				$('#start_date').attr("max", endTimeV);
				$('#start_date').attr('min', '2000-08-31');
					  })

	})// script end
