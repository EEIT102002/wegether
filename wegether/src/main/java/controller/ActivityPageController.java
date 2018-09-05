package controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import model.ActivityBean;
import model.MemberBean;
import model.PictureBean;
import model.dao.implement.ActivityDAOHibernate;
import model.dao.implement.MemberDAOHibernate;
import model.dao.implement.PictureDAOHibernate;
import pictureconvert.PictureConvert;

@Controller
public class ActivityPageController {
	@Autowired
	private ActivityDAOHibernate activityDAOHibernate;
	
	@Autowired
	private MemberDAOHibernate memberDaoHibernate;
	
	@Autowired
	private PictureDAOHibernate pictureDAOHibernate;
	
	


	
	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));		
	}
	
	@RequestMapping("/activityPage.controller")
	public String method(Model model) {
		ActivityBean actBean = activityDAOHibernate.selectId(1);
		MemberBean memBean = memberDaoHibernate.select(actBean.getHostid());
		List<PictureBean> actPicBean = pictureDAOHibernate.selectByActivity(actBean.getId());		
		List<PictureBean> memPicBean = pictureDAOHibernate.selectByMember(memBean.getId());
		
		String base64Image = PictureConvert.convertBase64Image(actPicBean.get(0).getPicture());

		
		model.addAttribute("actBean",actBean);
		model.addAttribute("memBean",memBean);
		model.addAttribute("actPicBean",actPicBean);
		model.addAttribute("memPicBean",memPicBean);
		model.addAttribute("base64Image",base64Image);
		
		return "activityPage";
	}
}
