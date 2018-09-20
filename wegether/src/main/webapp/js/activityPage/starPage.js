			//star1  活動滿意度
			var flagStar1=true;   
			function over1(){
        		var imgidNO=$(this).attr("id").substr(7);
        		$('#spa1').text("評分中... "+imgidNO); 
        		flagStar1=true;
        		for (var i = 1; i < 6; i++) {
    				if (i <= imgidNO) {
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
    			var imgidNO=$(this).attr("id").substr(7);          
                 $('#spa1').text("您評分 "+imgidNO+" 顆星"); 
                 flagStar1=false;
             }
            
    			
    			//star2  溝通安排
    			var flagStar2=true;   
    			function over2(){
            		var imgidNO=$(this).attr("id").substr(7);
            		$('#spa2').text("評分中... "+imgidNO); 
            		flagStar2=true;
            		for (var i = 1; i < 6; i++) {
        				if (i <= imgidNO) {
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
        			var imgidNO=$(this).attr("id").substr(7);          
                     $('#spa2').text("您評分 "+imgidNO+" 顆星"); 
                     flagStar2=false;
                 }
        			
        			//star3  時間地點選擇
        			var flagStar3=true;   
        			function over3(){
                		var imgidNO=$(this).attr("id").substr(7);
                		$('#spa3').text("評分中... "+imgidNO); 
                		flagStar3=true;
                		for (var i = 1; i < 6; i++) {
            				if (i <= imgidNO) {
        						$('#idstar3'+ i).removeClass('s').addClass('n');
            				} else {
            					console.log("in")
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
            			var imgidNO=$(this).attr("id").substr(7);          
                         $('#spa3').text("您評分 "+imgidNO+" 顆星"); 
                         flagStar3=false;
                     }	
            			
            			