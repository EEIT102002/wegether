package controller;

import java.util.Date;

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
import model.MemberBean;
import model.dao.MemberDAO;



@Controller
public class FacebookLoginCheckController {

	@Autowired
	private ActivityPagRankeService activityPagRankeService;
	
	@Autowired
	private MemberDAO memberDAO;

	@RequestMapping( path= {("/FbloginCheck.controller/{Fbid}/{Fbemail}/{Fbname}/{Fbpicture}")}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?>  method(
//			@RequestAttribute(name = "memberid", required = false) Integer memberid,
			@PathVariable(name="Fbid") String fbid,
			@PathVariable(name="Fbname") String fbName,
			@PathVariable(name="Fbemail") String fbEmail,
			@PathVariable(name="Fbpicture") Object FbPicture
			) {
			System.out.println("fbid="+fbid);
			System.out.println("fbName="+fbName);
			System.out.println("fbEmail="+fbEmail);
			System.out.println("picture="+FbPicture);
			
			
			MemberBean bean = new MemberBean();
			MemberBean checkuser = memberDAO.selectByAccount(fbEmail);//檢查有無相同帳號
			System.out.println(fbEmail);
			System.out.println("checkuser結果:" + checkuser);
			String beginDate = "2018-08-01";
			Date t1 = null;
//			
//			
			if (checkuser == null) {
				bean.setAccount(fbEmail);
				bean.setName(fbName);
				bean.setPwd("EA123456".getBytes());
				bean.setBirthday(new java.util.Date());
				memberDAO.insert(bean);
				return new ResponseEntity<>(true,HttpStatus.OK);

			} else {
				System.out.println("相同帳號");
				return new ResponseEntity<>(true,HttpStatus.OK);
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
