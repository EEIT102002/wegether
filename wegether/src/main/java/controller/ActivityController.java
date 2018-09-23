package controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.ActivityService;

@Controller
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(path= {"/member/activity/host/now"}, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> hostActivity(
			@RequestAttribute("memberid") Integer hostid, HttpServletRequest request) {
		JSONArray result = activityService.hostNow(hostid);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/member/activity/host/history"}, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> hostActivityHistory(
			@RequestAttribute("memberid") Integer hostid, HttpServletRequest request) {
		JSONArray result = activityService.hostHistory(hostid);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/member/activity/attend/now"}, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> attendActivity(
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		JSONArray result = activityService.attendNow(memberid);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/member/activity/attend/history"}, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> attendActivityHistory(
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		JSONArray result = activityService.attendHistory(memberid);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/member/activity/applying"}, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> applyingActivity(
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		JSONArray result = activityService.applying(memberid);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/member/activity/beInvited"}, produces = { "application/json" })
	public @ResponseBody ResponseEntity<?> beInvitedActivity(
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		JSONArray result = activityService.beInvited(memberid);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

}
