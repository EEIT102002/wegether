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
import model.dao.implement.ActivityDAOHibernate;
import model.dao.implement.ArticleDAOHibernate;
import model.dao.implement.MemberDAOHibernate;

@WebServlet("/MemberTest")
public class TestMemberDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private MemberDAOHibernate memberDaoHibernate;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		memberDaoHibernate = (MemberDAOHibernate) context.getBean("memberDaoHibernate");

		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// select(id)
		MemberBean result = memberDaoHibernate.select(1);

		// select()
		// List<ActivityBean> result = memberDaoHibernate.select();

		// insert
		// MemberBean bean = new MemberBean();
		// bean.setAccount("ewf@yahoo");
		// bean.setPwd("E".getBytes());
		// bean.setName("安妮");
		// try {
		// bean.setBirthday(simpleDateFormat.parse("1999-01-01"));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// MemberBean result = memberDaoHibernate.insert(bean);

		// update
		// MemberBean memberBean = memberDaoHibernate.select(4);
		// memberBean.setAddr("景安");
		// memberBean.setNickname("longlong");
		// boolean result = memberDaoHibernate.update(memberBean);

		// delete
		// boolean result = memberDaoHibernate.delete(1);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>ActivityTest</p>");
		out.println(result);
		// result.forEach(x->out.println(x+"<br>"));
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
