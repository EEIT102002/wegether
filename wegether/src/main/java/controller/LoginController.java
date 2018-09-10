package controller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

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
	@Autowired
	private UniqueToken uniqueToken;
	@Autowired
	@Qualifier("tokenMap")
	private Map<String, Integer> tokenMap;
	@Autowired
	private Map<Integer, LoginBean> loginMap;
	@Autowired
	private CookieService cookieService;
	
	@RequestMapping(value = "/login.do", produces = "application/json")
	public @ResponseBody Map<String, Object> method(String account, String pwd, Model model,
			HttpServletResponse response, HttpServletRequest request) {
		System.out.println(request.getAttribute("memberid"));
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
				String token = uniqueToken.randomToken();
				tokenMap.put(token, memberBean.getId());
				if(loginMap.get(memberBean.getId()) == null) {
					loginMap.put(memberBean.getId(), new LoginBean(memberBean.getId()));
				}
				loginMap.get(memberBean.getId()).getTokens().add(token);
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(60 * 60);
				cookie.setPath("/");
				response.addCookie(cookie);

			} else {
				result.put("state", false);
			}
		}
		result.forEach((k, v) -> System.out.println(k + ":" + v));
		return result;
	}
	
	@RequestMapping(value = "/logout.do", produces = "application/json")
	public @ResponseBody Map<String, Object> checkLogout(Model model,
			HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		Integer id= cookieService.getId(request);
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
	public @ResponseBody Map<String, Object> checkLogin(Model model,
			HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		if(request.getAttribute("memberid") != null) {
			result.put("state", true);
		}else {
			result.put("state", false);
		}
		return result;
	}
	
	
	public Cookie getCookie(HttpServletRequest request, String name) {
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
