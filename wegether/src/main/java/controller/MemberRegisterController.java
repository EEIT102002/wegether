package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
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
			@RequestAttribute(name = "memberid", required = false) Integer memberid,
			@RequestParam(value = "account", required = false) String account1,
			@RequestParam(value = "pwd2", required = false) String pwdre) throws IOException {

		// flag= 0:未登入 1:登入
//		 Integer flag = 0;
//		 if (memberid != null) {
//			 
//		 return "register.success";
//		 }

		System.out.println(memberid);
		// 帳號信箱驗證
		String email = bean.getAccount();
		Pattern patternemail = Pattern.compile("^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$");
		Matcher matcheremail = patternemail.matcher(email);
		boolean checkemail = matcheremail.matches();

		// 帳號重複者驗證
		MemberBean checkuser = memberDAOHibernate.selectByAccount(bean.getAccount());

		// 手機格式驗證
		String cellphone = bean.getTel();
		Pattern patterncell = Pattern.compile("[0-9]{10}");
		Matcher matchercell = patterncell.matcher(cellphone);
		boolean checkcel = matchercell.matches();

		// 密碼格式驗證 密碼長度8~16碼、必須有[!@#$%^&*]、必須要有英文及數字
		String code = new String(bean.getPwd());
		Pattern patterncode = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[\\W]).{8,16}$");
		Matcher matchercode = patterncode.matcher(code);
		boolean checkcode = matchercode.matches();
		// System.out.println(code);

		// 確認密碼 密碼長度8~16碼、必須有[!@#$%^&*]、必須要有英文及數字
		String recode = new String(pwdre);
		Pattern patterncodere = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[\\W]).{8,16}$");
		Matcher rematchercode = patterncodere.matcher(recode);
		boolean recheckcode = rematchercode.matches();
		// System.out.println(recheckcode);
		// System.out.println(recode);

		// 姓名驗證 只能中文2~7
		String name = StringEscapeUtils.unescapeHtml(bean.getName());
		Pattern patternname = Pattern.compile("[\u4E00-\u9FBF]{2,7}+");
		Matcher rematchername = patternname.matcher(name);
		boolean checkname = rematchername.matches();
		// System.out.println(name);
		// System.out.println(checkname);

		// 暱稱 預設姓名
		String checknickname = bean.getNickname();
		if (bean.getNickname() == null || bean.getNickname().length() == 0) {
			checknickname = bean.getName();
			bean.setNickname(checknickname);
		}
		// 地址驗證 只能輸入中文
		String addr = StringEscapeUtils.unescapeHtml(bean.getAddr());
		Pattern patternaddr = Pattern.compile("^[\u4E00-\u9FBF_0-9]+$");
//		^[\u4e00-\u9fa5_0-9]+$
		Matcher rematcheraddr = patternaddr.matcher(addr);
		boolean checkaddr = rematcheraddr.matches();
		System.out.println(addr);
		System.out.println(checkaddr);
		
		// 地址驗證 只能輸入中文
		String job = StringEscapeUtils.unescapeHtml(bean.getJob());
		Pattern patternjob = Pattern.compile("[\u4E00-\u9FBF]+");
		Matcher rematcherjob = patternjob.matcher(job);
		boolean checkjob = rematcherjob.matches();
		System.out.println(job);
		System.out.println(checkjob);

		Map<String, String> errors = new HashMap<>();

		// 帳號錯誤訊息
		if (bean.getAccount() == null || bean.getAccount().length() == 0) {
			errors.put("account", "帳號未輸入");
		} else if (!checkemail) {
			errors.put("account", "帳號格式錯誤");
		} else if (checkuser != null) {
			errors.put("account", "帳號重複");
		}
		// 密碼錯誤訊息
		if (bean.getPwd() == null || bean.getPwd().length == 0) {
			errors.put("pwd", "密碼未輸入");
		} else if (!checkcode) {
			errors.put("pwd", "密碼格式錯誤");
		}

		// 確認密碼 錯誤訊息
		if (pwdre == null || pwdre.length() == 0) {
			errors.put("pwd2", "密碼未輸入");
		} else if (!recheckcode) {
			errors.put("pwd2", "請在確認密碼");
		} else if (code.equals(recode) == false) {
			errors.put("pwd2", "請在確認密碼");
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

		} else if (!checkname) {
			errors.put("name", "姓名格式錯誤");
		}
		
		// 日期錯誤訊息
		if (bean.getBirthday()==null) {
			errors.put("birthday", "請輸入日期");
		} 

		// 地址錯誤訊息
		if (!checkaddr && bean.getAddr().length()>0) {
			errors.put("addr", "請輸入中文");
		} 
		// 職業錯誤訊息
				if (!checkjob && bean.getJob().length()>0&&bean.getJob()!=null) {
					errors.put("job", "請輸入中文");
				} 
		
		if (errors != null && !errors.isEmpty()) {
			model.addAttribute("inputRrrors", errors);
			System.out.println("所得到的bean:" + bean);
			System.out.println("erroers" + errors);

			return "register.error";
		} else {
			System.out.println(bean);
			memberDAOHibernate.insert(bean);
//			bean.getId()
			// flag=1;

			return "register.success";
		}

	}
	
}
