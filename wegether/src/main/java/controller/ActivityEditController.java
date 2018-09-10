package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.ActivityBean;
import model.dao.ActivityDAO;

@Controller
public class ActivityEditController {

	@Autowired
	ActivityBean activityBean;
	@Autowired
	ActivityDAO activityDAO;

	@RequestMapping("/actEdit.getBean.controller")
	public String getBeanToJsp(Model model, int actid) {
		actid = 55; //整合時記得拿掉
		ActivityBean result = activityDAO.selectId(actid);

		model.addAttribute("result", result);
		
		return "actEditSuc.page";
	}
	
	@RequestMapping("/actEdit.edit.controller")
	public String activityEdit() {
		
		return "";
	}
}
