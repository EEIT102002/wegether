package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.AttendDAO;
import model.dao.MemberDAO;
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
	
	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));		
	}
	
	@RequestMapping("/activityPage.controller")
	public String method(Model model,String actid) {
		System.out.println("actid="+actid);
		List<String> actPicList = new ArrayList<>();		
		List<String> hostPicList = new ArrayList<>();
		List<String> memPicList = new ArrayList<>();
		
		ActivityBean actBean = activityDAO.selectId(Integer.parseInt(actid));
		MemberBean hostBean = memberDAO.select(actBean.getHostid());		
		List<AttendBean> attBean = attendDAO.selectByActID(Integer.parseInt(actid));//報名人員名單
		
		List<PictureBean> actPicBean = pictureDAO.selectByActivity(actBean.getId());	
		List<PictureBean> hostPicBean = pictureDAO.selectByMember(hostBean.getId());
		
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
		
		
		//時間轉換
		String[] months = {"一 月", "二 月", "三 月", "四 月",
                "五 月", "六 月", "七 月", "八 月",
                "九 月", "十 月", "十一 月", "十二 月"};
		
		String[] week = {"(日)","(一)", "(二)", "(三)", "(四)",
                "(五)", "(六)"};

		String actbegin = null;
		Calendar actTime = Calendar.getInstance();
		if(actBean.getActbegin()!=null) {
			actTime.setTime(actBean.getActbegin());
			int atMonth = actTime.get(Calendar.MONTH);
			int atDay = actTime.get(Calendar.DAY_OF_MONTH);
			int atWeek = actTime.get(Calendar.DAY_OF_WEEK);
			int atHour = actTime.get(Calendar.HOUR_OF_DAY);
			String atHourStr = Integer.toString(atHour);
			  if (atHour<10) atHourStr = "0"+atHourStr;
			
			int atMinute = actTime.get(Calendar.MINUTE);
			String atMinuteStr = Integer.toString(atMinute);
			  if (atMinute<10) atMinuteStr = "0"+atMinuteStr;
			
			actbegin = months[atMonth]+" "+atDay+" 日  "+week[atWeek-1]+" "+atHour+" : "+atMinuteStr;
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
		
		if(hostPicList.size()!=0) model.addAttribute("hostPicList",hostPicList);
		else model.addAttribute("hostPicList",null);
			
		if(memPicList.size()!=0) model.addAttribute("memPicList",memPicList);
		else model.addAttribute("memPicList",null);
		
		if(attBean.size()!=0) 	model.addAttribute("attedNumber",attBean.size()+" 個申請人");//報名人數
		else model.addAttribute("attedNumber",null);//報名人數
		
		if(actbegin!=null) model.addAttribute("actbegin",actbegin);
		else model.addAttribute("actbegin",null);
		
		if(dateline!=null) model.addAttribute("dateline",dateline);
		else model.addAttribute("dateline",null);
		
		return "activityPage";
	}
}
