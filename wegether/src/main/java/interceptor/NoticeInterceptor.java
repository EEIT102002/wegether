package interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import Service.CookieService;
import Service.bean.LoginBean;
import model.dao.NoticeDAO;
import querylanguage.QueryBean;
import querylanguage.Select;

public class NoticeInterceptor implements HandlerInterceptor {
	@Autowired
	@Qualifier("tokenMap")
	private Map<String, Integer> tokenMap;
	@Autowired
	@Qualifier("loginMap")
	private Map<Integer, LoginBean> loginMap;
	@Autowired
	private CookieService cookieService;
	@Autowired
	private QueryBean queryBean;
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		queryBean.getSession().flush();
		Integer id = (Integer) modelAndView.getModel().get("id");
		String ntype = (String) modelAndView.getModel().get("ntype"); 
		switch(id) {
			case 1:
			case 2:
				//friend
				break;
			case 3:
				//invite
				break;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				//attend
				break;
			case 10:
			case 15:
				//article
				break;
			case 11:
			case 12:
			case 13:
			case 14:
				//activity
				break;
		}
		
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
