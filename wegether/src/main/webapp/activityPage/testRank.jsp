<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>EEIT10214 吳宗諭</title>
<link href="/wegether/css/bootstrap.css" rel="stylesheet" type="text/css"	media="all" />
<link href="/wegether/css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="/wegether/js/jquery-2.1.4.min.js"></script>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'>
<script src="/wegether/js/bootstrap.js"></script>
<link rel="stylesheet"	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
<!-- 登入使用 -->
<script src="/wegether/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/wegether/js/noticeWebStocket.js" type="text/javascript"></script>
<script src="/wegether/js/logMethod.js" type="text/javascript"></script>
<!-- 登入使用 END -->
<!-- 留言 /心得心享 視窗  -->
<link rel="stylesheet"	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/wegether/css/activityPage.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/activityPage.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/msgPage.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/articlePage.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/idCheck.js"></script>
<script type="text/javascript" src="/wegether/js/activityPage/autoPlay.js"></script>
<script src="http://malsup.github.io/jquery.form.js" type="text/javascript"></script>
<!-- 留言 /心得心享 視窗 END -->
<!-- applyForm -->
<script src="/wegether/js/applyForm.js" type="text/javascript"></script>
<link rel="stylesheet" href="/wegether/css/applyForm.css">
    <style>
       
        .star{
                margin:40px;
                text-align: center;
                font-size: 50px;
                color:#CC0000;
                height: 50px;
            }
            .s {
                -webkit-filter: grayscale(1);  /*沒有任何色彩的黑白影像 webkit filter 濾鏡 */
             }
            .n {
                -webkit-filter: grayscale(0);  /*顏色不變*/
            }            
        </style>
        <script>
            var flag=true;  
            var imgidNO;
        	$(function() {

        		$('.content img').click(click)
        		        .hover(over,out);
        		
        		$('#myForm').ajaxForm({
        			name:takshali
        		},
        				function(data) {   
                    $('#output1').html("提交成功！欢迎下次再来！"+data).show();    
                }); 
    			
        		
    			
        	 });
        	
        	function over(){
        		 imgidNO=$(this).attr("id").substr(7);
        		$('#spa').text("評分中... "+imgidNO); 
        		 flag=true;
        		for (var i = 1; i < 6; i++) {
    				if (i <= imgidNO) {
						$('#idstar1'+ i).removeClass('s').addClass('n');
    				} else {
    					console.log("in")
    					$('#idstar1'+ i).removeClass('n').addClass('s');	
    				}
    			}
			}
        	
        	
        	
    			function out(){
    				 if(flag){
    					$('#spa').text("");  
                        for(var i=1;i<6;i++)  {         
							$('#idstar1'+ i).removeClass('n').addClass('s');	
                     	}           
                     flag=true;
    				}
    			}
        	
    			function click(){
    			 imgidNO=$(this).attr("id").substr(7);          
                 $('#spa').text("恭喜您獲得 "+imgidNO+" 顆星"); 
                 flag=false;
             }
              
    			function send(){
    				console.log("gg");
    				$.get("../activityPageRank.controller/1/"+imgidNO+"/3/4",
    						  function(data){	
    				 			
    				 
    				 },'json');	
    			}
           
        </script>
</head>

<body>

        		<div class="content">
              
                        <img id="idstar11" class="s" src="../images/star.png" />
                        <img id="idstar12" class="s" src="../images/star.png" />
                        <img id="idstar13" class="s" src="../images/star.png" />
                        <img id="idstar14" class="s" src="../images/star.png" />
                        <img id="idstar15" class="s" src="../images/star.png" />
                        <div class="star">
                        	<spa id="spa"></spa>
                     	</div>   
<!--                      	 名称： <input type="text" name="name" /> <br/> -->
                     	<input type="button"value="提交"  class="btn btn-warning" onclick="send()"/> <br/>
        				<div id="output1" style="display:none;"></div>
              </div>  
              
                
</body>

</html>