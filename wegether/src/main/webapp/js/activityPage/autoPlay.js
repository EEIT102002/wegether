//activityPage 輪播使用
	var picNo = 1;
	var timerObj = setInterval(autoPlay, 1500);
	function autoPlay() {
		picNo++;
		if (picNo > actPicListSize ) picNo = 1;
		selectPic();
	}

	function selectPic() {
		for (var i = 1; i <= actPicListSize ; i++)
			 $("#imd" + i).attr("style","border:2px solid #FFBB00");
 		 $("#imd" + picNo).attr("style","border:3px solid red");
		 $("#imd0").attr("src",$("#imd" + picNo).attr("src"));
	}
	//activityPage 輪播使用  end	