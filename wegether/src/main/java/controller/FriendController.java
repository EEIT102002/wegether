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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private FriendDAO friendDAO;
	@Autowired
	private FriendService friendService;

	// 邀請好友
	@RequestMapping(path = { "/friend/invite" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> invFriend(
			@RequestParam(name = "memberidf") Integer memberidf,
			@RequestAttribute("memberid") Integer memberid,
			HttpServletRequest request) {
		// 邀請好友 使用 memberid , memberidf
		Map<String, Object> result = new HashMap<>();
		if (memberidf == null || memberid == null) {
			result.put("state", false);

		} else {
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
				request.setAttribute("id", check.getId());
				request.setAttribute("ntype", 1);
				result.put("state", true);
			} else {
				result.put("state", false);
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 刪除好友
	@RequestMapping(path = { "/friend/delete" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> delFriend(
			@RequestParam(name = "id") Integer id,
			@RequestAttribute("memberid") Integer memberid) {
		// 刪除好友 使用 memberid , id
		Map<String, Object> result = new HashMap<>();
		if (id == null || memberid == null) {
			result.put("state", false);

		} else {

			FriendBean bean = new FriendBean();
			bean.setId(id);
			bean.setMemberid(memberid);
			// 刪除成功
			// result.put("state", true);
			// 刪除失敗
			// result.put("state", false);
			result.put("state", friendDAO.delete(bean));
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 拒絕好友邀請
	@RequestMapping(path = { "/friend/reject" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> rejectFriend(
			@RequestParam(name = "id") Integer id,
			@RequestAttribute("memberid") Integer memberidf) {
		// 拒絕好友 使用 memberidf , id
		Map<String, Object> result = new HashMap<>();
		if (id == null || memberidf == null) {
			result.put("state", false);

		} else {
			FriendBean bean = new FriendBean();
			bean.setId(id);
			bean.setMemberidf(memberidf);
			bean.setState(2);
			// 拒絕成功
			// result.put("state", true);
			// 拒絕失敗
			// result.put("state", false);
			result.put("state", friendService.changeStateByMemberF(bean));
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 接受好友邀請
	@RequestMapping(path = { "/friend/accept" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> acceptFriend(
			@RequestParam(name = "id") Integer id,
			@RequestAttribute("memberid") Integer memberidf,
			HttpServletRequest request) {
		// 接受好友邀請 使用 memberid , memberfid 更改state為1
		Map<String, Object> result = new HashMap<>();
		if (id == null || memberidf == null) {
			result.put("state", false);

		} else {
			FriendBean bean = new FriendBean();
			bean.setId(id);
			bean.setMemberidf(memberidf);
			bean.setState(1);		
			// 接受成功
			// result.put("state", true);
			// 接受失敗
			// result.put("state", false);
			if(friendService.changeStateByMemberF(bean)) {
				request.setAttribute("id", bean.getId());
				request.setAttribute("ntype", 2);
				result.put("state", true );
			}else {
				result.put("state", false );
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
