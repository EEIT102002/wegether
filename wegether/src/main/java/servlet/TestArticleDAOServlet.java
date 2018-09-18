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
import model.ArticleBean;
import model.dao.implement.ActivityDAOHibernate;
import model.dao.implement.ArticleDAOHibernate;

@WebServlet("/AriticleTest")
public class TestArticleDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private ArticleDAOHibernate articleDaoHibernate;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		articleDaoHibernate = (ArticleDAOHibernate) context.getBean("articleDAOHibernate");

		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// select(id)
		// ArticleBean result = articleDaoHibernate.select(1);

		// select()
		// List<ActivityBean> result = activityDAOHibernate.select();

		// insert
		ArticleBean bean = new ArticleBean();
		bean.setMemberid(6);
		bean.setActivityid(1);
		bean.setContent("奧萬大與您相約在每一個季節。"
				);//		bean.setCreatetime(new java.util.Date());
		ArticleBean result = articleDaoHibernate.insert(bean);

		// update
		// boolean result = articleDaoHibernate.update(1, "去宜蘭烤肉");

		// delete
//		 boolean result = articleDaoHibernate.delete(1);

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
