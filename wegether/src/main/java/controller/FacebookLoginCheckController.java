package controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.ActivityPagRankeService;
import Service.AttendService;
import model.AttendBean;



@Controller
public class FacebookLoginCheckController {

	@Autowired
	private ActivityPagRankeService activityPagRankeService;
	
	@Autowired
	private AttendService attendService;

	@RequestMapping( path= {("/FbloginCheck.controller/{Fbid}/{Fbemail}/{Fbname}")}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?>  method(
//			@RequestAttribute(name = "memberid", required = false) Integer memberid,
			@PathVariable(name="Fbid") String fbid,
			@PathVariable(name="Fbname") String fbName,
			@PathVariable(name="Fbemail") String fbEmail
			) {
			System.out.println("fbid="+fbid);
			System.out.println("fbName="+fbName);
			System.out.println("fbEmail="+fbEmail);
			
			
//			AttendBean bean = activityPagRankeService.updateRank(activityid,memberid,rank1,rank2,rank3);
//			if(bean!=null) {			
				return new ResponseEntity<>(true,HttpStatus.OK);
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
