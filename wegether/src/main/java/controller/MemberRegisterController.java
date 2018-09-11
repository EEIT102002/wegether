package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import model.MemberBean;
import model.ServiceBean;
import model.dao.implement.MemberInfoDAOHibernate;


@Controller
public class MemberRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberInfoDAOHibernate memberInfoDAOHibernate;
   
	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

	}
	@RequestMapping(
			path= {"/register.controller"}
		)
public String selectMethod(Model model,MemberBean bean) {
	List<MemberBean> result = (List<MemberBean>) memberInfoDAOHibernate.select(4);
//	model.addAttribute("select", result);
	return "register.success";
}

}
