	$(function() {
		$.ajax({
			url:'/wegether/headertemp.html',
			success:function(data){
				$('#tempCon').html(data);
			}
		})
		$.ajax({
			url:'/wegether/footertemp.html',
			success:function(data){
				$('#footerC').html(data);
			}
		})
		$('#header_nav ul li').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
		})
	})	
	