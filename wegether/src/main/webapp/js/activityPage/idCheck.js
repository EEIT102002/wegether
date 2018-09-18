//身分驗證	
	function idCheck(attNo,state){
		console.log("idCheck attNo="+attNo+" /idCheck state="+state)
		// 1:主辦人  2:已報名者  3:未報名者
		if(attNo == 1){
			$('#memBut').text('編 輯 活 動 ').click(function(){
				console.log(attflag);
				document.location.href="actEdit.getBean.controller?actid=${actBean.id}";
			});
		}
		if(attNo == 2){
//			--報名狀態 0:報名中 1:報名成功 2:報名失敗 3:活動主辦人邀請
			switch(state) {
			    case 0:
			    	$('#memBut').text('報名審核中...請耐心等候!').click(function(){
						
					});
			        break;
			    case 1:
			    	$('#memBut').text('取 消 報 名 ').click(function(){
						
					});
			        break;
			    case 2:
			    	$('#memBut').text('報 名 失 敗 ').click(function(){
						
					});
			        break;
			    case 3:
			    	$('#memBut').text('活動主辦人邀請 ').click(function(){
			    		clickApplyForm(activityid);
					});
			        break;
			    default:
			    	$('#memBut').text('取 消 報 名 ').click(function(){
						
					});
			}
			
		}
		if(attNo == 3){
			$('#memBut').text('報 名 ').click(function(){
				clickApplyForm(activityid);
			});
		}
		
	}
	
	//登入身分驗證 END