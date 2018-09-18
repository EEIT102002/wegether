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

	
	@GetMapping( path= {"/wegether/activity/attend/check/{id}"}, produces= {"application/json"})
	public  ResponseEntity<Map<String, Integer>>  attendCheck(@PathVariable(name="id") Integer activityid,
		@RequestAttribute(name = "memberid", required = false) Integer memberid) throws URISyntaxException {
			System.out.println("activityid="+activityid+" memberid="+memberid);
		
		if(memberid!=null) {
			System.out.println("resultMap1");
			//1:主辦人 2:已報名者 3:未報名者
			Map resultMap = attendService.attendCheck(activityid,memberid);
			System.out.println("resultMap="+resultMap);
			return new  ResponseEntity<Map<String, Integer>>(resultMap,HttpStatus.OK );
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
