package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
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

import com.ctc.wstx.util.StringUtil;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;

import antlr.StringUtils;
import model.ActivityBean;
import model.PictureBean;
import model.SettingBean;
import model.dao.ActivityDAOHibernate;
import model.dao.PictureDAOHibernate;
import model.dao.SettingDAOHibernate;

@WebServlet("/PictureTest")
public class TestPictureDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureDAOHibernate pictureDAOHibernate;
	private PictureBean pictureBean;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) 
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		pictureDAOHibernate = (PictureDAOHibernate) context.getBean("pictureDAOHibernate");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		selectByMember
		List<PictureBean> Memberresult = pictureDAOHibernate.selectByMember(3);
		out.println("<p>selectByMember</p>");
		out.println(Memberresult);
		Memberresult.forEach(x->{
			out.println(x+"<br>");
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/jpg;base64,");
//			byte[] picture = new byte[x.getPicture().length];
//			int i = 0;
//			for(byte b : Arrays.asList(x.getPicture())) {
//				picture[i]= b;
//				i++;
//			}
			sb.append(
					org.apache.commons.codec.binary.StringUtils.newStringUtf8(
							org.apache.commons.codec.binary.Base64.encodeBase64(
									x.getPicture(), false)));
			out.println("<img src=\""+sb.toString()+"\"/>");
		});
		
		

		out.print("<br>==================================================");
//		selectByArticle
		List<PictureBean> Articleresult = pictureDAOHibernate.selectByArticle(3);
		out.println("<p>selectByArticle</p>");
		out.println(Articleresult);
		Articleresult.forEach(x->{
			out.println(x+"<br>");
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/jpg;base64,");
//			byte[] picture = new byte[x.getPicture().length];
//			int i = 0;
//			for(byte b : Arrays.asList(x.getPicture())) {
//				picture[i]= b;
//				i++;
//			}
			sb.append(
					org.apache.commons.codec.binary.StringUtils.newStringUtf8(
							org.apache.commons.codec.binary.Base64.encodeBase64(
									x.getPicture(), false)));
			out.println("<img src=\""+sb.toString()+"\"/>");
		});
		
		

		out.print("<br>==================================================");
//		selectByActivity
		List<PictureBean> Activityresult = pictureDAOHibernate.selectByActivity(3);
		out.println("<p>selectByActivity</p>");
		out.println(Activityresult);
		Activityresult.forEach(x->{
			out.println(x+"<br>");
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/jpg;base64,");
//			byte[] picture = new byte[x.getPicture().length];
//			int i = 0;
//			for(byte b : Arrays.asList(x.getPicture())) {
//				picture[i]= b;
//				i++;
//			}
			sb.append(
					org.apache.commons.codec.binary.StringUtils.newStringUtf8(
							org.apache.commons.codec.binary.Base64.encodeBase64(
									x.getPicture(), false)));
			out.println("<img src=\""+sb.toString()+"\"/>");
			out.println("<br>");
		});
		
		

		out.print("<br>==================================================");

		//insert
		
//		PictureBean pcB = new PictureBean();
//		pcB.setPicture(new byte[20]);
////		pcB.setActivityid(3);
//		pcB.setMemberid(3);
//		PictureBean xxx = pictureDAOHibernate.insert(pcB);
		
		// delete
		
//		boolean deleresult = pictureDAOHibernate.delete(7);
//		out.println(deleresult);
		
		// update
//		List<PictureBean> AP = pictureDAOHibernate.selectByActivity(3);
		List<PictureBean> AP = pictureDAOHibernate.selectByMember(3);

//		out.print(AP.get(1).getId());
		int x = AP.get(0).getId();
		
		PictureBean finalresult = pictureDAOHibernate.Select(x);
		
		finalresult.setPicture(new byte[1]);
//		
//		
		PictureBean updateresult = pictureDAOHibernate.update(finalresult);
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
