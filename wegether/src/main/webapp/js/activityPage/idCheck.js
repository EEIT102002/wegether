//身分驗證	

var buttonTemp = '<button type="button" class="btn btn-warning" ></button>';

function idCheck(data) {
	// 1:主辦人 2:已報名者 3:未報名者
	var memBut = $('#memBut');
	memBut.html('');
	if (data.host) {
		$('#memBut')
				.text('編 輯 活 動 ')
				.click(
						function() {
							console.log(attflag);
							document.location.href = "actEdit.getBean.controller?actid="+activityid;
						});
	}else if(data.state != null) {
		// --報名狀態 0:報名中 1:報名成功 2:報名失敗 3:活動主辦人邀請s
		memBut.attr("attendid",data.attendid);
		switch (data.state) {
			case 0:
				memBut.append($(buttonTemp).text().click(function() {
	
				}))
				break;
			case 1:
				memBut.append($(buttonTemp).text('取消參加').click(function() {
	
				}))
				break;
			case 2:
				memBut.append($(buttonTemp).text('失敗'));
				break;
			case 3:
				memBut.append($(buttonTemp).text('接受邀請').val('accept'));
				memBut.append($(buttonTemp).text('拒絕邀請').val('reject'));
				memBut.on('click', 'button', function() {
					$.post(
							'/wegether/attend/' + e.val() + '/'+ parent.attr('attendid')
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
