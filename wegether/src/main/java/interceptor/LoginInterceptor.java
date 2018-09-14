package interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import Service.CookieService;


public class LoginInterceptor implements HandlerInterceptor {
	@Resource(name = "tokenMap")
	private Map<String, Integer> tokenMap;
	@Autowired
	private CookieService cookieService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token = cookieService.getValue(request, "token");
		if(token != null) {
			Integer id = tokenMap.get(token);
			request.setAttribute("memberid", id);
		}else {
			request.setAttribute("memberid", null);
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
