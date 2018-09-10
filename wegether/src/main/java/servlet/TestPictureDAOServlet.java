package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.PictureBean;
import model.dao.implement.PictureDAOHibernate;
import pictureconvert.PictureConvert;

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
//		List<PictureBean> Memberresult = pictureDAOHibernate.selectByMember(1);
//		out.println("<p>selectByMember</p>");
//		out.println(Memberresult);
//		Memberresult.forEach(x->{
//			out.println(x+"<br>");
//			StringBuilder sb = new StringBuilder();
//			sb.append("data:image/jpg;base64,");
////			byte[] picture = new byte[x.getPicture().length];
////			int i = 0;
////			for(byte b : Arrays.asList(x.getPicture())) {
////				picture[i]= b;
////				i++;
////			}
//			sb.append(
//					org.apache.commons.codec.binary.StringUtils.newStringUtf8(
//							org.apache.commons.codec.binary.Base64.encodeBase64(
//									x.getPicture(), false)));
//			out.println("<img src=\""+sb.toString()+"\"/>");
//		});
//		
//		
//
//		out.print("<br>==================================================");
////		selectByArticle
//		List<PictureBean> Articleresult = pictureDAOHibernate.selectByArticle(1);
//		out.println("<p>selectByArticle</p>");
//		out.println(Articleresult);
//		Articleresult.forEach(x->{
//			out.println(x+"<br>");
//			StringBuilder sb = new StringBuilder();
//			sb.append("data:image/jpg;base64,");
////			byte[] picture = new byte[x.getPicture().length];
////			int i = 0;
////			for(byte b : Arrays.asList(x.getPicture())) {
////				picture[i]= b;
////				i++;
////			}
//			sb.append(
//					org.apache.commons.codec.binary.StringUtils.newStringUtf8(
//							org.apache.commons.codec.binary.Base64.encodeBase64(
//									x.getPicture(), false)));
//			out.println("<img src=\""+sb.toString()+"\"/>");
//		});
//		
//		
//
//		out.print("<br>==================================================");
//		selectByActivity
//		List<PictureBean> Activityresult = pictureDAOHibernate.selectByActivity(1);
//		out.println("<p>selectByActivity</p>");
//		out.println(Activityresult);
//		Activityresult.forEach(x->{
//			out.println(x+"<br>");
//			StringBuilder sb = new StringBuilder();
//			sb.append("data:image/jpg;base64,");
////			byte[] picture = new byte[x.getPicture().length];
////			int i = 0;
////			for(byte b : Arrays.asList(x.getPicture())) {
////				picture[i]= b;
////				i++;
////			}
//			sb.append(
//					org.apache.commons.codec.binary.StringUtils.newStringUtf8(
//							org.apache.commons.codec.binary.Base64.encodeBase64(
//									x.getPicture(), false)));
//			out.println("<img src=\""+sb.toString()+"\"/>");
//			out.println("<br>");
//		});
//		
//		
//
//		out.print("<br>==================================================");

		//insert
		PictureBean pcB = new PictureBean();
		File file = new File("wegether/mem05.png");
		pcB.setPicture(PictureConvert.converFileToByte(file));
		pcB.setActivityid(null);
		pcB.setArticleid(null);
		pcB.setMemberid(5);
		PictureBean temp = pictureDAOHibernate.insert(pcB);
	 	out.println(temp);		
	 	out.println("<img src=\"data:image/jpg;base64,"+PictureConvert.convertBase64Image(temp.getPicture())+"\"/>");
	 	
		
		// delete
//		boolean deleresult = pictureDAOHibernate.delete(7);
//		out.println(deleresult);
		
		// update
//		List<PictureBean> AP = pictureDAOHibernate.selectByActivity(3);
//		List<PictureBean> AP = pictureDAOHibernate.selectByArticle(3);
//		List<PictureBean> AP = pictureDAOHibernate.selectByMember(3);
//
////		out.print(AP.get(1).getId());
//		int x = AP.get(0).getId();
//		
//		PictureBean finalresult = pictureDAOHibernate.Select(x);
//		File picfile = new File("wegether/act014.jpg");
//		finalresult.setPicture(PictureConvert.converFileToByte(picfile));
//		PictureBean updateresult = pictureDAOHibernate.update(finalresult);
//		out.println(updateresult);		
//	 	out.println("<img src=\"data:image/jpg;base64,"+PictureConvert.convertBase64Image(updateresult.getPicture())+"\"/>");
	 	
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
