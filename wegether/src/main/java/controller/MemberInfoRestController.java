package controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.BlacklistBean;
import model.FriendBean;
import model.MemberBean;
import model.MemberInfoBean;
import model.SettingBean;
import model.TrackmemberBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;
import model.dao.TrackmemberDAO;

@RestController
public class MemberInfoRestController {
	@Autowired
	MemberInfoDAO memberInfoDAO;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	SettingDAO settingDAO;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	FriendDAO friendDAO;
	@Autowired
	TrackmemberDAO trackmemberDAO;
	@Autowired
	BlacklistDAO blacklistDAO;
	
	@GetMapping( path= {"/member/Info"}, produces= {"application/json"})
	public ResponseEntity<?> getInfo(){
		MemberInfoBean result = memberInfoDAO.select(2);
		System.out.println(result);
		
		
		if(result!=null) {
			return new ResponseEntity<MemberInfoBean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping( path= {"/member/Account"}, produces= {"application/json"})
	public ResponseEntity<?> getAccount(){
		MemberBean memberBean = 	memberDAO.select(2);
		if(memberBean!=null) {
			JSONObject result = (JSONObject)applicationContext.getBean("newJson");
			result.put("account", memberBean.getAccount());
			result.put("signupdate",memberBean.getSignupdate());
			result.put("state", memberBean.getState());
			return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping( path= {"/member/Setting"}, produces= {"application/json"})
	public ResponseEntity<?> getSetting(){
		SettingBean settingBean = 	settingDAO.select(2);
		if(settingBean != null) {
			sessionFactory.getCurrentSession().evict(settingBean);
			settingBean.setMemberBean(null);
			return new ResponseEntity<SettingBean>(settingBean, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping( path= {"/member/Friend"}, produces= {"application/json"})
	public ResponseEntity<?> getFriend(){
		List<FriendBean> friendBeans = 	friendDAO.selectByMemberState(2, 1,0);
		if(friendBeans.size() > 0) {
			JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
			friendBeans.forEach(x->{
				JSONObject row = (JSONObject)applicationContext.getBean("newJson");
				row.put("nickname", x.getMemberFBean().getNickname());
				row.put("photo",x.getMemberFBean().getPhoto());
				row.put("id", x.getMemberidf());
				result.add(row);
			});
			return new ResponseEntity<JSONArray>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping( path= {"/member/Friend/Applyed"}, produces= {"application/json"})
	public ResponseEntity<?> getFriendApplyed(){
		List<FriendBean> friendBeans = 	friendDAO.selectByMemberFState(2, 0,0);
		if(friendBeans.size() > 0) {
			JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
			friendBeans.forEach(x->{
				JSONObject row = (JSONObject)applicationContext.getBean("newJson");
				row.put("nickname", x.getMemberBean().getNickname());
				row.put("photo",x.getMemberBean().getPhoto());
				row.put("id", x.getMemberid());
				result.add(row);
			});
			return new ResponseEntity<JSONArray>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping( path= {"/member/Friend/Apply"}, produces= {"application/json"})
	public ResponseEntity<?> getFriendApply(){
		List<FriendBean> friendBeans = 	friendDAO.selectByMemberState(2, 0,0);
		if(friendBeans.size() > 0) {
			JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
			friendBeans.forEach(x->{
				JSONObject row = (JSONObject)applicationContext.getBean("newJson");
				row.put("nickname", x.getMemberFBean().getNickname());
				row.put("photo",x.getMemberFBean().getPhoto());
				row.put("id", x.getMemberidf());
				result.add(row);
			});
			return new ResponseEntity<JSONArray>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping( path= {"/member/Trackmember"}, produces= {"application/json"})
	public ResponseEntity<?> getTrackmember(){
		List<TrackmemberBean> trackmemberBeans = trackmemberDAO.selectByFan(2);
		if(trackmemberBeans.size() > 0) {
			JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
			trackmemberBeans.forEach(x->{
				JSONObject row = (JSONObject)applicationContext.getBean("newJson");
				row.put("nickname", x.getMemberByMemberid().getNickname());
				row.put("photo",x.getMemberByMemberid().getPhoto());
				row.put("id", x.getId().getMemberid());
				result.add(row);
			});
			return new ResponseEntity<JSONArray>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping( path= {"/member/Blacklist"}, produces= {"application/json"})
	public ResponseEntity<?> getBlacklist(){
		List<BlacklistBean> blacklistBeans = blacklistDAO.selectByMember(2);
		JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
		if(blacklistBeans.size() > 0) {
			blacklistBeans.forEach(x->{
				JSONObject row = (JSONObject)applicationContext.getBean("newJson");
				row.put("nickname", x.getMemberByBlackid().getNickname());
				row.put("photo",x.getMemberByBlackid().getPhoto());
				row.put("id", x.getId().getBlackid());
				result.add(row);
			});
			
		} 
		return new ResponseEntity<JSONArray>(result, HttpStatus.OK);
	}
	
	
}
