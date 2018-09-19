package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.NoticeService;
import model.NoticeBean;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping(path = { "/noitce/unread" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> unreadNotices(@RequestAttribute("memberid") Integer memberid) {

		Integer count = noticeService.unreadCount(memberid);

		if (count != null) {
			Map<String, Object> result = new HashMap<>();
			result.put("count", count);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path = { "/noitce/unread/clear" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> clearUnreadNotices(@RequestAttribute("memberid") Integer memberid) {

		boolean check = noticeService.clearNotices(memberid);

		if (check) {
			Map<String, Object> result = new HashMap<>();
			result.put("state", true);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path = { "/noitce/select/{first}" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> selectNotices(@RequestAttribute("memberid") Integer memberid,
			@PathVariable(name = "first") Integer first) {

		List<NoticeBean> beans = noticeService.selectNotices(memberid, first);
		Map<String, Object> result = new HashMap<>();
		if (first == 0) {
			result.put("count", noticeService.noticesCount(memberid));
		}
		if (beans.size() > 0) {
			result.put("state", true);
			result.put("notices", beans);
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
