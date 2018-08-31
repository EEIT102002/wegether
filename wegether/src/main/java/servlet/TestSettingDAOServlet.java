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
import model.SettingBean;
import model.dao.implement.SettingDAOHibernate;

@WebServlet("/SettingTest")
public class TestSettingDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SettingDAOHibernate settingDAOHibernate;
	private SettingBean settingBean;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) 
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		settingDAOHibernate = (SettingDAOHibernate) context.getBean("settingDAOHibernate");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		select(id)
		SettingBean result = settingDAOHibernate.select(2);
		
		//select()
//		List<ActivityBean> result = activityDAOHibernate.select();
		
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
		bean.setId(4);
		bean.setJudges(101);
		bean.setNumberlimit(100);
		bean.setPicture(null);
		bean.setRank1(3.0);
		bean.setRank2(3.0);
		bean.setRank3(3.0);
		bean.setState(1);
		bean.setTitle("一起去爬山title");
		ActivityBean result = activityDAOHibernate.insert(bean);
		*/
		
		//update
		SettingBean currentbean = settingDAOHibernate.select(2);
		currentbean.setTel(0);
		currentbean.setAddr(0);
		currentbean.setJob(1);
		
		SettingBean result1 = settingDAOHibernate.update(currentbean);
		
		//delete
	//	boolean result = activityDAOHibernate.delete(4);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>SettingSelectTest</p>");
		out.println(result);
//		result.forEach(x->out.println(x+"<br>"));
		out.println("<p>SettingUpdateTest</p>");
		out.println(result1);
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
