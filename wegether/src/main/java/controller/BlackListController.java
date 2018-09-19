package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.BlackListService;
import model.BlacklistBean;

@Controller
public class BlackListController {
	@Autowired
	private BlackListService blackListService;

	@RequestMapping(path = { "/blacklist/add" })
	public @ResponseBody ResponseEntity<?> addBlackList(@RequestParam Integer blackid,
			@RequestAttribute("memberid") Integer memberid) {
		Map<String, Object> result = new HashMap<>();
		if (memberid == null || blackid == null) {
			result.put("state", false);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		BlacklistBean bean = blackListService.insert(blackid, memberid);// 加黑名單
		if (bean != null) {
			result.put("state", true);
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = { "/blacklist/delete" })
	public @ResponseBody ResponseEntity<?> deleteBlackList(@RequestParam Integer blackid,
			@RequestAttribute("memberid") Integer memberid) {
		Map<String, Object> result = new HashMap<>();
		if (memberid == null || blackid == null) {
			result.put("state", false);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		if (blackListService.delete(blackid, memberid)) {
			result.put("state", true);
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
