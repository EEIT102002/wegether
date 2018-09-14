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
			@RequestParam(value = "account", required = false) String account1) throws IOException {

		// 圖片轉換
		// byte[] pic = null;
		// if (!file.isEmpty()) { // 圖片判定
		// pic = file.getBytes();
		// bean.setPhoto(pic);
		// } else {
		// File defultPic = new File("C:/temp/2.jpg");
		// try {
		// // pic = ((MultipartFile) defultPic).getBytes();
		// pic = PictureConvert.converFileToByte(defultPic);
		// bean.setPhoto(pic);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

		// // 帳號信箱驗證
		String email = bean.getAccount();
		Pattern patternemail = Pattern.compile("^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$");
		Matcher matcheremail = patternemail.matcher(email);
		boolean checkemail = matcheremail.matches();
		if (!checkemail) {
			System.out.println("信箱格式不正確");
		} else {
			System.out.println("信箱格式正確");

		}

		// 手機格式驗證
		String cellphone = bean.getTel();
		Pattern patterncell = Pattern.compile("[0-9]{10}");
		Matcher matchercell = patterncell.matcher(cellphone);
		boolean checkcel = matchercell.matches();
		if (!checkcel) {
			System.out.println("手機號碼格式不正確");
		} else {
			System.out.println("手機號碼格式正確");
		}

		// 密碼格式驗證 密碼長度8~16碼、不能有特殊符號、必須要有英文及數字

		String code = new String(bean.getPwd());
		Pattern patterncode = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");

		Matcher matchercode = patterncode.matcher(code);
		boolean checkcode = matchercode.matches();
		System.out.println(checkcode);
		if (!checkcode) {
			System.out.println("密碼錯誤");
		} else {
			System.out.println("密碼正確");
		}

		Map<String, String> errors = new HashMap<>();

		

		// 帳號錯誤訊息
		if (bean.getAccount() == null || bean.getAccount().length() == 0) {
			errors.put("account", "帳號未輸入");
		} else if (!checkemail) {
			errors.put("account", "帳號格式錯誤");
		}

		// 密碼錯誤訊息
		if (bean.getPwd() == null || bean.getPwd().length == 0) {
			errors.put("pwd", "密碼未輸入");
		} else if (!checkcode) {
			errors.put("pwd", "密碼格式錯誤");
		}

		// 電話錯誤訊息
		if (bean.getTel() == null || bean.getTel().length() == 0) {
			errors.put("tel", "請輸入手機號碼");
		} else if (!checkcel) {
			errors.put("tel", "電話格式錯誤");
		}

		// 姓名錯誤訊息
		if (bean.getName() == null || bean.getName().length() == 0) {
			errors.put("name", "姓名未輸入");
		}

		if (errors != null && !errors.isEmpty()) {
			model.addAttribute("inputRrrors", errors);
			System.out.println("a" + bean);
			System.out.println("erroers" + errors);

			return "register.error";
		} else {
			System.out.println(bean);

			memberDAOHibernate.insert(bean);

			return "register.success";
		}

	}

}
