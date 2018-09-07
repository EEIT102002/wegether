package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Service.MemberService;
import model.MemberBean;
import model.MemberInfoBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;
import model.dao.TrackmemberDAO;

@Controller
public class MemberSettingController {
	@Autowired
	MemberInfoDAO memberInfoDAO;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	SettingDAO settingDAO;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	FriendDAO friendDAO;
	@Autowired
	TrackmemberDAO trackmemberDAO;
	@Autowired
	BlacklistDAO blacklistDAO;
	@Autowired
	MemberService memberServic;
	
	@RequestMapping( path= {"/member/Info/setting"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> setInfo(MemberBean bean, HttpServletRequest request){
		bean.setId((Integer) request.getAttribute("memberid"));
		MemberInfoBean result = memberServic.setMemberInfo(bean);

		if(result!=null) {
			return new ResponseEntity<MemberInfoBean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
