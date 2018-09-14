package controller;

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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping("/activityPage.controller")
	public String method(Model model, Integer actid,
			@RequestAttribute(name = "memberid", required = false) Integer memberid) {
		System.out.println("id :" + memberid);
		
		// flag= 0:未登入 1:主辦人 2:已報名者 3:未報名者
		Integer flag = 0;		
		if (memberid != null) flag = 3;
		// 時間轉換

		String[] week = { "(日)", "(一)", "(二)", "(三)", "(四)", "(五)", "(六)" };
		System.out.println("actid=" + actid);
		List<String> actPicList = new ArrayList<>();
		List<String> hostPicList = new ArrayList<>();
		List<Map> memPicList = new ArrayList<>();
		List<Map> msgsList = new ArrayList<>();

		if (actid == null)	actid = 1;
		ActivityBean actBean = activityDAO.selectId(actid);
		MemberBean hostBean = memberDAO.select(actBean.getHostid());
		
		List<PictureBean> actPicBeans = pictureDAO.selectByActivity(actid);
		List<PictureBean> hostPicBeans = pictureDAO.selectByMember(hostBean.getId());
		List<MsgBean> msgBeans = msgDAO.selectByActivity(actid);
		Set<AttendBean> attBeans = actBean.getAttendBean();
		
			
			if (attBeans.size() != 0) {
				for(AttendBean att :attBeans) {
					List<PictureBean> memPicBeans = pictureDAO.selectByMember(att.getMemberid()); // 報名人員的照片名單
					Map<String, String> attMap = new HashMap<String, String>();
					if (memPicBeans.size() != 0) {
							attMap.put("memberId", att.getMemberid().toString());
							attMap.put("memberPic", PictureConvert.convertBase64Image(memPicBeans.get(0).getPicture()));// memPicBean.get(0) -->第一筆照片物件
							memPicList.add(attMap);
							if (flag != 2 && memberid == att.getMemberid())	flag = 2;
						}					
				};
			}
			
		if (memberid == actBean.getHostid()) flag = 1;

		if (actPicBeans.size() != 0)
			actPicBeans.forEach(pic -> {
				actPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
			});

		if (hostPicBeans.size() != 0)
			hostPicBeans.forEach(pic -> {
				hostPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
			});

		if (msgBeans.size() != 0)
			msgBeans.forEach(msg -> {
				Calendar msgtime = Calendar.getInstance();
				Map<String, String> msgsMap = new HashMap<String, String>();
				msgsMap.put("nickname", memberDAO.select(msg.getMemberid()).getNickname());
				String picMemStr = PictureConvert
						.convertBase64Image(pictureDAO.selectByMember(msg.getMemberid()).get(0).getPicture());
				msgsMap.put("picMem", picMemStr);
				msgsMap.put("memberId", msg.getMemberid().toString());
				msgsMap.put("content", msg.getContent());

				msgtime.setTime(msg.getMsgtime());
				int msgMonth = msgtime.get(Calendar.MONTH) + 1;
				int msgDay = msgtime.get(Calendar.DAY_OF_MONTH);
				int msgHour = msgtime.get(Calendar.HOUR_OF_DAY) + 8;
				String msgHourStr = Integer.toString(msgHour);
				if (msgHour > 24)
					msgHour = msgHour - 24;
				if (msgHour < 10)
					msgHourStr = "0" + msgHourStr;

				int msgMinute = msgtime.get(Calendar.MINUTE);
				String msgMinuteStr = Integer.toString(msgMinute);
				if (msgMinute < 10)
					msgMinuteStr = "0" + msgMinuteStr;

				String msgtimeStr = msgMonth + " 月 " + msgDay + " 日  " + msgHourStr + " : " + msgMinuteStr;
				msgsMap.put("msgtime", msgtimeStr);
				msgsList.add(msgsMap);

			});

		String actbegin = null;
		Calendar actTime = Calendar.getInstance();
		if (actBean.getActbegin() != null) {
			actTime.setTime(actBean.getActbegin());
			int atMonth = actTime.get(Calendar.MONTH) + 1;
			int atDay = actTime.get(Calendar.DAY_OF_MONTH);
			int atWeek = actTime.get(Calendar.DAY_OF_WEEK);
			int atHour = actTime.get(Calendar.HOUR_OF_DAY);
			String atHourStr = Integer.toString(atHour);
			if (atHour < 10)
				atHourStr = "0" + atHourStr;

			int atMinute = actTime.get(Calendar.MINUTE);
			String atMinuteStr = Integer.toString(atMinute);
			if (atMinute < 10)
				atMinuteStr = "0" + atMinuteStr;

			actbegin = atMonth + " 月 " + atDay + " 日  " + week[atWeek - 1] + " " + atHourStr + " : " + atMinuteStr;
		}

		String dateline = null;
		Calendar dl = Calendar.getInstance();
		if (actBean.getDateline() != null) {
			dl.setTime(actBean.getDateline());
			int dlMonth = dl.get(Calendar.MONTH) + 1;
			int dlDay = dl.get(Calendar.DAY_OF_MONTH);
			int dlWeek = dl.get(Calendar.DAY_OF_WEEK);

			dateline = dlMonth + "/" + dlDay + week[dlWeek - 1];
		}

		model.addAttribute("actBean", actBean);
		model.addAttribute("hostBean", hostBean);

		if (actPicList.size() != 0)
			model.addAttribute("actPicList", actPicList);
		else
			model.addAttribute("actPicList", null);

		if (actPicList.size() != 0)
			model.addAttribute("actPicListSize", actPicList.size() - 1);
		else
			model.addAttribute("actPicListSize", null);

		if (hostPicList.size() != 0)
			model.addAttribute("hostPicList", hostPicList);
		else
			model.addAttribute("hostPicList", null);
		
		if (memPicList.size() != 0)
			model.addAttribute("memPicList", memPicList);
		else
			model.addAttribute("memPicList", null);

		if (attBeans.size() != 0)
			model.addAttribute("attedNumber", attBeans.size() + " 個人報名參加");// 報名人數
		else
			model.addAttribute("attedNumber", null);// 報名人數

		if (actbegin != null)
			model.addAttribute("actbegin", actbegin);
		else
			model.addAttribute("actbegin", null);

		if (dateline != null)
			model.addAttribute("dateline", dateline);
		else
			model.addAttribute("dateline", null);

		if (msgsList != null)
			model.addAttribute("msgsList", msgsList);
		else
			model.addAttribute("msgsList", null);

		model.addAttribute("flag", flag);
		

				return "activityPage";

	}
}
