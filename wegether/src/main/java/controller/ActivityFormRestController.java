package controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Service.ActivityFormService;
import model.AttendBean;
import model.dao.AttendDAO;

@RestController
public class ActivityFormRestController {
	@Autowired
	private ActivityFormService service;
	@Autowired
	private AttendDAO attendDAO;
	
	
	@PostMapping(path = "/activity/apply/{id}", produces = { "application/json" })
	public ResponseEntity<?> getApply(
			@RequestParam Map<String, String> data,@PathVariable Integer id,
			@RequestAttribute(name = "memberid", required = false) Integer memberid,
			Model model){
		Map<String, String> errors = new HashMap<>();
		Map<String, Object> result = new HashMap<>();
		System.out.println(id);
		result.put("errors", errors);
		String answers = service.checkActivityForm(id, data, errors);
		System.out.println(answers);
		if(answers == null) {
			result.put("state", false);
		}else if(errors.size() <= 0 ) {
			result.put("state", true);
//			AttendBean bean = new AttendBean();
//			bean.setActivityid(id);
//			bean.setMemberid(memberid);
//			bean.setForm(answers);
//			bean = attendDAO.insert(bean);
//			if(bean == null) {
//				result.put("state", false);
//			}
			model.addAttribute("id", id);
			model.addAttribute("ntype", 4);
		}else {	
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
			
	}
	
	@GetMapping(path = { "/activity/applyform/{id}" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> searchByNickname(@PathVariable Integer id){
		if(id  == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		JSONObject result = service.getApplyForm(id);
		
		if(result != null) {	
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
