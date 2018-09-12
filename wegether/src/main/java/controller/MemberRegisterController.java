package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.MemberBean;

import model.dao.implement.MemberDAOHibernate;

@Controller
public class MemberRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberDAOHibernate memberDAOHibernate;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

	}

	@RequestMapping(path = { "/register.controller" }, method = RequestMethod.POST)
	public String selectMethod(Model model, MemberBean bean) {

		// 接收資料
		// 轉換資料
		Map<String, String> errors = new HashMap<>();

		if (bean.getAccount() == null || bean.getAccount().length() == 0) {
			errors.put("account", "帳號未輸入");

		}
		if (bean.getPwd() == null || bean.getPwd().length == 0) {
			errors.put("pwd", "密碼未輸入");
			System.out.println(errors.get("pwd"));
		}
//		if (bean.getPhoto() == null || bean.getPhoto().length == 0) {
//			errors.put("photo", "大頭貼未上傳");
//			System.out.println(errors.get("photo"));
//		}

		if (bean.getName() == null || bean.getName().length() == 0) {
			errors.put("name", "姓名未輸入");
			System.out.println(errors.get("name"));
		}
		if (bean.getNickname() == null || bean.getNickname().length() == 0) {
			errors.put("nickname", "暱稱未輸入");
			System.out.println(errors.get("nickname"));
		}
		// if(bean.getBirthday()==null || bean.getBirthday()) {
		// errors.put("birthday", "生日未選取");
		// System.out.println(errors.get("nickname"));
		// }

		// if(bean.getTel().length()>=8) {
		// errors.put("tel", "電話未輸入");
		// System.out.println(bean.getTel());
		//
		// }
		if (bean.getFavorite() == null || bean.getFavorite().length() == 0) {
			errors.put("favorite", "請勾選聚會類型");
		}

		if (errors != null || !errors.isEmpty()) {
			System.out.println("errors2=" + errors.get("account"));
			model.addAttribute("inputRrrors", errors);
			return "register.error";
		}
		return "register.success";

		// }
		//
		// 驗證資料
		// if("送出".equals(prodaction) ) {
		// if (temp1 == null || temp1.length() == 0) {
		// errors.put("id", "please enter id for " + prodaction);
		// System.out.println(temp1);
		// }
		// }
		//
		// if(errors!=null && !errors.isEmpty()) {
		// return "register.error";
		// }

		// 根據Model執行結果呼叫View

		// if("送出".equals(prodaction)) {
		// MemberBean result = memberDAOHibernate.insert(bean);
		// if(result==null) {
		// errors.put("action", "Insert fail");
		// return "register.error";
		// }
		//
		// }
	
	
	}

}
