var picNo=1;
document.addEventListener("DOMContentLoaded", function () {  
     selectPic();                
     });

            

var timerObj=setInterval(autoPlay,1500);
 function autoPlay(){
     picNo++;  if(picNo>4) picNo=1;                    
     selectPic();  

 }
 
 function selectPic(){
//     document.getElementById("imd0").src="images/"+picNo+".jpg";            
     for(var i=1;i<5;i++)
             document.getElementById("imd"+i).style="border:2px solid #FFBB00"; 
     document.getElementById("imd"+picNo).style="border:3px solid red";    
}

 function logoutDo() {
		alert(777);
	}