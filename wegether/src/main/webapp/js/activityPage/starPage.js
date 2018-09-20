			//star1  活動滿意度
			var flagStar1=true;  
			var imgidNO1;
			function over1(){
        		imgidNO1=$(this).attr("id").substr(7);
        		$('#spa1').text("評分中... "+imgidNO1); 
        		flagStar1=true;
        		for (var i = 1; i < 6; i++) {
    				if (i <= imgidNO1) {
						$('#idstar1'+ i).removeClass('s').addClass('n');
    				} else {
    					console.log("in")
    					$('#idstar1'+ i).removeClass('n').addClass('s');	
    				}
    			}
			}
        	
    			function out1(){
    				 if(flagStar1){
    					$('#spa1').text("");  
                        for(var i=1;i<6;i++)  {         
							$('#idstar1'+ i).removeClass('n').addClass('s');	
                     	}           
                        flagStar1=true;
    				}
    			}
        	
    			function click1(){
    			imgidNO1=$(this).attr("id").substr(7);          
                 $('#spa1').text("您評分 "+imgidNO1+" 顆星"); 
                 flagStar1=false;
             }
    			
    			//star2  溝通安排
    			var flagStar2=true; 
    			var imgidNO2;
    			function over2(){
            		imgidNO2=$(this).attr("id").substr(7);
            		$('#spa2').text("評分中... "+imgidNO2); 
            		flagStar2=true;
            		for (var i = 1; i < 6; i++) {
        				if (i <= imgidNO2) {
    						$('#idstar2'+ i).removeClass('s').addClass('n');
        				} else {
        					console.log("in")
        					$('#idstar2'+ i).removeClass('n').addClass('s');	
        				}
        			}
    			}
            	
        			function out2(){
        				 if(flagStar2){
        					$('#spa2').text("");  
                            for(var i=1;i<6;i++)  {         
    							$('#idstar2'+ i).removeClass('n').addClass('s');	
                         	}           
                            flagStar2=true;
        				}
        			}
            	
        			function click2(){
        			 imgidNO2=$(this).attr("id").substr(7);          
                     $('#spa2').text("您評分 "+imgidNO2+" 顆星"); 
                     flagStar2=false;
                 }

        			//star3  時間地點選擇
        			var flagStar3=true;  
        			var imgidNO3;
        			function over3(){
                		imgidNO3=$(this).attr("id").substr(7);
                		$('#spa3').text("評分中... "+imgidNO3); 
                		flagStar3=true;
                		for (var i = 1; i < 6; i++) {
            				if (i <= imgidNO3) {
        						$('#idstar3'+ i).removeClass('s').addClass('n');
            				} else {
            					$('#idstar3'+ i).removeClass('n').addClass('s');	
            				}
            			}
        			}
                	
            			function out3(){
            				 if(flagStar3){
            					$('#spa3').text("");  
                                for(var i=1;i<6;i++)  {         
        							$('#idstar3'+ i).removeClass('n').addClass('s');	
                             	}           
                                flagStar3=true;
            				}
            			}
                	
            			function click3(){
            			 imgidNO3=$(this).attr("id").substr(7);          
                         $('#spa3').text("您評分 "+imgidNO3+" 顆星"); 
                         flagStar3=false;
                     }	
        			
        			
            			function sendRank(){
            				console.log("imgidNO3="+imgidNO3);
            				$.get("activityPageRank.controller/"+activityid+"/"+imgidNO1+"/"+imgidNO2+"/"+imgidNO3+"",
            						  function(data){	
	            				 		if(data){
	            				 			 $('#stardiv1 img').unbind();
	            				 			 $('#stardiv2 img').unbind();
	            				 			 $('#stardiv3 img').unbind();
	            				 			 $('#output1').html("感謝您的評點！").show();   
	            				 			 $('#startSumit').hide();
	            				 		}	
            				 
            				 },'json');	
            			}
                   	