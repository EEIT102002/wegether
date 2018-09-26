package controller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.CookieService;
import Service.MemberService;
import Service.bean.LoginBean;
import Service.bean.UniqueToken;
import model.MemberBean;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	
	@Resource(name ="uniqueToken")
	private UniqueToken uniqueToken;
	
	@Resource(name = "noticeToken")
	private UniqueToken noticeToken;
	
	@Resource(name = "tokenMap")
	private Map<String, Integer> tokenMap;
	
	@Resource(name = "noticeTokenMap")
	private Map<String, Integer> noticeTokenMap;
	
	@Autowired
	private Map<Integer, LoginBean> loginMap;
	@Autowired
	private CookieService cookieService;
	
	@RequestMapping(value = "/login.do", produces = "application/json")
	public @ResponseBody Map<String, Object> method(String account, String pwd, Model model,
			HttpServletResponse response) {
		account.trim();
		pwd.trim();
		Map<String, String> errors = new HashMap<String, String>();
		Map<String, Object> result = new HashMap<>();
		result.put("errors", errors);

		if (account == null || account.length() == 0) {
			errors.put("id", "ID is require");
		}
		if (pwd == null || pwd.length() == 0) {
			errors.put("pwd", "Password is requires");
		}
		if (errors.size() > 0) {
			result.put("state", false);
		} else {
			MemberBean memberBean = memberService.login(account, pwd);
			if (memberBean != null) {
				result.put("state", true);
				String token = uniqueToken.randomToken(memberBean.getId());
				
				if(loginMap.get(memberBean.getId()) == null) {
					loginMap.put(memberBean.getId(), new LoginBean(memberBean.getId()));
				}
				loginMap.get(memberBean.getId()).getTokens().add(token);
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(60*60*24 );
				cookie.setPath("/");
				response.addCookie(cookie);
				result.put("ntoken", noticeToken.randomToken(memberBean.getId()));
			} else {
				result.put("state", false);
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/logout.do", produces = "application/json")
	public @ResponseBody Map<String, Object> checkLogout(
			HttpServletRequest request,@RequestAttribute("memberid") Integer id) {
		Map<String, Object> result = new HashMap<>();
		if(id != null) {
			String token = cookieService.getValue(request, "token");
			tokenMap.remove(token);
			loginMap.get(id).getTokens().remove(token);
			result.put("state", true);
		}else {
			result.put("state", false);
		}
		return result;
	}
	@RequestMapping(value = "/login.check", produces = "application/json")
	public @ResponseBody Map<String, Object> checkLogin(@RequestAttribute(name = "memberid", required=false) Integer id) {
		Map<String, Object> result = new HashMap<>();
		if(id != null) {			
			result.put("state", true);
			result.put("ntoken", noticeToken.randomToken(id));
		}else {
			result.put("state", false);
		}
		return result;
	}
	
	
	private Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }


}
