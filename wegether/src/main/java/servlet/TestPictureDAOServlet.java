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
//		List<PictureBean> Memberresult = pictureDAOHibernate.selectByMember(5);
//		List<PictureBean> Memberresult = pictureDAOHibernate.selectByActivity(2);
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

		//insert		/wegether/src/main/webapp/images/06.jpg
		PictureBean pcB = new PictureBean();
		
		String path1 = this.getServletContext().getRealPath("images/06.jpg");
		System.out.println(path1);
		
		String path2 = this.getServletContext().getRealPath("");
		System.out.println(path2);
		File file = new File(path1);
//		 String path = "D:\\TEMP\\picture\\九份\\1.jpg";
//		 File file = new File("a.htm");
//		    File file = new File(path);
//		    System.out.println(file.getAbsolutePath());//输出读取到的文件路径

		
		
		pcB.setPicture(PictureConvert.converFileToByte(file));
		pcB.setActivityid(2);
		pcB.setArticleid(null);
		pcB.setMemberid(null);
		PictureBean temp = pictureDAOHibernate.insert(pcB);
////	 	out.println(temp);		
//////	 	out.println("<img src=\"data:image/jpg;base64,"+PictureConvert.convertBase64Image(temp.getPicture())+"\"/>");
		String pic = "<img src='/wegether/picture/"+temp.getId()+"' width='350' height='250' style=' border: 2px solid #272727; margin: 10px;'>";
		out.println(pic);
		
		// delete
//		boolean deleresult = pictureDAOHibernate.delete(13);
//		out.println(deleresult);
//		
		// update
//		List<PictureBean> AP = pictureDAOHibernate.selectByActivity(2);
//		List<PictureBean> AP = pictureDAOHibernate.selectByArticle(3);
//		List<PictureBean> AP = pictureDAOHibernate.selectByMember(3);
//
////		out.print(AP.get(1).getId());
//		int x = AP.get(0).getId();
//		
//		PictureBean finalresult = pictureDAOHibernate.Select(2);
////
//		File picfile = new File("D:\\WegetherGitHub\\wegether\\src\\main\\webapp\\images\\04.jpg");
//		finalresult.setPicture(PictureConvert.converFileToByte(picfile));
//		PictureBean updateresult = pictureDAOHibernate.update(finalresult);
//		out.println(updateresult);		
//	 	out.println("<img src=\"data:image/jpg;base64,"+PictureConvert.convertBase64Image(updateresult.getPicture())+"\"/>");
//	 	
//		out.println("<img src='images/04.jpg'>");
		
//		String pic = "<img src='/wegether/picture/2' width='350' height='250' style=' border: 2px solid #272727; margin: 10px;'>";
//		out.println(pic);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
