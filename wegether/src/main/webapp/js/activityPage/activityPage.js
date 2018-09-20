$(function(){
	
	if(state==0){
		console.log("state3="+state)
		 $("#demoArticle").hide();
		 $("#starBlock").hide();
		 $("#msgButId").hide();	//留言按鍵
		 $("#articleButId").hide(); //心得分享按鍵   
	}else if(state==1){
		 console.log("state4="+state)
		 $("#msgBlock").hide();
	}
	
})



