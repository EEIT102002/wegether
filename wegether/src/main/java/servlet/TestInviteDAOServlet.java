package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import model.ActivityBean;
import model.InviteBean;
import model.dao.implement.InviteDAOHibernate;

@WebServlet("/TestInviteDAOServlet")
@Controller
public class TestInviteDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private InviteDAOHibernate inviteDAOHibernate;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		inviteDAOHibernate = (InviteDAOHibernate) context.getBean("inviteDAOHibernate");

		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// select(id)
		 InviteBean result = inviteDAOHibernate.select(2);

		// select()
		// List<InviteBean> result = inviteDAOHibernate.select();

		// insert
		///*
//		InviteBean bean = new InviteBean();
//		
//		bean.setMemberid(2);
//		bean.setInvitedid(2);
//		bean.setActivityid(2);
//
//		InviteBean result = inviteDAOHibernate.insert(bean);
//*/
		// update

		// InviteBean result = inviteDAOHibernate.update(2,1,2,2);
		

		// delete
		// boolean result = inviteDAOHibernate.delete(1);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		// result.forEach(x->out.println(x+"<br>"));
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
