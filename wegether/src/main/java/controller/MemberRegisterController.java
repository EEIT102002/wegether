package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import pictureconvert.PictureConvert;

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

	@SuppressWarnings("unused")
	@RequestMapping(path = { "/register.controller" }, method = RequestMethod.POST)
	public String selectMethod(Model model, MemberBean bean,
			@RequestParam(value = "photo1", required = false) MultipartFile file,
			@RequestParam(value = "account", required = false) String account1) throws IOException {

		// 圖片轉換
		byte[] pic = null;
		if (!file.isEmpty()) { // 圖片判定
			pic = file.getBytes();
			bean.setPhoto(pic);
		} else {
			File defultPic = new File("C:/temp/2.jpg");
			try {
				// pic = ((MultipartFile) defultPic).getBytes();
				pic = PictureConvert.converFileToByte(defultPic);
				bean.setPhoto(pic);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		Map<String, String> errors = new HashMap<>();
		//
		// if (bean.getAccount() == null || bean.getAccount().length() == 0) {
		// errors.put("account", "帳號未輸入");
		
		// }
		// if (bean.getPwd() == null || bean.getPwd().length == 0) {
		// errors.put("pwd", "密碼未輸入");
		// System.out.println(errors.get("pwd"));
		// }

		//
		// if (bean.getName() == null || bean.getName().length() == 0) {
		// errors.put("name", "姓名未輸入");
		// System.out.println(errors.get("name"));
		// }
	
		// // if(bean.getBirthday()==null || bean.getBirthday()) {
		// // errors.put("birthday", "生日未選取");
		// // System.out.println(errors.get("nickname"));
		// // }
		//
		// 電話驗證格式

		// String cellphone = "0919-123-456";
		//
		// cellphone = cellphone.trim(); //我會先把空白濾掉，這行可寫可不寫...
		//
		String checkphone="[0-9]{4}-[0-9]{3}-[0-9]{3}";
		
//		 Matcher matcher = pattern.matcher(bean.getTel());
//		 boolean CheckCellPhone = matcher.matches();
//		if(CheckCellPhone =true){
//		 System.out.println("手機號碼格式不正確");
//		 System.out.println("CheckCellPhone");
//		 errors.put("tel", "手機號碼格式不正確");
//		
//		 }
		if (bean.getTel() == null || bean.getTel().length() == 0||bean.getTel()!=checkphone) {
			errors.put("tel", "電話格式錯誤");
			System.out.println(bean.getTel());
			
		}
		
		
		if (errors!=null && !errors.isEmpty()) {
			model.addAttribute("inputRrrors", errors);
			System.out.println("a" + bean);
			System.out.println("erroers"+errors);
			return "register.error";
		} else {
			System.out.println(bean);
			memberDAOHibernate.insert(bean);
			return "register.success";
		}
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
