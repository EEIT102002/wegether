package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.ActivityPagRankeService;
import model.AttendBean;



@Controller
public class ActivityPageRankController {

	@Autowired
	private ActivityPagRankeService activityPagRankeService;

	

	@RequestMapping( path= {("/activityPageRank.controller/{activityid}/{rank1}/{rank2}/{rank3}")}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?>  method(
			@PathVariable(name="activityid") Integer activityid, 
			@RequestAttribute(name = "memberid", required = false) Integer memberid,
			@PathVariable(name="rank1") Integer rank1, 
			@PathVariable(name="rank2") Integer rank2,
			@PathVariable(name="rank3") Integer rank3) {
		
			System.out.println("activityid:"+activityid);
			System.out.println("rank1:"+rank1);
			System.out.println("rank2:"+rank2);
			System.out.println("rank3:"+rank3);
			
			AttendBean bean = activityPagRankeService.updateRank(activityid,memberid,rank1,rank2,rank3);
			if(bean!=null) {			
				return new ResponseEntity<>(true,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}		
	}
}
