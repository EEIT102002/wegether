package Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
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
	
	public String getValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if(cookie != null) {
			return cookie.getValue();
		}else {
			return null;
		}
		
	}
	
	public Integer getId(HttpServletRequest request) {
		Object rs = request .getAttribute("memberid");
		if(rs != null && rs.getClass() == Integer.class) {
			return  (Integer ) rs;
		}
		return null;
	}
}
