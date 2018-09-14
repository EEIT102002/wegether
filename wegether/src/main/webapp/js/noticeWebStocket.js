var unread;
var first = 0;
var unchick= true;
var count = 0;
var scrollHeight;

function connectNotice(token) {
    //開始WebSocket連線
    var loc = window.location;
    var url = "ws://" + loc.host + "/wegether/noticeHandler?token=";
    console.log(url);
    nwebSocket = new WebSocket(url + token);
    //以下開始偵測WebSocket的各種事件

    //onerror , 連線錯誤時觸發  
    nwebSocket.onopen = function () {
        console.log("connection opened");
    };
    nwebSocket.onclose = function () {
        console.log("connection closed");
    };
    nwebSocket.onerror = function wserror(message) {
        console.log("error: " + message);
    };
    //onmessage , 接收到來自Server的訊息時觸發
    nwebSocket.onmessage = function (message) {
        console.log(message.data);

    };

    $.get(
        '/wegether/noitce/unread'
        , function (data) {
            unread = data.count;
            if (unread != 0) {
                $('#supremind').text(unread).show();
            }
        }
        , 'json'
    )
    
    
    var noticediv = $('#contentRemind');
    var noticedivdiv = noticediv.children('div');
    
    noticediv.scroll(function(){
    	if(first != 0 && noticediv.scrollTop() <= scrollHeight-(first*42) && first%10 == 0){
    		selectNotices(function(){}, function(){})  
    		console.log(123);
    	}
    })
    
    $('#dropdownMenuButton2').click(function () {
        if ($(this).attr('aria-expanded') == 'false' ){
        	if(unread > 0){
        		selectNotices(clearNoticeDiv,clearUnread);
        	}else if(unchick){
        		selectNotices(clearNoticeDiv,clearUnread);
        	}
        }
    })
    function clearNoticeDiv(){
        first = 0;
    	noticedivdiv.html("");
    }
    
    function selectNotices(fn1,fn2){
        $.post(
            '/wegether/noitce/select/' + first
            , function (data) {
                if (data.state) {
                	fn1();
                    first += data.notices.length;
                    if(data.count != null){
                    	count = data.count;
                    	noticedivdiv.css('height',(count*42+10)+"px");
                    }
                    $.each(data.notices, function (i, e) {
                    	noticedivdiv.append($('<a class="dropdown-item"/>').text(e.id))
                    })
                    scrollHeight = noticediv.prop("scrollHeight");                   
                    fn2();
                }
            }
        )
    }

    function clearUnread(){
    	unread =0;
        noticediv.scrollTop(scrollHeight);
        unclick = false;
        $('#supremind').text(0).hide();
//    	  $.get(
//             '/wegether/noitce/unread/clear'
//             , function (data) {
//                 if (data.state) {
//                     unread = 0;
//                     $('#supremind').text(0).hide();
//                 }
//             }
//             , 'json'
//         )
    }
}

