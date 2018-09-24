package controller;

import java.io.File;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.MemberBean;
import pictureconvert.PictureConvert;



@Controller
public class ServiceloginCheckController{

	@RequestMapping( path= {("/servicelogincheck.controller")}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?>  method(
			@RequestAttribute(name = "memberid", required = false) Integer Id
			) {
			System.out.println("servicelogincheck.controller!!!!="+Id);

	
				
				if(Id!=null) {
				return new ResponseEntity<>(true,HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			
			
			
//			if(bean!=null) {			
//				return new ResponseEntity<>(true,HttpStatus.OK);
//			}else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}		
	}
	
//	@RequestMapping( path= {("/activityPageRank.controller/{activityid}")}, produces= {"application/json"})
//	public @ResponseBody ResponseEntity<?>  method(
//			@PathVariable(name="activityid") Integer activityid, 
//			@RequestAttribute(name = "memberid", required = false) Integer memberid, 
//			HttpServletRequest request) {
//		
//			System.out.println("selectRank");
//			
//			AttendBean bean = activityPagRankeService.selectRank(activityid,memberid);
//			if(bean!=null) {	
//				JSONObject result = attendService.attendToJson(bean);
//				return new ResponseEntity<>(result,HttpStatus.OK);
//			}else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}		
//	}
	
	
	
}
