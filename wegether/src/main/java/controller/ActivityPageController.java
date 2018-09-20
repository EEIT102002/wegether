package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import Service.ActivityPageService;
import model.ActivityBean;
import model.AttendBean;
import model.MemberBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.AttendDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;
import pictureconvert.TimeConvert;

@Controller
public class ActivityPageController {
	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private PictureDAO pictureDAO;

	@Autowired
	private AttendDAO attendDAO;

	@Autowired
	private MsgDAO msgDAO;
	
	@Autowired
	private ActivityPageService activityPageService;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	///activityPage.controller?actid=1#
	// "/activity/{id}"
	
	// @PathVariable(name= "id",required= false) Integer actid
	@RequestMapping("/activityPage.controller")
	public String method(Model model, Integer actid,
			@RequestAttribute(name = "memberid", required = false) Integer memberid) {
			
		System.out.println("actid:"+actid+" / memberid :" + memberid);

		Map<String, String> activityTime = new HashMap<String, String>();
		String actbegin = null , dateline = null;
		ActivityBean activityBean = null;
		MemberBean hostBean = null;
		List<Integer> attBeans = null, actPicList = null;
		int state=0;
		if(actid!=null) {
			activityBean = activityPageService.selectActBean(actid);
			Date actBeginTime = activityBean.getActbegin();
			Date now = new java.util.Date();
			
			if(now.before(actBeginTime)) state=0;
			else state=1;

			activityTime = activityPageService.activityTime(actid);
			actbegin = activityTime.get("actbegin");
			dateline = activityTime.get("dateline");
			
			hostBean = activityPageService.hostBean(actid);
			attBeans = activityPageService.attBeans(actid);
			actPicList = activityPageService.actPicList(actid);
		}else {
			return "activityPage.error";
		}
		
				

		model.addAttribute("actBean", activityBean);
		model.addAttribute("hostBean", hostBean);
		model.addAttribute("state", state);

		if (actPicList.size() != 0) {
			model.addAttribute("actPicList", actPicList);
			model.addAttribute("actPicListSize", actPicList.size());
		}else {
			model.addAttribute("actPicList", null);
			model.addAttribute("actPicListSize", 0);
		}


		if (attBeans.size() != 0) {
			model.addAttribute("attBeans", attBeans);
			model.addAttribute("attedNumber", attBeans.size() + " 個人報名參加");// 報名人數
		}else {
			model.addAttribute("attBeans", null);
			model.addAttribute("attedNumber", null);// 報名人數
		}
		
		if (actbegin != null)
			model.addAttribute("actbegin", actbegin);
		else
			model.addAttribute("actbegin", null);

		if (dateline != null)
			model.addAttribute("dateline", dateline);
		else
			model.addAttribute("dateline", null);
		
		if (memberid != null)
			model.addAttribute("memberid", memberid);
		else
			model.addAttribute("memberid", null);


				return "activityPage";

	}
}
