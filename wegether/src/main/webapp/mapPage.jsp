<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MapPage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"/>
<script>
	$(function(){
		$('#header_nav ul li').click(function(){
			$(this).addClass('active').siblings().removeClass('active');
		})
		// $('#upTop').click(function(){
		// 	var body = $("html, body");
		// 	body.stop().animate({scrollTop:0}, 500, 'swing');
		// 	}).mouseover(function(){
		// 		$(this).removeClass('animated');
		// 	}).mouseout(function(){
		// 		$(this).addClass('animated');
		// 	})
	})
</script>
<style>
    *{
        list-style: none;
        margin: 0;
        padding: 0;

    }
    body{
        /* background-color: rgb(145, 145, 145);
         */
         background: url(images/v6.jpg) no-repeat;
         background-size: cover;
         background-attachment: fixed;
    }
    #small_con{
        width: 100%;
        min-height: 800px;
        background-color: rgba(255, 255, 255, 0.363);
    }
    footer{
        margin-top: 20px;
    }
    footer ul{
        text-align: center;
        /* border: 2px solid red; */
        display: flex;
        width: 70%;
        margin: auto;

    }
    footer>ul>li{
        /* border: 2px solid green; */
        flex: 1;

    }
    footer>ul>li a{
        font-size: 1.2em;
    }
    footer>ul>li ul{
        display: flex;
        flex-direction: column;
        width: 80%;
        color: rgb(255, 153, 0);
    }
    .container p{
        color: white;
    }
/*      mapPage  Style */
      #map {
         height: 500px; 
         border:1px solid salmon;  
       
      }
  
</style>
</head>	
<body>
		<div class="container">
			<nav class="navbar navbar-default" id="stickytop">
				<div class="navbar-header">
				  <button type="button" class="
				   collapsed" data-toggle="collapse" data-target="#dropdown_munu" id="hum">
					<span><i class="fa fa-bars" aria-hidden="true"></i></span>
				  </button>
					<div class="logo">
						<h1><a class="navbar-brand" href="index.html">Wegether</a></h1>
					</div>
				</div>
				<div class="collapse navbar-collapse nav-wil" id="dropdown_munu">
					<nav class="header_nav" id="header_nav"> 
						<ul class="nav navbar-nav">
							<li class="active"><a href="#"><span data-hover="活動">活動</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="心得">心得</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="發起活動">發起活動</span></a></li>
							<li><a href="#" class="scroll"><span data-hover="發起心得">發起心得</span></a></li>
							<li><a href="#" class="scrol" data-toggle="modal" data-target="#ActPageBox"><span data-hover="登入">登入</span></a></li>
						</ul>
					</nav>
				</div>
			</nav>
		</div>
	<div class="modal fade" id="ActPageBox" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-lg" role="document">
			  <div class="modal-content"><!--白色遮罩層-->
				  <div class="modal-body">
		  <!--       // modal-body  有差padding -->
					  <div class="modal-header">
						  
						  <h5 class="modal-title lead"><strong>請選擇登入方式</strong></h5>
						  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
						  </button>
					  </div>
					  <div class="modal-body" id="mid-body">
						  <div class="form-group" id="ACT">
							  <label for="recipient-name" class="col-form-label">帳號:</label>
							  <input type="text" class="form-control" id="account">
						  </div>
						  <div class="form-group" id="PWD">
							  <label for="recipient-name" class="col-form-label">密碼:</label>
							  <input type="text" class="form-control" id="password">
						  </div>
						  <div id="or" class="bg-primary text-white"><h4>or</h4></div>
						  <button type="button" class="btn big_use Google_i"><i class="fa fa-google" aria-hidden="true"></i>Google 登入</button>
						  <button type="button" class="btn big_use Fb_i"><i class="fa fa-facebook-official" aria-hidden="true"></i>FB 登入</button>
						  <div class="AreaCon">
						  <button type="button" class="btn small_use Google_i"><i class="fa fa-google" aria-hidden="true"></i></button>
						  <button type="button" class="btn small_use Fb_i"><i class="fa fa-facebook-official" aria-hidden="true"></i></button>
						  </div>
					  </div>
					  <div class="modal-footer">
						  <p class="small text-left" >還沒註冊嗎?趕緊註冊一個帳號吧! <a href="javascript:void(0)">點我註冊</a></p>
						  <button type="button" class="btn btn-primary">登入</button>
						  <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
					  </div>
				  </div>
			  </div>
			</div>
		  </div>
	<div class="container">
        <div id="small_con">
<!--       寫在這以下 -->
<div id="allid">
        請輸入查詢資料：
          <input id="address" type="textbox" >
          <input id="submit" type="button" value="查詢"><span id="show"></span>
          <br><br>           
          <div id="map" ></div>
        
        
    </div> 
     <script>
    
//*多點座標
function initMap() {
  
  var map;
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 25.037, lng: 121.517},
    zoom: 14
  });

  //*************查詢功能

 var geocoder = new google.maps.Geocoder();
document.getElementById('submit').addEventListener('click', function() {
  geocodeAddress(geocoder, map);
});


//*************標記活動地點

 var infowindows = info_config.map(function(con,i){
   return new google.maps.InfoWindow({
          content: con,
          //disableAutoPan:true
               }); 
 });

  //* /標出 marker
  var markers = [];
  var btn=[];
  locations.forEach(function(location,i){    
    //座標 icon
    markers[i] = new google.maps.Marker({
            position: location,
           // label: 'E',
          });
    markers[i].setMap(map); //.setMap(null) 就可以清除該 marker   
    markers[i].addListener('click', function() {        
           //跳動效果 
            if(markers[i].getAnimation()==null){
                markers[i].setAnimation(google.maps.Animation.BOUNCE);
                //資訊視窗 
                infowindows[i].open(map,markers[i]);  
            }else{
                markers[i].setAnimation(null);
                infowindows[i].close();
            }
            //關閉資訊視窗，停止跳動
            infowindows[i].addListener('closeclick',function() {
              markers[i].setAnimation(null);              
            }); 

            document.getElementById('btn'+i).addEventListener('click', function() {
                  document.location="ActivityPage.html";
                  infowindows[i].close();
                  markers[i].setAnimation(null);
                  }); 
        }); 

  }); 
  
        // Add a marker clusterer to manage the markers.
        var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
  //*************  

}

var marker;
function geocodeAddress(geocoder, resultsMap) {  
var address = document.getElementById('address').value;
geocoder.geocode({'address': address}, function(results, status) {
  if (status === 'OK') {
      if(marker!=null){
        marker.setMap(null);
      };      
    resultsMap.setCenter(results[0].geometry.location);
     marker = new google.maps.Marker({
      map: resultsMap,          
      position: results[0].geometry.location
    });    
   
    document.getElementById("show").
    innerHTML="";
  } else {    
      if(marker!=null){
        marker.setMap(null);
      };
    document.getElementById("show").
    innerHTML="<i style='color: red'> 查無資料，請重新輸入，謝謝～</i>";
  }
});
}

 var info_config = [
    
    '<h2>總統府</h2>'+
    '<span>這是總統府</span><br/>'+
    '<img id="imd1" src="images/mapPageImages/pic1.jpg" /><br/>'+
    '<i>(圖片取自總統府網站)</i><br/>'+
    '<button id="btn0">更多資訊...</button>',

    '<h2>中正紀念堂</h2>'+
    '<span>這是中正紀念堂</span><br/>'+
    '<img id="imd2" src="images/mapPageImages/pic2.jpg" /><br/>'+
    '<i>(圖片取自中正紀念堂網站)</i><br/>'+
    '<button id="btn1">更多資訊...</button>',

    '<h2>一個適合碰面的美好日子</h2>'+
    '<span>雖然每天都要吃飯...</span><br/>'+
    '<img id="imd2" src="images/mapPageImages/pic4.png" /><br/>'+
    '<i>七月 29 (日) 12:00</i><br/>'+
    '<button id="btn2">更多資訊...</button>',

    '<h2>資策會</h2>'+
    '<span>JAVA就業養成班...</span><br/>'+
    '<img id="imd2" src="images/mapPageImages/pic3.jpg" /><br/>'+
    '<i>七月 29 (日) 12:00</i><br/>'+
    '<button id="btn3">更多資訊...</button>',

    '<h2>宗諭家</h2>'+
    '<span>通化夜市...</span><br/>'+
    '<img id="imd2" src="images/mapPageImages/pic5.jpg" /><br/>'+
    '<i>七月 29 (日) 12:00</i><br/>'+
    '<button id="btn4">更多資訊...</button>',

    '<h2>鼎泰豐 信義總店</h2>'+
    '<span>台北市大安區信義路二段194號</span><br/>'+
    '<img id="imd2" src="images/mapPageImages/pic6.jpg" /><br/>'+
    '<i>七月 29 (日) 12:00</i><br/>'+
    '<button id="btn5">更多資訊...</button>',

  ];


var locations = 
[{lat: 25.040389, lng: 121.511955},
{lat: 25.034957, lng: 121.521824},
{lat: 25.045901, lng: 121.514860},
{lat: 25.033772, lng: 121.542792},
{lat: 25.029830, lng: 121.553752},
{lat: 25.033497, lng: 121.530114},];       
      

    </script>
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDGKu-WM7zqLOM3a86wVIpi4X8oHu7Sh4&callback=initMap"
    async defer></script>
<!--       寫在這以上 -->
        </div>
	</div>

	<!-- <footer>
		<div class="container">
			<p id="fw">Wegther 2018</p>
		</div>
	</footer> -->
	<!-- <i  id="upTop" class="fa fa-chevron-circle-up animated infinite bounce" aria-hidden="true"></i> -->
    <footer class="container">
            <ul>
                    <li>
                        <a href="" title="">關於Wegether</a>
                        <ul>
                            <li>-我們的出發</li>
                            <li>-我們的團隊</li>
                            <li>-我們的服務</li>
                        </ul>
                    </li>
                    <li>
                        <a href="" title="">聯絡我們</a>
                        <ul>
                                <li>-聯絡我們</li>
                        </ul>
                    </li>
            </ul>
            <p class="text-center">- Wegether 2018 EEIT10202 -</p>
    </footer>
</body>
</html>