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
import model.PictureBean;
import model.dao.implement.ActivityDAOHibernate;

@WebServlet("/TestActivityDAOServlet")
public class TestActivityDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private ActivityDAOHibernate activityDAOHibernate;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) 
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		activityDAOHibernate = (ActivityDAOHibernate) context.getBean("activityDAOHibernate");
		
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//select(id)
		//ActivityBean result = activityDAOHibernate.select(2);
		
		//select()
		List<ActivityBean> result = activityDAOHibernate.select();
		
		//insert
/*		
	    ActivityBean bean = new ActivityBean();
		bean.setActbegin(new java.util.Date());
		bean.setActend(new java.util.Date());
		bean.setAddr("象山");
		bean.setCity(102);
		bean.setClasstype("戶外活動");
		bean.setClick(97);
		bean.setContent("一起去爬山Content");
		bean.setCreatetime(new java.util.Date());
		bean.setDateline(new java.util.Date());
		bean.setFeed(500);
		bean.setForm("Activityform");
		bean.setHostid(1);
		bean.setJudges(101);
		bean.setNumberlimit(100);
		bean.setPicture(null);
		bean.setRank1(3.0);
		bean.setRank2(3.0);
		bean.setRank3(3.0);
		bean.setState(1);
		bean.setTitle("一起去爬山title7");
		ActivityBean result = activityDAOHibernate.insert(bean);
	*/
		
		//update				
//	    ActivityBean bean = new ActivityBean();
//		bean.setActbegin(new java.util.Date());
//		bean.setActend(new java.util.Date());
//		bean.setAddr("大屯山");
//		bean.setCity(102);
//		bean.setClasstype("戶外活動");
//		bean.setClick(97);
//		bean.setContent("一起去爬山Content");
//		bean.setCreatetime(new java.util.Date());
//		bean.setDateline(new java.util.Date());
//		bean.setFeed(500);
//		bean.setForm("Activityform");
//		bean.setHostid(1);
//		bean.setJudges(101);
//		bean.setNumberlimit(100);
//		bean.setPicture(null);
//		bean.setRank1(3.0);
//		bean.setRank2(3.0);
//		bean.setRank3(3.0);
//		bean.setState(1);
//		bean.setTitle("一起去爬山title7");
//		bean.setId(6);
//		ActivityBean result = activityDAOHibernate.update(bean);
		
		
		//delete
	//	boolean result = activityDAOHibernate.delete(7);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>ActivityTest</p>");
		result.forEach(x->{			
			out.println(x+"<br>");
			out.println(x.getPictureBean()+"<br>");
		});
	//	out.println(result);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
