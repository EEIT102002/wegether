	
$(function(){
	console.log("articlePage")
	
})

//載入心得
 function getArticles(act,mem,sta,cont){
	console.log("ArticlePage in:"+" actId="+act+" memId="+mem+" state="+sta+" msg="+cont);
	 var divElem1 = null ;
	 var temp="";
	 $.get("article.controller",
			 { activityid:act, 
		 	   memberid:mem,
		 	   state:sta,
		 	   content:cont
			 },
			 function(result){	
	 			$.each(result, function(i,item){	
	 				divElem1 =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'">'+
					'<img src="data:image/jpg;base64,'+item[1]+'" width="50">  </a>' +
					'<span style="color: blue;">'+item[2]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[3]+'</span>'+
					'<button id="deleteId" href="#" class="btn btn-danger" msgid='+item[5]+'>刪除</button>'+			
			 		'</br>'+item[4]+'</br>'
			 		);
	 				 var divElem2 = null ;
	 				 var temp1="";
	 				$.each(item[6], function(i,item){
	 					divElem2 =(
	 					 '<img src="/wegether/picture/'+item+'" width="350" height="250" style=" border: 2px solid #272727; margin: 10px;">' 
	 					);
	 					temp1 = temp1 + divElem2;
	 				});	
	 				temp = divElem1 + temp1 + "</div>" + temp ;
	 			});	
	 			$('#demoArticle').html(temp);
	 });	
	 
 }

//刪除心得功能
function deleteArticles(act,mem,sta,cont){
	console.log("deleteArticles in:"+" actId="+act+" memId="+mem+" state="+sta+" msgId="+cont);
	 var divElem = null ;
	 var temp="";
	 $.ajax({
		  url: 'article.controller/'+cont,
		  type: 'DELETE',
		  data: {
			  	activityid:act, 
		 		memberid:mem,
		 		state:sta,
		 		content:cont
		  },
	      success:function(result){	
	 			$.each(result, function(i,item){	
	 				divElem1 =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'">'+
					'<img src="data:image/jpg;base64,'+item[1]+'" width="50">  </a>' +
					'<span style="color: blue;">'+item[2]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[3]+'</span>'+
					'<button id="deleteId" href="#" class="btn btn-danger" msgid='+item[5]+'>刪除</button>'+			
			 		'</br>'+item[4]+'</br>'
			 		);
	 				 var divElem2 = null ;
	 				 var temp1="";
	 				$.each(item[6], function(i,item){
	 					divElem2 =(
	 					 '<img src="/wegether/picture/'+item+'" width="350" height="250" style=" border: 2px solid #272727; margin: 10px;">' 
	 					);
	 					temp1 = temp1 + divElem2;
	 				});	
	 				temp = divElem1 + temp1 + "</div>" + temp ;
	 			});	
	 			$('#demoArticle').html(temp);
	 }
		});
	 
	}
	//刪除心得功能 END


	