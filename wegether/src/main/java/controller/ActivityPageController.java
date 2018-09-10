package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

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
		public String method(Model model,String actid) {
		
		//時間轉換
				String[] months = {"一 月", "二 月", "三 月", "四 月",
		                "五 月", "六 月", "七 月", "八 月",
		                "九 月", "十 月", "十一 月", "十二 月"};
				
				String[] week = {"(日)","(一)", "(二)", "(三)", "(四)",
		                "(五)", "(六)"};
				
		System.out.println("actid="+actid);
		List<String> actPicList = new ArrayList<>();		
		List<String> hostPicList = new ArrayList<>();
		List<String> memPicList = new ArrayList<>();
    	List<Map> msgsList = new ArrayList<>();
		
		
		ActivityBean actBean = activityDAO.selectId(Integer.parseInt(actid));
		MemberBean hostBean = memberDAO.select(actBean.getHostid());		
		List<AttendBean> attBean = attendDAO.selectByActID(Integer.parseInt(actid));//報名人員名單
		
		List<PictureBean> actPicBean = pictureDAO.selectByActivity(Integer.parseInt(actid));	
		List<PictureBean> hostPicBean = pictureDAO.selectByMember(hostBean.getId());
		List<MsgBean>  msgBean = msgDAO.selectByActivity(Integer.parseInt(actid));
	
		
		if(attBean.size()!=0) 
				attBean.forEach(att->{			
					List<PictureBean> memPicBean = pictureDAO.selectByMember(att.getMemberid());	//報名人員的照片名單
						if(memPicBean.size()!=0)
							memPicList.add(PictureConvert.convertBase64Image(memPicBean.get(0).getPicture()));//memPicBean.get(0) -->第一筆照片物件
					});
		
		if(actPicBean.size()!=0) 
			actPicBean.forEach(pic->{
					actPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
				});
		
		if(hostPicBean.size()!=0) 
			hostPicBean.forEach(pic->{
					hostPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
				});
		
		
		if(msgBean.size()!=0) 
			msgBean.forEach(msg->{
				Calendar msgtime = Calendar.getInstance();
				Map<String, String> msgsMap = new HashMap<String,String>();
				msgsMap.put("nickname", memberDAO.select(msg.getMemberid()).getNickname());
				String picMemStr = PictureConvert.convertBase64Image(pictureDAO.selectByMember(msg.getMemberid()).get(0).getPicture());
				msgsMap.put("picMem", picMemStr);
				msgsMap.put("content", msg.getContent());
				
				msgtime.setTime(msg.getMsgtime());
				int msgMonth = msgtime.get(Calendar.MONTH)+1;
				int msgDay = msgtime.get(Calendar.DAY_OF_MONTH);
				int msgHour = msgtime.get(Calendar.HOUR_OF_DAY)+8;
				String msgHourStr = Integer.toString(msgHour);
				if (msgHour>24) msgHour = msgHour-24;
				if (msgHour<10) msgHourStr = "0"+msgHourStr;
				
				int msgMinute = msgtime.get(Calendar.MINUTE);
				String msgMinuteStr = Integer.toString(msgMinute);
				  if (msgMinute<10) msgMinuteStr = "0"+msgMinuteStr;
				
				String  msgtimeStr = msgMonth+" 月 "+msgDay+" 日  "+msgHourStr+" : "+msgMinuteStr;
				msgsMap.put("msgtime",msgtimeStr );
				msgsList.add(msgsMap);
				
				System.out.println(memberDAO.select(msg.getMemberid()).getNickname()+" : "+msgtimeStr+" : "+ msg.getContent());
			});
		
		
		

		String actbegin = null;
		Calendar actTime = Calendar.getInstance();
		if(actBean.getActbegin()!=null) {
			actTime.setTime(actBean.getActbegin());
			int atMonth = actTime.get(Calendar.MONTH)+1;
			int atDay = actTime.get(Calendar.DAY_OF_MONTH);
			int atWeek = actTime.get(Calendar.DAY_OF_WEEK);
			int atHour = actTime.get(Calendar.HOUR_OF_DAY);
			String atHourStr = Integer.toString(atHour);
			  if (atHour<10) atHourStr = "0"+atHourStr;
			
			int atMinute = actTime.get(Calendar.MINUTE);
			String atMinuteStr = Integer.toString(atMinute);
			  if (atMinute<10) atMinuteStr = "0"+atMinuteStr;
			
			actbegin = atMonth+" 月 "+atDay+" 日  "+week[atWeek-1]+" "+atHourStr+" : "+atMinuteStr;
		}
		
		String dateline = null;
		Calendar dl = Calendar.getInstance();
		if(actBean.getDateline()!=null) {
			dl.setTime(actBean.getDateline());
			int dlMonth = dl.get(Calendar.MONTH) +1;
			int dlDay = dl.get(Calendar.DAY_OF_MONTH);
			int dlWeek = dl.get(Calendar.DAY_OF_WEEK);
		
			dateline = dlMonth+"/"+dlDay+week[dlWeek-1];
		}
		
		
		model.addAttribute("actBean",actBean);
		model.addAttribute("hostBean",hostBean);
		
		if(actPicList.size()!=0) model.addAttribute("actPicList",actPicList);
		else model.addAttribute("actPicList",null);
		
		if(actPicList.size()!=0) model.addAttribute("actPicListSize",actPicList.size()-1);
		else model.addAttribute("actPicListSize",null);
		
		if(hostPicList.size()!=0) model.addAttribute("hostPicList",hostPicList);
		else model.addAttribute("hostPicList",null);
			
		if(memPicList.size()!=0) model.addAttribute("memPicList",memPicList);
		else model.addAttribute("memPicList",null);
		
		if(attBean.size()!=0) 	model.addAttribute("attedNumber",attBean.size()+" 個人報名參加");//報名人數
		else model.addAttribute("attedNumber",null);//報名人數
		
		if(actbegin!=null) model.addAttribute("actbegin",actbegin);
		else model.addAttribute("actbegin",null);
		
		if(dateline!=null) model.addAttribute("dateline",dateline);
		else model.addAttribute("dateline",null);
		
		if(msgsList!=null) model.addAttribute("msgsList", msgsList);
		else model.addAttribute("msgsList",null);
		
		msgsList.forEach(x->{
		System.out.println( x.get("nickname") + " : " + x.get("msgtime") + " : " + x.get("content"));	
		
		});
		
		return "activityPage";
 

	}
}
