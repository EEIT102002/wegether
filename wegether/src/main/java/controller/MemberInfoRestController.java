package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;
import javax.xml.crypto.Data;

import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import Service.MemberInfoService;
import model.MemberBean;
import model.MemberInfoBean;
import model.SettingBean;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;

@RestController
public class MemberInfoRestController {
	@Autowired
	private MemberInfoDAO memberInfoDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private SettingDAO settingDAO;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private MemberInfoService memberInfoService;

	@GetMapping(path = { "/member/Info" }, produces = { "application/json" })
	public ResponseEntity<?> getInfo(@RequestAttribute("memberid") Integer id) {
		if (id == null) {
			System.out.println("is null");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MemberInfoBean result = memberInfoDAO.select(id);
		
		if (result != null) {
			System.out.println(result.getPhotoSrc());
			return new ResponseEntity<MemberInfoBean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = { "/member/Account" }, produces = { "application/json" })
	public ResponseEntity<?> getAccount(@RequestAttribute("memberid") Integer id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MemberBean memberBean = memberDAO.select(id);
		if (memberBean != null) {
			JSONObject result = (JSONObject) applicationContext.getBean("newJson");
			result.put("account", memberBean.getAccount());
			result.put("signupdate", memberBean.getSignupdate());
			result.put("state", memberBean.getState());
			return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = { "/member/Setting" }, produces = { "application/json" })
	public ResponseEntity<?> getSetting(@RequestAttribute("memberid") Integer id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		SettingBean settingBean = settingDAO.select(id);
		if (settingBean != null) {
			sessionFactory.getCurrentSession().evict(settingBean);
			settingBean.setMemberBean(null);
			return new ResponseEntity<SettingBean>(settingBean, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = { "/member/Friend/{first}" }, produces = { "application/json" })
	public ResponseEntity<?> getFriend(@PathVariable(name = "first") Integer first,
			@RequestAttribute("memberid") Integer id) {

		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Map<String, Object> result = new HashMap<>();
		if(first == 0) {
			result.put("count", memberInfoService.friendCount(id));
		}
		
		JSONArray beans = memberInfoService.searchFreind(id, first);

		if (beans != null) {
			result.put("state", true);
			result.put("json", beans);	
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(path = { "/member/Friend/Applyed/{first}" }, produces = { "application/json" })
	public ResponseEntity<?> getFriendApplyed(
			@PathVariable(name = "first") Integer first,@RequestAttribute("memberid") Integer id) {

		Map<String, Object> result = new HashMap<>();
		if(first == 0) {
			result.put("count", memberInfoService.friendApplyedCount(id));
		}
		JSONArray beans = memberInfoService.searchFreindApplyed(id, first);

		if (beans != null) {
			result.put("state", true);
			result.put("json", beans);	
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(path = { "/member/Friend/Apply/{first}" }, produces = { "application/json" })
	public ResponseEntity<?> getFriendApply(
			@PathVariable(name = "first") Integer first,@RequestAttribute("memberid") Integer id) {
		Map<String, Object> result = new HashMap<>();
		System.out.println(first+","+id);
		if(first == 0) {
			result.put("count", memberInfoService.friendApplyCount(id));
		}	
		JSONArray beans = memberInfoService.searchFreindApply(id, first);

		if (beans != null) {
			result.put("state", true);
			result.put("json", beans);	
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(path = { "/member/Trackmember" }, produces = { "application/json" })
	public ResponseEntity<?> getTrackmember(@RequestAttribute("memberid") Integer id) {

		if (id == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		JSONArray result = memberInfoService.searchTrackmember(id, 0);
		
		if (result != null) {
			return new ResponseEntity<JSONArray>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = { "/member/Blacklist" }, produces = { "application/json" })
	public ResponseEntity<?> getBlacklist(@RequestAttribute("memberid") Integer id) {

		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		JSONArray result = memberInfoService.searchBlacklist(id, 0);
		
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(path = { "/member/Info/index" }, produces = { "application/json" })
	public ResponseEntity<?> getInfoIndex(@RequestAttribute("memberid") Integer id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Object[]> resultlist = new ArrayList<>();
		MemberInfoBean result = memberInfoDAO.select(id);
		if (result != null) {
			Object[] obj = new Object[2];
			obj[0] = result;
			obj[1]= "/wegether/member/photo/"+ id;
			
			resultlist.add(obj);
			return new ResponseEntity<List<Object[]>>(resultlist, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
