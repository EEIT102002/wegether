$(function(){
	var rowtemp = '<div><a href=""><div class="memberPhoto"><div><img src="#" alt=""></div></div><span class="name"></span></a><div class="friendButton"><button type="button" value="invite">'+friendbuttonText+'</button></div></div>';
	var first = 0;
	var count = 0;
	var scrolltimes = 0;
	var listhight = 50;
	var friendList= $('#friendsearchBox .modal-body>div');
	var scrollDiv = friendList.parent();
	
	$(document).on('click','.friendButton button',function(){
		friendbuttonDo(this);

	})
	
	scrollDiv.scroll(function () {
		if (scrolltimes == 0 && first != 0 && scrollDiv.scrollTop() > (first * listhight)-500 && first % 20 == 0) {
			scrolltimes = 1;
			addfriendList(rowtemp, "/wegether/member/Friend/", friendList);
		}
	})
	
	$('#friendsearchButton').click(function(){
		var searchbox = $('#friendsearchBox');
		searchbox.modal();
		first = 0;
		count = 0;
		scrolltimes = 0;
		friendList.html("");
		addfriendList(rowtemp, "/wegether/member/Friend/", friendList);
	})
	
	function addfriendList(temple, restful, inputdiv) {
			$.ajax({
				type: "GET",
				url: restful + first,
				dataType: "json",
				success: function (data) {
					if (data.state) {
						first += data.json.length;
						if (count == 0 && data.count != null) {
							count = data.count;
							if(count != 0){
								inputdiv.css('min-height', (count* listhight) + "px");
							}
						}
						$.each(data.json, function (i, e) {
							var row = $(temple);
							row.find(".name").html(e.nickname);
							row.find('.memberPhoto img').attr('src', e.photoSrc);
							row.find('a').attr('href', '/wegether/personal.controller?memberId='+e.memberid);
							row.find('.friendButton').attr('friendid', e.id);
							inputdiv.append(row);
						})
						scrolltimes = 0;
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {

				}
			});
		}
})