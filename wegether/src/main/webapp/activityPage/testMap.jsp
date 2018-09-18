<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Test Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
         height: 500px; 
         width:500px; 
         border:1px solid salmon;  
       
      }
     
     
      #allid{
            overflow: auto;
            height: 90%;
            width:90%;   
            /* border: 1px solid gray; */
           
        }
        #leftid{
          height: 90%;
            width:80%;  
            float: left;
            
        }
        #rightid{
          height: 90%;
            width:50%;  
            float: right;
        }
    </style>
  </head>
  <body>
     <div id="allid">
       <div id="leftid">
        請輸入查詢資料：
          <input id="address" type="textbox" >
          <input id="submit" type="button" value="查詢"><span id="show"></span>
          <br><br>           
          <div id="map" ></div>
        </div>  
        
        <div id="rightid">
            <!-- <iframe  src="monthTable.html" id="frameid" frameborder="0" scrolling="no"></iframe> -->
        </div>
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
    '<img id="imd1" src="images/pic1.jpg" /><br/>'+
    '<i>(圖片取自總統府網站)</i><br/>'+
    '<button id="btn0">更多資訊...</button>',

    '<h2>中正紀念堂</h2>'+
    '<span>這是中正紀念堂</span><br/>'+
    '<img id="imd2" src="images/pic2.jpg" /><br/>'+
    '<i>(圖片取自中正紀念堂網站)</i><br/>'+
    '<button id="btn1">更多資訊...</button>',

    '<h2>一個適合碰面的美好日子</h2>'+
    '<span>雖然每天都要吃飯...</span><br/>'+
    '<img id="imd2" src="images/pic4.png" /><br/>'+
    '<i>七月 29 (日) 12:00</i><br/>'+
    '<button id="btn2">更多資訊...</button>',

    '<h2>資策會</h2>'+
    '<span>JAVA就業養成班...</span><br/>'+
    '<img id="imd2" src="images/pic3.jpg" /><br/>'+
    '<i>七月 29 (日) 12:00</i><br/>'+
    '<button id="btn3">更多資訊...</button>',

    '<h2>宗諭家</h2>'+
    '<span>通化夜市...</span><br/>'+
    '<img id="imd2" src="images/pic5.jpg" /><br/>'+
    '<i>七月 29 (日) 12:00</i><br/>'+
    '<button id="btn4">更多資訊...</button>',

    '<h2>鼎泰豐 信義總店</h2>'+
    '<span>台北市大安區信義路二段194號</span><br/>'+
    '<img id="imd2" src="images/pic6.jpg" /><br/>'+
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
  </body>
</html>

