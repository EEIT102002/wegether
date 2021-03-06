package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.AttendService;
import model.AttendBean;

@Controller
public class AttendController {
	@Autowired
	private AttendService attendService;
	
	@RequestMapping(path= {"/attend/{stateStr}/{attendid}"})
	public @ResponseBody ResponseEntity<?> RespondAttend(
			@PathVariable(name="stateStr") String stateStr, @PathVariable(name="attendid") Integer attendid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		Integer state = null;
		if("accept".equals(stateStr)) {
			state = 1;
		}else if("reject".equals(stateStr)) {
			state = 2;
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		AttendBean bean = attendService.respond(attendid, memberid, state);
		if(bean != null) {
			request.setAttribute("id", bean.getId());
			if(state == 1) {
				request.setAttribute("ntype", 6);
			}else if(state == 2) {
				request.setAttribute("ntype", 7);
			}
			return new ResponseEntity<>(true,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping(path= {"/attend/activity/{activityid}/invite/{friendid}"})
	public @ResponseBody ResponseEntity<?> inviteAttned(
			@PathVariable(name="activityid") Integer activityid, @PathVariable(name="friendid") Integer invitemember,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		AttendBean bean = attendService.invite(activityid, memberid, invitemember);
		if(bean != null) {
			request.setAttribute("id", bean.getId());
			request.setAttribute("ntype", 5);
			return new ResponseEntity<>(true,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(path= {"/attend/invite/{stateStr}/{attendid}"})
	public @ResponseBody ResponseEntity<?> RespondAttendInvite(
			@PathVariable(name="stateStr") String stateStr, @PathVariable(name="attendid") Integer attendid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		Integer state = null;
		if("accept".equals(stateStr)) {
			state = 1;
		}else if("reject".equals(stateStr)) {
			state = 2;
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		AttendBean bean = attendService.respondInvite(attendid, memberid, state);
		return AttendInviteState(bean, request);	
	}
	
	@RequestMapping(path= {"/attend/invite/{stateStr}/activity/{activityid}"})
	public @ResponseBody ResponseEntity<?> RespondAttendInviteByActivity(
			@PathVariable(name="stateStr") String stateStr, @PathVariable(name="activityid") Integer activityid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		Integer state = null;
		if("accept".equals(stateStr)) {
			state = 1;
		}else if("reject".equals(stateStr)) {
			state = 2;
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		AttendBean bean = attendService.respondInviteByActivity(activityid, memberid, state);
		return AttendInviteState(bean, request);	
	}
	
	private ResponseEntity<?> AttendInviteState(AttendBean bean,HttpServletRequest request){
		if(bean != null) {
			request.setAttribute("id", bean.getId());
			if(bean.getState() == 1) {
				request.setAttribute("ntype", 8);
			}else if(bean.getState() == 2) {
				request.setAttribute("ntype", 9);
			}
			return new ResponseEntity<>(true,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(path= {"/attend/unrespond/activity/{id}"})
	public @ResponseBody ResponseEntity<?> unrespondAttends(@PathVariable(name="id") Integer activityid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		List<AttendBean> beans = attendService.unrespondApplys(activityid, memberid);
		JSONArray result = attendService.attendToJsons(beans);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/attend/accepted/activity/{id}"})
	public @ResponseBody ResponseEntity<?> acceptAttends(@PathVariable(name="id") Integer activityid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		List<AttendBean> beans = attendService.acceptApplys(activityid, memberid);
		JSONArray result = attendService.attendToJsons(beans);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/attend/invite/activity/{id}"})
	public @ResponseBody ResponseEntity<?> inviteAttends(@PathVariable(name="id") Integer activityid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		List<AttendBean> beans = attendService.inviteApplys(activityid, memberid);
		JSONArray result = attendService.attendToJsons(beans);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/attend/cancel/{attendid}"})
	public @ResponseBody ResponseEntity<?> cancelAttend(
			@PathVariable(name="attendid") Integer attendid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		if(attendService.cancelAttend(attendid, memberid)) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping(path= {"/attend/cancel/activity/{activityid}"})
	public @ResponseBody ResponseEntity<?> cancelAttendByActivityid(
			@PathVariable(name="activityid") Integer activityid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		if(attendService.cancelAttendByActivity(activityid, memberid)) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping(path= {"/attend/{attendid}"})
	public @ResponseBody ResponseEntity<?> getAttend(
			@PathVariable(name="attendid") Integer attendid,
			@RequestAttribute("memberid") Integer memberid, HttpServletRequest request) {
		
			return new ResponseEntity<>(attendService.getAttend(attendid, memberid),HttpStatus.OK);		
	}
}
