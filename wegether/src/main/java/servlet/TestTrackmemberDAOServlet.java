package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import model.ArticleBean;
import model.MemberBean;
import model.TrackmemberBean;
import model.dao.TrackmemberDAO;
import model.dao.implement.ActivityDAOHibernate;
import model.dao.implement.ArticleDaoHibernate;
import model.dao.implement.MemberDaoHibernate;
import model.dao.implement.TrackmemberDAOHibernate;

@WebServlet("/TrackemberTest")
public class TestTrackmemberDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrackmemberDAO dao;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		dao = (TrackmemberDAO) context.getBean("trackmemberDAOHibernate");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// select(id)
		 List<TrackmemberBean> result = dao.selectByFan(2);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>Trackmember Test</p>");
//		 result.forEach(x->out.println(x+"<br>"));
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
