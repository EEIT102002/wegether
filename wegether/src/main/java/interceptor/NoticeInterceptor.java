package interceptor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import Service.CookieService;
import Service.NoticeService;
import Service.bean.LoginBean;
import model.NoticeBean;
import model.dao.NoticeDAO;
import querylanguage.QueryBean;
import querylanguage.Select;

public class NoticeInterceptor implements HandlerInterceptor {
	@Resource(name = "loginMap")
	private Map<Integer, LoginBean> loginMap;
	@Autowired
	private QueryBean queryBean;
	@Autowired
	private NoticeService noticeService;
	private TextMessage text = new TextMessage("1");

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (request.getAttribute("id") == null || request.getAttribute("ntype") == null) {
			HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
			return;
		}
		queryBean.getSession().flush();
		Integer id = (Integer) request.getAttribute("id");
		Integer ntype = (Integer) request.getAttribute("ntype");
		List<NoticeBean> beans = noticeService.getNotice(id, ntype);

		if (beans != null) {
			beans.forEach(bean -> {
				LoginBean loginBean = loginMap.get(bean.getMemberid());
				if (loginBean != null) {
					List<WebSocketSession> sessions = loginMap.get(bean.getMemberid()).getSessions();
					synchronized (sessions) {
						sessions.forEach(x -> {
							try {
								x.sendMessage(text);
							} catch (IOException e) {
								e.printStackTrace();
							}
						});
					}
				}
			});
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
