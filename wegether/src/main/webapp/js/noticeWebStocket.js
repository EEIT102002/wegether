function connectNotice(token) {
    //開始WebSocket連線
	var loc = window.location;
    var url = "ws://" + loc.host + "/wegether/noticeHandler?token=";
    console.log(url);
    nwebSocket = new WebSocket(url+token);
    //以下開始偵測WebSocket的各種事件

    //onerror , 連線錯誤時觸發  
    nwebSocket.onopen = function() {
		console.log("connection opened");
	};
	nwebSocket.onclose = function() {
		console.log("connection closed");
	};
	nwebSocket.onerror = function wserror(message) {
		console.log("error: " + message);
	};
    //onmessage , 接收到來自Server的訊息時觸發
    nwebSocket.onmessage = function (message) {
    	console.log(message.data);
       
    };
}