package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import Service.AttendService;
import model.AttendBean;
import model.dao.AttendDAO;

@RestController
public class ActivityFormRestController {
	@Autowired
	private ActivityFormService service;
	@Autowired
	private AttendService attendService;

	@PostMapping(path = "/Rest/activity/apply/{id}", produces = { "application/json" })
	public ResponseEntity<?> applyActivity(@RequestParam Map<String, String> data,
			@PathVariable(name = "id") Integer activityid,
			@RequestAttribute(name = "memberid", required = false) Integer memberid,
			HttpServletRequest request) {
		if (memberid == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Map<String, String> errors = new HashMap<>();
		Map<String, Object> result = new HashMap<>();
		result.put("errors", errors);
		String answer = service.checkActivityForm(activityid, data, errors);
		System.out.println(answer);
		if (answer == null) {
			result.put("state", false);
		}else if (errors.size() <= 0) {
			AttendBean bean = attendService.apply(activityid, memberid, answer);
			if (bean != null) {
				result.put("state", true);
				request.setAttribute("id", bean.getId());
				request.setAttribute("ntype", 4);
			} else {
				result.put("state", false);
			}
		} else {
			result.put("state", false);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping(path = { "/Rest/activity/applyform/{id}" }, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> getActivityForm(@PathVariable Integer id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		JSONObject result = service.getApplyForm(id);

		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
