

//取得留言功能
	function getMsgs(activityid){
	 var divElem = null ;
	 var temp="";
//	 console.log("activityid:"+activityid)
	 $.get("msgs.controller/"+activityid,
			 function(result){	
	 			$.each(result, function(i,item){	
	 				divElem =("<div id='msgid'>" +
					'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
					'<img src="/wegether/member/photo/'+item[0]+'" class="img-circle" width="70" height="70">  </a>' +
					'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[2]+'</span>'+
					'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除留言</button>'+			
			 		'</br>'+item[3]+'</br>'+
					"</div>");
	 				temp = temp + divElem;
	 			});	
	 			$('#demo').html(temp);
	 			
	 });	
	 
 }
	
	//取得留言功能 END	
//	$('#demo').html("<h3 style='color:black'>目前沒有留言</h3>")
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
				'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
				'<img src="/wegether/member/photo/'+item[0]+'" width="70"  height="70" class="img-circle">  </a>' +
				'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
				'<span style="font-size: small;">'+item[2]+'</span>'+
				'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除留言</button>'+			
		 		'</br>'+item[3]+'</br>'+
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
					'<a href="personal.controller?memberId='+item[0]+'"  style="text-decoration:none;">'+
					'<img src="/wegether/member/photo/'+item[0]+'" width="70"  height="70" class="img-circle">  </a>' +
					'<span style="color: blue;">'+item[1]+'</span> &emsp; '+
					'<span style="font-size: small;">'+item[2]+'</span>'+
					'<button id="deleteId" class="btn btn-danger" msgid='+item[4]+'>刪除留言</button>'+			
			 		'</br>'+item[3]+'</br>'+
					"</div>");
	 				temp = temp + divElem;
	 			});	
	 			$('#demo').html(temp);
//	    	  console.log("success="+result);
	 }
		});
	 
	}
	//刪除留言功能 END	
	