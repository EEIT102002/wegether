package controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.MemberBean;
import model.dao.implement.MemberDAOHibernate;

@Controller
@SessionAttributes(names={"mem"})
public class MemeberPersonalController {
	@Autowired
	private MemberDAOHibernate memberDaoHibernate;
	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
	}
	@RequestMapping(
			path={"/personal.controller"}
	)
	public String processTest(Model model,MemberBean bean) {
//		 List<MemberBean> result = memberDaoHibernate.select();
		MemberBean result = memberDaoHibernate.select(2);
		System.out.println(result);
		model.addAttribute("mem",result);
		return "personal.success";
	}

}
