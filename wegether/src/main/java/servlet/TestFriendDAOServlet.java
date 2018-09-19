package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.FriendBean;
import model.dao.implement.FriendDAOHibernate;

@WebServlet("/FriendTest")
public class TestFriendDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private FriendDAOHibernate friendDaoHibernate;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		friendDaoHibernate = (FriendDAOHibernate) context.getBean("friendDaoHibernate");

		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// select(id)
		// ArticleBean result = friendDaoHibernate.select(1);

		// select()
//		 List<FriendBean> result = friendDaoHibernate.select(2);

		// insert
		 FriendBean bean = new FriendBean();
//
		 bean.setMemberid(Integer.parseInt(request.getParameter("1")));
		 bean.setMemberidf(Integer.parseInt(request.getParameter("3")));
		 bean.setState(1);

//		 bean.setMemberid(2);
//		 bean.setMemberidf(4);
//		 bean.setState(0);

		 FriendBean result = friendDaoHibernate.insert(bean);
		
		
		 
		// update
//		FriendBean bean = new FriendBean();
//		bean.setMemberid(2);
//		bean.setMemberidf(3);
//		bean.setState(1);
//		boolean result = friendDaoHibernate.updateState(bean);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>FriendTest</p>");
		out.println(result);
		// result.forEach(x->out.println(x+"<br>"));
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
