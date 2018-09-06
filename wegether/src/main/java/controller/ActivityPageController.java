package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		attBean.forEach(att->{			
			List<PictureBean> memPicBean = pictureDAO.selectByMember(att.getMemberid());	//報名人員的照片名單
			memPicList.add(PictureConvert.convertBase64Image(memPicBean.get(0).getPicture()));//memPicBean.get(0) -->第一筆照片物件
		});
		
		actPicBean.forEach(pic->{
			actPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
		});
		
		hostPicBean.forEach(pic->{
			hostPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
		});

		
		model.addAttribute("actBean",actBean);
		model.addAttribute("hostBean",hostBean);
		
		model.addAttribute("actPicList",actPicList);
		model.addAttribute("hostPicList",hostPicList);
		
		if(memPicList.size()!=0) 
			model.addAttribute("memPicList",memPicList);
		else
			model.addAttribute("memPicList",null);
		
		if(attBean.size()!=0) 
			model.addAttribute("attedNumber",attBean.size()+" 個申請人");//報名人數
		else
			model.addAttribute("attedNumber",null);//報名人數
		
		return "activityPage";
	}
}
