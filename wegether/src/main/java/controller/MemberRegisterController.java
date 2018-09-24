package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.swing.JOptionPane;

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
import model.dao.PictureDAO;
import model.dao.implement.MemberDAOHibernate;
import pictureconvert.PictureConvert;

@Controller
public class MemberRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberDAOHibernate memberDAOHibernate;
	
	@Autowired
	private PictureDAO pictureDAO;

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
			@RequestParam(value = "pwd2", required = false) String pwdre,
			@RequestParam(value = "photo2", required = false) MultipartFile file) throws IOException {

		// flag= 0:未登入 1:登入
		// Integer flag = 0;
		 if (memberid != null) {
//		JOptionPane.showMessageDialog(null, "已登入過，將轉至首頁請再次登入", "註冊失敗", JOptionPane.INFORMATION_MESSAGE );

			 
		 return "register.success";
		 }
		System.out.println(memberid);

		// 圖片轉換
		byte[] pic = null;
		if (!file.isEmpty()) { // 圖片判定
			pic = file.getBytes();
			bean.setPhoto(pic);
		} else {
//			File defultPic = new File("/wegether/images/04.jpg");
			
		
//			System.out.println(defultPic);
					
			try {
				bean.setPhoto(memberDAOHibernate.select(1).getPhoto());
				// pic = ((MultipartFile) defultPic).getBytes();
//				pic = PictureConvert.converFileToByte(defultPic);
//				bean.setPhoto(pic);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

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
		// ^[\u4e00-\u9fa5_0-9]+$
		Matcher rematcheraddr =   patternaddr.matcher(addr);
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
		if (bean.getBirthday() == null) {
			errors.put("birthday", "請輸入日期");
		}

		// 地址錯誤訊息
		if (!checkaddr && bean.getAddr().length() > 0) {
			errors.put("addr", "請輸入中文");
		}
		// 職業錯誤訊息
		if (!checkjob && bean.getJob().length() > 0 && bean.getJob() != null) {
			errors.put("job", "請輸入中文");
		}
		// 轉進位 暱稱 介紹 最愛
		String nickname = StringEscapeUtils.unescapeHtml(bean.getNickname());
		String content = StringEscapeUtils.unescapeHtml(bean.getContent());
		String favorite = StringEscapeUtils.unescapeHtml(bean.getFavorite());

		if (errors != null && !errors.isEmpty()) {
			model.addAttribute("inputRrrors", errors);
			System.out.println("所得到的bean:" + bean);
			System.out.println("erroers" + errors);
//			 JOptionPane.showMessageDialog(null, "請再次註冊", "註冊失敗", JOptionPane.PLAIN_MESSAGE );
//
			return "register.error";
		} else {

			System.out.println(bean);
			bean.setName(name);
			bean.setNickname(nickname);
			bean.setJob(job);
			bean.setAddr(addr);
			bean.setFavorite(favorite);
			bean.setContent(content);

			memberDAOHibernate.insert(bean);
//			JOptionPane.showMessageDialog(null, "將轉至首頁，請再次登入", "註冊成功", JOptionPane.INFORMATION_MESSAGE );

			// bean.getId()
			// flag=1;

			return "register.success";
		}

	}

}
