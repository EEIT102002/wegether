			//star1  活動滿意度
			var flagStar1=true;  
			var imgidNO1;
			var rank = ["還不錯","值得參加","值得推薦","很滿意","非常滿意"]

			function over1(){
        		imgidNO1=$(this).attr("id").substr(7);
        		$('#spa1').text("... "+rank[imgidNO1-1]); 
        		flagStar1=true;
        		for (var i = 1; i < 6; i++) {
    				if (i <= imgidNO1) {
						$('#idstar1'+ i).removeClass('s').addClass('n');
    				} else {
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
                 $('#spa1').text("... "+rank[imgidNO1-1]); 
                 flagStar1=false;
             }
    			
    			//star2  溝通安排
    			var flagStar2=true; 
    			var imgidNO2;
    			function over2(){
            		imgidNO2=$(this).attr("id").substr(7);
            		$('#spa2').text("... "+rank[imgidNO2-1]); 
            		flagStar2=true;
            		for (var i = 1; i < 6; i++) {
        				if (i <= imgidNO2) {
    						$('#idstar2'+ i).removeClass('s').addClass('n');
        				} else {
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
                     $('#spa2').text("... "+rank[imgidNO2-1]); 
                     flagStar2=false;
                 }

        			//star3  時間地點選擇
        			var flagStar3=true;  
        			var imgidNO3;
        			function over3(){
                		imgidNO3=$(this).attr("id").substr(7);
                		$('#spa3').text("... "+rank[imgidNO3-1]); 
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
                         $('#spa3').text("... "+rank[imgidNO3-1]); 
                         flagStar3=false;
                     }	
        			
        			
            			function sendRank(){
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
            			
            			
            			
            			function rankgo(){
            				console.log("rankgo2")

            				$.get("activityPageRank.controller/"+activityid,
            						  function(data){	
	            				 		if(data){
	            				 			
	            				 			if(data.rank1!=null || data.rank2!=null || data.rank3!=null ){
	            				 				
			            				 				 $("#stardiv1").show();
			            		       					 $("#stardiv2").show();
			            		       					 $("#stardiv3").show();
		//	            		       					 $("#startSumit").show();
			            		       					 $("#attendShare").show();
			            		       					 
			            				 				 $('#stardiv1 img').unbind();
				            				 			 $('#stardiv2 img').unbind();
				            				 			 $('#stardiv3 img').unbind();
				            				 			 $('#output1').html("感謝您的評點！").show();   
				            				 			 $('#startSumit').hide();
				            				 			 
				            				 			//-----		            				 			 
				            							for (var i = 1; i <= 5; i++) {
				            								if (i <= data.rank1) {
				            									$('#idstar1'+ i).removeClass('s').addClass('n');                             
				            								} else {
				            									$('#idstar1'+ i).removeClass('n').addClass('s');
				            								}
				            							}
				            							$('#spa1').text("... "+rank[data.rank1-1]); 
				            							
				            							for (var i = 1; i <= 5; i++) {
				            								if (i <= data.rank2) {
				            									$('#idstar2'+ i).removeClass('s').addClass('n');                             
				            								} else {
				            									$('#idstar2'+ i).removeClass('n').addClass('s');
				            								}
				            							}
				            							$('#spa2').text("... "+rank[data.rank2-1]); 
				            							
				            							for (var i = 1; i <= 5; i++) {
				            								if (i <= data.rank3) {
				            									$('#idstar3'+ i).removeClass('s').addClass('n');                             
				            								} else {
				            									$('#idstar3'+ i).removeClass('n').addClass('s');
				            								}
				            							}
				            							$('#spa3').text("... "+rank[data.rank3-1]); 
			            				 				//-----end
			            				 				
			            				 			}
	            				 			
	            				 			
	            				 		}	
            				 
            				 },'json');	
            				
            				
	            			 
            			}
            			
            			
            			
            			
                   	