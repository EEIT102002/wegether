package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.ActivityBean;
import model.dao.ActivityDAO;

@Controller
public class ActivityCreateController {

	@Autowired
	private ActivityDAO activityDAO;

	@RequestMapping(path = { "/actCreate.controller" })
	public String actCreate(Model model, ActivityBean activityBean, BindingResult bindingResult,
			@RequestParam("startTime") String actbegin) {
		System.out.println("actCreate()");

		// Map<String, String> errors = new HashMap<>();
		// model.addAttribute("errMsgs", errors);

		System.out.println(actbegin);
		System.out.println(activityBean);
		// activityDAO.insert(activityBean);

		// if (activityBean!=null) {
		// System.out.println("a="+activityBean.getCity());
		// return"actCreateSuc.page";
		// }

		return "actCreateErr.page";
	}
}
