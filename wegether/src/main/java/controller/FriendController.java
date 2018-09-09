package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.FriendService;
import Service.MemberService;
import Service.SettingService;
import Service.bean.LoginBean;
import model.FriendBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;
import model.dao.TrackmemberDAO;

@Controller
public class FriendController {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private BlacklistDAO blacklistDAO;
	@Autowired
	private Map<Integer, LoginBean> loginMap;
	@Autowired
	private FriendService friendService;

	// 邀請好友
	@RequestMapping(path = { "/friend/invite" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> invFriend(String memberidfstr, HttpServletRequest request) {
		// 邀請好友 使用 memberid , memberidf
		Map<String, Object> result = new HashMap<>();
		Integer memberid =  getId(request, result);
		Integer memberidf = checkInteger(memberidfstr, result);
		if (result.size() > 0) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		FriendBean bean = new FriendBean();
		bean.setMemberid(memberid);
		bean.setMemberidf(memberidf);
		bean.setState(0);
		FriendBean check = friendDAO.insert(bean);
		// 邀請成功
		// result.put("state", true);
		// 邀請失敗
		// result.put("state", false);
		if (check != null) {
			result.put("state", true);
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

	}

	// 刪除好友
	@RequestMapping(path = { "/friend/delete" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> delFriend(String idStr, HttpServletRequest request) {
		// 邀請好友 使用 memberid , id
		Map<String, Object> result = new HashMap<>();
		Integer memberid =  getId(request, result);
		Integer id = checkInteger(idStr, result);
		if (result.size() > 0) {
			result.put("state", false);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		FriendBean bean = new FriendBean();
		bean.setId(id);
		bean.setMemberid(memberid);

		friendDAO.selectById(id);

		result.put("state", friendDAO.delete(bean));

		// 刪除成功
		// result.put("state", true);
		// 刪除失敗
		// result.put("state", false);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	// 拒絕好友邀請
	@RequestMapping(path = { "/friend/reject" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> rejectFriend(String idStr, HttpServletRequest request) {
		// 拒絕好友邀請 使用 memberid , memberfid 更改state為2
		Map<String, Object> result = new HashMap<>();
		Integer memberidf =  getId(request, result);
		Integer id = checkInteger(idStr, result);
		if (result.size() > 0) {
			result.put("state", false);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		FriendBean bean = new FriendBean();
		bean.setId(id);
		bean.setMemberidf(memberidf);
		bean.setState(2);
		result.put("state", friendService.changeFriend(bean));
		// 拒絕成功
		// result.put("state", true);
		// 拒絕失敗
		// result.put("state", false);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	// 接受好友邀請
	@RequestMapping(path = { "/friend/accept" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> acceptFriend(String idStr, HttpServletRequest request) {
		// 接受好友邀請 使用 memberid , memberfid 更改state為1
		Map<String, Object> result = new HashMap<>();
		Integer memberidf =  getId(request, result);
		Integer id = checkInteger(idStr, result);
		if (result.size() > 0) {
			result.put("state", false);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		FriendBean bean = new FriendBean();
		bean.setId(id);
		bean.setMemberidf(memberidf);
		bean.setState(1);
		result.put("state", friendService.changeFriend(bean));
		// 接受成功
		// result.put("state", true);
		// 接受失敗
		// result.put("state", false);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	private Integer checkInteger(String idStr, Map<String, Object> result) {
		if (!checkVoid(idStr, result)) {
			try {
				return Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				result.put("state", false);
			}
		}
		result.put("state", false);
		return null;
	}

	private boolean checkVoid(String str, Map<String, Object> result) {
		str = str.trim();
		return (str == null || str.isEmpty()) ? true : false;
	}
	
	private Integer getId(HttpServletRequest request,Map<String, Object> result ) {
		Object rs = request.getAttribute("memberid");
		if(rs != null && rs.getClass() == Integer.class) {
			return  (Integer) rs;
		}
		result.put("state", false);
		return null;
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
