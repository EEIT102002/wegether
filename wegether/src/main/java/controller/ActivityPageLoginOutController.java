package controller;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Service.ActivityService;
import Service.AttendService;
import model.ActivityBean;
import model.AttendBean;
import model.MemberBean;
import model.MsgBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.AttendDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;

@RestController
public class ActivityPageLoginOutController {

	@Autowired
	private AttendService attendService;
	@Autowired
	private ActivityService activityService;

	
	@GetMapping( path= {"/wegether/activity/attend/check/{id}"}, produces= {"application/json"})
	public  ResponseEntity<?>  attendCheck(@PathVariable(name="id") Integer activityid,
		@RequestAttribute("memberid") Integer memberid) throws URISyntaxException {
			System.out.println("activityid="+activityid+" memberid="+memberid);
		
		Map<String, Object> result = new HashMap<>();
		boolean host = activityService.checkHost(memberid, activityid);
		result.put("host",host);
		if(!host) {
			AttendBean bean = attendService.attendCheck(activityid,memberid);
			if(bean != null) {
				result.put("state", bean.getState() );
				result.put("attendid", bean.getId());
			}else {
				result.put("state", null);
			}
		}
		return new  ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
}
