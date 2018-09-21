$(function(){
	
	if(actState==0){
		console.log("state3="+actState)
		 $("#demoArticle").hide();
		 $("#starBlock").hide();
		 $("#msgButId").hide();	//留言按鍵
		 $("#articleButId").hide(); //心得分享按鍵   
		
		 
	}else if(actState==1){
		 console.log("state4="+actState)
		 $("#msgBlock").hide();
		 $('#memBut').hide(); //報名按鍵
		 $("#stardiv1").hide();
		 $("#stardiv2").hide();
		 $("#stardiv3").hide();
		 $("#startSumit").hide();
		 $("#attendShare").hide();
	}
	
})



