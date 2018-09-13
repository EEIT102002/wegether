package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import Service.MemberService;
import Service.SettingService;
import model.MemberBean;
import model.MemberInfoBean;
import model.SettingBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;
import model.dao.TrackmemberDAO;

@Controller
public class MemberSettingController {
	@Autowired
	private MemberInfoDAO memberInfoDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private SettingDAO settingDAO;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private TrackmemberDAO trackmemberDAO;
	@Autowired
	private BlacklistDAO blacklistDAO;
	@Autowired
	private MemberService memberServic;
	@Autowired
	private SettingService settingService;
	
	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));	
		webDataBinder.registerCustomEditor(Double.class,
				new CustomNumberEditor(Double.class, true));
		webDataBinder.registerCustomEditor(Integer.class,
				new CustomNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(byte[].class, 
				new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping( path= {"/member/Info/setting"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> setInfo(
			MemberBean bean
			, @RequestAttribute("memberid") Integer id
			,BindingResult bindingResult){
		if(id == null || bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		bean.setId(id);
		MemberInfoBean result = memberServic.setMemberInfo(bean);

		if(result!=null) {
			return new ResponseEntity<MemberInfoBean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping( path= {"/member/Info/account"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> setInfo(String oldpwd, String pwd, String pwdrepeat, @RequestAttribute("memberid") Integer id){
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Map<String, String> errors = new HashMap<String, String>();
		Map<String, Object> result = new HashMap<>();
		result.put("errors", errors);
		
	
		if(!pwd.equals(pwdrepeat)) {
			errors.put("notrepeat", "重複密碼不相同");
		}
		
		if(errors.size() >0) {
			result.put("state", false);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		
		
		Integer changed = memberServic.changePassword(id, oldpwd, pwd);

		if(changed == 1) {
			result.put("state", true);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			result.put("state", false);
			if(changed == 2) {
				errors.put("repeat", "與舊密碼重複");
			}
			if(changed == 0) {
				errors.put("fail", "密碼變更失敗");		
			}
			if(changed == 3) {
				errors.put("repeat", "違反密碼規則");
			}
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
	}
	
	@RequestMapping( path= {"/member/Setting/setting"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> setSetting(SettingBean bean, 
			@RequestAttribute("memberid") Integer id, BindingResult bindingResult){
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Map<String, Object> result = new HashMap<>();
		System.out.println(bean);
		
		if(bindingResult!=null && bindingResult.hasFieldErrors()) {
			result.put("state", false);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		
		bean.setMemberid(id);
		
		
		SettingBean settingbean = settingService.setSetting(bean);
		if(settingbean != null) {
			result.put("state", true);
			result.put("setting", settingbean);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			result.put("state", false);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
	}
	
	@RequestMapping( path= {"/member/photo"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> updatePhoto(
			MemberBean bean
			,@RequestAttribute("memberid") Integer id 
			) throws IOException{
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		bean.setId(id);
		MemberInfoBean check = memberServic.setMemberInfo(bean);
		
		if(check!=null) {
			Map<String, Object> result = new HashMap<>();
			result.put("photoSrc", check.getPhotoSrc());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
