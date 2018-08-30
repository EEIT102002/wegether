package servlet;

import java.awt.print.Printable;
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

import org.hibernate.mapping.Component.ValueGenerationPlan;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.ServiceBean;
import model.dao.ActivityDAOHibernate;
import model.dao.ServiceDAOHibernate;


@WebServlet("/TestServiceServlet")
public class TestServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServiceDAOHibernate serviceDAOHibernate;
	private SimpleDateFormat simpleDateFormat;
	
	public void init() {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext)
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		serviceDAOHibernate = (ServiceDAOHibernate) context.getBean("serviceDAOHibernate");
		
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		以ID搜尋
		ServiceBean result=serviceDAOHibernate.select(2);
		
		
////	抓全部資料
//		List<ServiceBean> result=serviceDAOHibernate.select();
		
		
//		新增一筆資料
//		ServiceBean bean = new ServiceBean();
//		
//		bean.setMemberid(3);
//		bean.setAsktime(new java.util.Date());
//		bean.setTitle("無法參加");
//		bean.setClasstype(33);
//		bean.setContent("無法參加");
//
//		ServiceBean result = serviceDAOHibernate.insert(bean);
		
		
//		修改資料
//		ServiceBean result = serviceDAOHibernate.update(3, 3, new java.util.Date(), "更新測試", 33, "更新測試");
		
		
//		刪除資料
//		boolean result = serviceDAOHibernate.delete(3);
		
		
		
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>ServiceBean<p>");
//		result.forEach(x->out.println(x+"<br>"));////對應<ServiceBean> result
		out.print(result);
		out.close();
		
		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
