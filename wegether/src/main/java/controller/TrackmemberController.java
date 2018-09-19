package controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import Service.TrackmemberService;
import model.TrackmemberBean;

@Controller
@SessionAttributes(names = { "select" })
public class TrackmemberController {
	@Autowired
	private TrackmemberService trackmemberService;


	@RequestMapping(path = { "/trackmember/add" })
	protected @ResponseBody ResponseEntity<?>  addTrack(
			@RequestParam Integer memberid,@RequestAttribute("memberid") Integer fanid){
		Map<String, Object>result = new HashMap<>();
		if(memberid == null || fanid == null) {
			result.put("state", false);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
			TrackmemberBean bean = trackmemberService.insert(memberid,fanid);// 加追蹤
		
		if (bean != null) {
			result.put("state", true);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.put("state", false);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}


	@RequestMapping(path = { "/trackmember/delete" })
	protected @ResponseBody ResponseEntity<?>  deleteTrack(
			@RequestParam Integer memberid,@RequestAttribute("memberid") Integer fanid){
		Map<String, Object>result = new HashMap<>();
		if(memberid == null || fanid == null) {
			result.put("state", false);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		boolean state = trackmemberService.delete(memberid, fanid);// 加追蹤
		
		if (state) {
			result.put("state", true);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			result.put("state", false);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
}
