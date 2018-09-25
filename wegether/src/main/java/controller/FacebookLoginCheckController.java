package controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import Service.ActivityPagRankeService;
import Service.AttendService;
import model.AttendBean;
import model.MemberBean;
import model.dao.MemberDAO;
import pictureconvert.PictureConvert;



@Controller
public class FacebookLoginCheckController {

	@Autowired
	private ActivityPagRankeService activityPagRankeService;
	
	@Autowired
	private MemberDAO memberDAO;

	@RequestMapping( path= {("/FbloginCheck.controller/{Fbid}/{Fbemail}/{Fbname}")}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?>  method(
			@RequestAttribute(name = "memberid", required = false) Integer Id,
			@PathVariable(name="Fbid") String fbid,
			@PathVariable(name="Fbname") String fbName,
			@PathVariable(name="Fbemail") String fbEmail
			) {
			System.out.println("fbid="+fbid);
			System.out.println("fbName="+fbName);
			System.out.println("fbEmail="+fbEmail);
			if(Id!=null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			MemberBean bean = new MemberBean();
			MemberBean checkuser = memberDAO.selectByAccount(fbEmail);//檢查有無相同帳號
			System.out.println(fbEmail);
			System.out.println("checkuser結果:" + checkuser);
			String beginDate = "2018-08-01";
			Date t1 = null;

			if (checkuser == null) {
				bean.setFbid(fbid);			//FB取得的id
				bean.setAccount(fbEmail);	//FB取得的email
				bean.setName(fbName);		//FB取得的名稱
				bean.setNickname(fbName);
				bean.setPwd("EA123456".getBytes());	//預設密碼
				
				//預設圖片
//				byte[] pic = null;
//				File defultPic = new File("/wegether/images/04.jpg");
//				try {
//					// pic = ((MultipartFile) defultPic).getBytes();
//					pic = PictureConvert.converFileToByte(defultPic);
//					bean.setPhoto(pic);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				bean.setBirthday(new java.util.Date());//系統註冊時間
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
