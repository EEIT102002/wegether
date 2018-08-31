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
import org.springframework.web.context.WebApplicationContext;

import model.AttendBean;
import model.dao.implement.AttendDAOHibernate;

@WebServlet("/TestAttendDAOServlet")
public class TestAttendDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private AttendDAOHibernate attendDAOHibernate;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) 
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		attendDAOHibernate = (AttendDAOHibernate) context.getBean("attendDAOHibernate");
		
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//select(id)
	//	AttendBean result = attendDAOHibernate.select(2);
		
		//select()
		List<AttendBean> result = attendDAOHibernate.select();
		
		//insert
/*		
	    AttendBean bean = new AttendBean();
	    bean.setActivityid(1);
		bean.setCreatetime(new java.util.Date());
		bean.setForm("Attenform");
		bean.setMemberid(2);
		bean.setRank1(3);
		bean.setRank2(3);
		bean.setRank3(3);
		bean.setState(0);
		AttendBean result = attendDAOHibernate.insert(bean);
		*/
		
		//update	
	/*		
	    AttendBean bean = new AttendBean();
	    bean.setActivityid(1);
		bean.setCreatetime(new java.util.Date());
		bean.setForm("Attenform");
		bean.setMemberid(3);
		bean.setRank1(4);
		bean.setRank2(5);
		bean.setRank3(5);
		bean.setState(0);
		bean.setId(4);
		AttendBean result = attendDAOHibernate.update(bean);
		*/
	
		
		//delete
//		boolean result = attendDAOHibernate.delete(4);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>TestAttendDAOServlet</p>");
		result.forEach(x->out.println(x+"<br>"));
//		out.println(result);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
