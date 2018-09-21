//身分驗證	
//style="color:red;width:250px;height:35px"
var buttonTemp = '<button type="button" class="btn btn-warning" style="width:250px;height:35px"></button>';

function idCheck(data) {
	// 1:主辦人 2:已報名者 3:未報名者
	var memBut = $('#memBut');
	memBut.html('');
	if (data.host) {
		console.log("actState:"+actState);
		if(actState==0){
			memBut.append($(buttonTemp).text('編 輯 活 動 ').click(function() {
				document.location.href = "actEdit.getBean.controller?actid="+activityid;
			}))
		}else if(actState==1){
			 console.log("state5="+actState)
//			 $("#stardiv1").show();
			 $("#attendShare").show();
			
		}
	}else if(data.state != null) {
		// --報名狀態 0:報名中 1:報名成功 2:報名失敗 3:活動主辦人邀請s
		memBut.attr("attendid",data.attendid);
		switch (data.state) {
			case 0:
				memBut.append($(buttonTemp).text('報名審核中...'))
				break;
			case 1:
				if(actState==0){
				memBut.append($(buttonTemp).text('取消參加').click(function() {
					console.log("取消參加:"+data.attendid);
					 $.ajax({
						  url: 'attend/cancel/'+data.attendid,
						  type: 'DELETE',
						  success:function(){	
							  loginDo();
						  }
						});
				}))
				}else if(actState==1){
					
					rankgo();
					console.log("rankgo1")
					
				}
				break;
			case 2:
				memBut.append($(buttonTemp).text('報名失敗'));
				break;
			case 3:
				memBut.append($(buttonTemp).text('接受邀請').val('accept'));
				memBut.append('<br><br>');
				memBut.append($(buttonTemp).text('拒絕邀請').val('reject'));
				memBut.on('click', 'button', function() {
					$.post(
							'/wegether/attend/invite/' + e.val() + '/'+ parent.attr('attendid')
							, function() {
								loginDo();
							}
							, 'json'
						)
				});
				break;
		}

	}else{
		memBut.append($(buttonTemp).text('報名').click(function(){
			clickApplyForm(activityid);
		}));
	}

}

// 登入身分驗證 END
