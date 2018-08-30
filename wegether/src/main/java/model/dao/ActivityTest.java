package model.dao;

import java.io.IOException;
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

@WebServlet("/ActivityTest")
@Controller
public class ActivityTest extends HttpServlet {
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
	//	ActivityBean result = activityDAOHibernate.select(2);
		
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
	/*
		ActivityBean result = activityDAOHibernate.update(1, new java.util.Date(), "一起去爬山title", 102, "台北市", null,
				new java.util.Date(), new java.util.Date(), new java.util.Date(), "戶外活動", "一起去爬山Content", 100,
				500, 0, 3.0, 3.0,3.0, 101,"Activityform", 97, 4);
		*/
		
		//delete
	//	boolean result = activityDAOHibernate.delete(4);
		
		
		System.out.println(result);
		System.out.println("ActivityTest");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
