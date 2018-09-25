//載入心得   
 function getArticles(activityid){
	 var divElem1 = null ;
	 var divElem = null ;
	 var temp="";
	 var tempMsg="";
	 var getArticlesid =null;
	 
	 $.get("article.controller/"+activityid,
			 function(result){	
	 			$.each(result, function(i,item){
	 				//心得分享
	 				divElem1 =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
					'<img src="/wegether/member/photo/'+item[0]+'" class="img-circle" width="70" height="70">  </a>' +
					'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[2]+'</span>'+
					'<button id="responseId" class="btn btn-primary" articleid='+item[4]+'>回覆心得</button>'+
// 					'<button id="deleteId" class="btn btn-danger" articleid='+item[4]+'>刪除心得</button>'+
			 		'</br>'+item[3]+'</br>'
			 		);
	 				
	 				//照片
	 				 var divElem2 = null ;
	 				 var temp1="";
	 				$.each(item[5], function(i,item){
	 					divElem2 =(
	 					 '<img src="/wegether/picture/'+item+'" width="350" height="250" style=" border: 2px solid #272727; margin: 10px;">' 
	 					);
	 					temp1 = temp1 + divElem2;
	 				});	
	 				
	 				//留言
	 				 var divElem3 = null ;
	 				var temp2="";
	 				$.each(item[6], function(i,item){	
		 				divElem3 =("<div id='msgid'>" +
						'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
						'<img src="/wegether/member/photo/'+item[0]+'" width="50" height="50" class="img-circle">  </a>' +
						'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
						'<span style="font-size: small;">'+item[2]+'</span>'+
						'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除留言</button>'+			
				 		'</br>'+item[3]+'</br>'+
						"</div>");
		 				temp2 = temp2 + divElem3;
		 			});	
	 				
	 				
 	 				temp = divElem1 + temp1 + '<hr><div id=articleMsgs'+item[4]+'>'+ temp2+'</div></div>' + temp ;
	 				

	 				
	 			});	
	 			$('#demoArticle').html(temp);
	 			
	 		});	
	 
		}
	 


//刪除心得功能
function deleteArticles(articleid){
	 var divElem = null ;
	 var temp="";
	 $.ajax({
		  url: 'article.controller/'+articleid,
		  type: 'DELETE',
	      success:function(result){	
	 			$.each(result, function(i,item){	
	 				divElem1 =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
					'<img src="/wegether/member/photo/'+item[0]+'"class="img-circle" width="70" height="70"> </a>' +
					'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[2]+'</span>'+
					'<button id="responseId" class="btn btn-primary" articleid='+item[4]+'>回覆心得</button>'+
// 					'<button id="deleteId" class="btn btn-danger" articleid='+item[4]+'>刪除心得</button>'+			
			 		'</br>'+item[3]+'</br>'
			 		);
	 				 var divElem2 = null ;
	 				 var temp1="";
	 				$.each(item[5], function(i,item){
	 					divElem2 =(
	 					 '<img src="/wegether/picture/'+item+'" width="350" height="250" style=" border: 2px solid #272727; margin: 10px;">' 
	 					);
	 					temp1 = temp1 + divElem2;
	 				});	
	 				temp = divElem1 + temp1 + '<hr><div id="articleMsgs"></div></div>' + temp ;
	 			});	
	 			$('#demoArticle').html(temp);
	 }
		});
	 
	}
	
	//回覆心得分享
	function  responseArticles(articleid,memberid,articleMsg){
		console.log("articleid:"+articleid +" memberid:"+memberid+" articleMsg:"+articleMsg);
		 var divElem = null ;
		 var temp="";
		 $('#dialogTxt').val('');
		 $.post('articlemsgs.controller',
				 {
			 		articleid:articleid,
			 		memberid:memberid,
			 		content:articleMsg,
				 },
				 function(result){	
					 console.log("responsemsg:");
			 			$.each(result, function(i,item){	
			 				divElem =("<div id='msgid'>" +
							'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
							'<img src="/wegether/member/photo/'+item[0]+'" width="50" class="img-circle">  </a>' +
							'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
							'<span style="font-size: small;">'+item[2]+'</span>'+
 							'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除留言</button>'+			
					 		'</br>'+item[3]+'</br>'+
							"</div>");
			 				temp = temp + divElem;
// 			 				console.log("temp:"+item[4]);
			 			});	
			 			
			 			$('#articleMsgs'+articleid).html(temp);
			 });	
		}
