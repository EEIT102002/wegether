package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import model.MemberBean;
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
		
		//selectId(id)
//		ActivityBean result = activityDAOHibernate.selectId(2);
		
		//selectAll()
//		List<ActivityBean> result = activityDAOHibernate.selectAll();
		
		//selectOfIndex
		String begin = "2018-08-01";
		String end = "2018-09-10";
		Date t1 = null, t2 = null;;
		  try {
			t1 = simpleDateFormat.parse(begin);
			t2 = simpleDateFormat.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  
		List<ActivityBean> result = activityDAOHibernate.selectOfIndex(112,"山",t1,t2);
		
		
		//insert
//	    ActivityBean bean = new ActivityBean();
//		bean.setActbegin(new java.util.Date());
//		bean.setActend(new java.util.Date());
//		bean.setAddr("象山");
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
//		ActivityBean result = activityDAOHibernate.insert(bean);

		
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
//		bean.setFeed(1000);
//		bean.setForm("Activityform");
//		bean.setHostid(3);
//		bean.setJudges(101);
//		bean.setNumberlimit(100);
//		bean.setPicture(null);
//		bean.setRank1(3.0);
//		bean.setRank2(3.0);
//		bean.setRank3(3.0);
//		bean.setState(1);
//		bean.setTitle("一起去爬山title7");
//		bean.setId(3);
//		ActivityBean result = activityDAOHibernate.update(bean);
		
		
		//delete
	//	boolean result = activityDAOHibernate.delete(7);
		
		
		//selectByState
//		List<ActivityBean> result = activityDAOHibernate.selectByState(0);
		
		//selectByAddress
//		List<ActivityBean> result = activityDAOHibernate.selectByAddress("仁愛");
		
		//selectByTime
//		String begin = "2018-09-01";
//		String end = "2018-09-10";
//		Date t1 = null, t2 = null;;
//		  try {
//			t1 = simpleDateFormat.parse(begin);
//			t2 = simpleDateFormat.parse(end);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	
//		List<ActivityBean> result = activityDAOHibernate.selectByTime(t1,t2);
		
		//selectByActbegin
//		String input = "2018-09-09";
//		Date t = null;
//		try{
//			t = simpleDateFormat.parse(input);		
//		  }catch(ParseException e){
//			  e.printStackTrace();
//		  }
//		List<ActivityBean> result = activityDAOHibernate.selectByActbegin(t);
		
		//selectByCity
//		List<ActivityBean> result = activityDAOHibernate.selectByCity(112);
		
		//selectByTitle
	//	List<ActivityBean> result = activityDAOHibernate.selectByTitle("大賞楓");
		
		//selectByClasstype
//		List<ActivityBean> result = activityDAOHibernate.selectByClasstype("戶外");
		
		//selectByHost
//		List<ActivityBean> result = activityDAOHibernate.selectByNickname("胡");
		
		//selectByFeed
//		List<ActivityBean> result = activityDAOHibernate.selectByFees(1000);
		
		//selectByClick
//		List<ActivityBean> result = activityDAOHibernate.selectByClick(150);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>ActivityTest</p>");

		result.forEach(temp->{			
				out.println(temp+"<br><br>");			
				out.println("===============<br><br>");
		});
		
//				out.println(result);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
