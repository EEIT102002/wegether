package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.MemberBean;
import model.dao.implement.MemberDAOHibernate;

@Controller
public class fbcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberDAOHibernate memberDAOHibernate;

	private SimpleDateFormat simpleDateFormat;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

	}
//@ResponseBody  ResponseEntity<?>
	@RequestMapping(path = { "/login/fb" }, method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<?> insertfb(
			String account, String name, Model model, MemberBean bean,Integer id1,String name1,
			@PathVariable(name="FbId") String fbid,@PathVariable(name="FbName") String fbname,
			@PathVariable(name="Email") String fbemail
			) throws Exception {
		System.out.println("FBlogin");
		//接收資料
//		System.out.println("fbid="+fbid);
//		System.out.println("fbname="+fbname);
//		System.out.print("fbemail"+fbemail);
		
		
		
//		MemberBean checkuser = memberDAOHibernate.selectByAccount(bean.getAccount());
//		System.out.println(bean);
//		System.out.println("有無值:" + checkuser);
//		String beginDate = "2018-08-01";
//		Date t1 = null;
//
//		if (checkuser == null) {
//			System.out.println("temp:" + bean);
//			bean.setAccount(account);
//			bean.setName(name);
//			bean.setPwd("E".getBytes());
//			bean.setBirthday(new java.util.Date());
//			System.out.println("新增成功:" + bean);
//			memberDAOHibernate.insert(bean);
//			return "register.success";
//
//		} else {
//
//			System.out.println("相同帳號");
//
//		}
		
//		return "activityPage";
		return null;


	}

}
