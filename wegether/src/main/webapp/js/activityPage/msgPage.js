	
$(function(){
	console.log("msgPage")
	
})

//取得留言功能
	function getMsgs(act,mem,sta,cont){
	console.log("msgPage in:"+" actId="+act+" memId="+mem+" state="+sta+" msg="+cont);
	 var divElem = null ;
	 var temp="";
	 $.get("msgs.controller",
			 { activityid:act, 
		 		memberid:mem,
		 		state:sta,
		 		content:cont
			 },
			 function(result){	
	 			$.each(result, function(i,item){	
	 				divElem =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'">'+
					'<img src="data:image/jpg;base64,'+item[1]+'" width="50" class="img-circle">  </a>' +
					'<span style="color: blue;">'+item[2]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[3]+'</span>'+
					'<button id="deleteId" class="btn btn-danger" msgid='+item[5]+'>刪除</button>'+			
			 		'</br>'+item[4]+'</br>'+
					"</div>");
	 				temp = temp + divElem;
	 			});	
	 			$('#demo').html(temp);
	 });	
	 
 }
	//取得留言功能 END	

//新增留言功能
function postMsgs(act,mem,sta,cont){
console.log("postMsgs in:"+" actId="+act+" memId="+mem+" state="+sta+" msg="+cont);
 var divElem = null ;
 var temp="";
 $.post("msgs.controller",
		 { activityid:act, 
	 		memberid:mem,
	 		state:sta,
	 		content:cont
		 },
		 function(result){	
 			$.each(result, function(i,item){	
 				divElem =("<div id='msgid'>" +
				'<a href="personal.controller?memberId='+item[0]+'">'+
				'<img src="data:image/jpg;base64,'+item[1]+'" width="50" class="img-circle">  </a>' +
				'<span style="color: blue;">'+item[2]+'</span> &emsp; '+
				'<span style="font-size: small;">'+item[3]+'</span>'+
				'<button id="deleteId" class="btn btn-danger" msgid='+item[5]+'>刪除</button>'+			
		 		'</br>'+item[4]+'</br>'+
				"</div>");
 				temp = temp + divElem;
 			});	
 			$('#demo').html(temp);
 });	
 
}
//新增留言功能 END	

//刪除留言功能
function deleteMsgs(act,mem,sta,cont){
	console.log("deleteMsgs in:"+" actId="+act+" memId="+mem+" state="+sta+" msgId="+cont);
	 var divElem = null ;
	 var temp="";
	 $.ajax({
		  url: 'msgs.controller/'+cont,
		  type: 'DELETE',
		  data: {
			  	activityid:act, 
		 		memberid:mem,
		 		state:sta,
		 		content:cont
		  },
	      success:function(result){	
	 			$.each(result, function(i,item){	
	 				divElem =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'">'+
					'<img src="data:image/jpg;base64,'+item[1]+'" width="50" class="img-circle">  </a>' +
					'<span style="color: blue;">'+item[2]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[3]+'</span>'+
					'<button id="deleteId" class="btn btn-danger" msgid='+item[5]+'>刪除</button>'+			
			 		'</br>'+item[4]+'</br>'+
					"</div>");
	 				temp = temp + divElem;
	 			});	
	 			$('#demo').html(temp);
//	    	  console.log("success="+result);
	 }
		});
	 
	}
	//刪除留言功能 END	
	