<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script language="JavaScript">
function ajax_send_reply() {
// 1. 用 ajax 的方式送出留言，並存檔 (ajax 的使用，請自行上網查 ajax 入門)
// 2. 將存檔結果傳回 --> 失敗: 顯示存檔失敗訊息 
// 3. 若存檔成成，則將 div reply_div innerHTML 改成留言內容
var frmObj = document.reply_form;
alert("請自行改寫 ajax_send_reply 來送出留言 " + frmObj.reply_textarea.value);
}
</script>
</head>
<body>
<button type="button" onclick="document.getElementById('reply_div').style.display = '';">回覆</button>

<form name="reply_form">
<div id="reply_div" style="display:none;">
<textarea name="reply_textarea">請在這裏留言</textarea>
<button type="button" onclick="ajax_send_reply();">送出回覆</button>
</div>
</form>
<hr>

</body>
</html>