package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import model.ActivityBean;
import model.NoticeBean;
import model.dao.ActivityDAOHibernate;
import model.dao.NoticeDAO;
import model.dao.NoticeDAOHibernate;

@WebServlet("/NoticeTest")
public class TestNoticeDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private ActivityDAOHibernate activityDAOHibernate;
	private NoticeDAO noticeDAO;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) 
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		activityDAOHibernate = (ActivityDAOHibernate) context.getBean("activityDAOHibernate");
		noticeDAO = (NoticeDAO)context.getBean("noticeDAOHibernate");
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<NoticeBean> result = noticeDAO.selectByMemberId(2,Integer.parseInt(request.getParameter("id")));
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>NoticeBean</p>");
		result.forEach(x->out.println(x+"<br>"));
//		out.print(result);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
