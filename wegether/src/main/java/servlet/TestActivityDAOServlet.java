package servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import model.ActivityBean;
import model.dao.implement.ActivityDAOHibernate;
import pictureconvert.PictureConvert;

@WebServlet("/TestActivityDAOServlet")
public class TestActivityDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat;
	private ActivityDAOHibernate activityDAOHibernate;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext) application
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		activityDAOHibernate = (ActivityDAOHibernate) context.getBean("activityDAOHibernate");

		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// selectId(id)
//		 ActivityBean result = activityDAOHibernate.selectId(8);

		// selectAll()
		 List<ActivityBean> result = activityDAOHibernate.selectAll();

		// selectOfIndex
		// String beginDate = "2018-08-01";
		// String endDate = "2018-10-01";
		// Date t1 = null, t2 = null;;
		// try {
		// t1 = simpleDateFormat.parse(beginDate);
		// t2 = simpleDateFormat.parse(endDate);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }

		// List<ActivityBean> result =
		// activityDAOHibernate.selectOfIndex(0,0,beginDate,endDate,"戶外活動","山");

		// insert
//		ActivityBean bean = new ActivityBean();
//		String beginDate = "2018-10-06 18:30:00";
//		 String endDate = "2018-10-06 21:00:00";
//		 String dateLine ="2018-09-28";
//		 Date actbegin = null, actend = null, dateline=null;
//		 try {
//			 actbegin = simpleDateFormat.parse(beginDate);
//			 actend = simpleDateFormat.parse(endDate);
//			 dateline = simpleDateFormat.parse(dateLine);
//		 } catch (ParseException e) {
//		 e.printStackTrace();
//		 }
//		bean.setActbegin(actbegin);
//		bean.setActend(actend);
//		bean.setAddr("台北市 中山北路二段37之一號");
//		bean.setCity(102);
//		bean.setClasstype("聚餐活動");
//		bean.setClick(97);
//		bean.setContent("鳳凰花開，驪歌輕唱，諄諄教誨，師恩難忘！\r\n" + 
//				"\r\n" + 
//				"台北老爺大酒店為莘莘學子及辛勞師長們，推出最新2018年謝師宴專案\r\n" + 
//				"\r\n" + 
//				"中西日三種風格任選，5月31日前訂席>>享早鳥優惠10%服務費減免\r\n" + 
//				" \r\n" + 
//				"台北老爺離中山捷運站僅5分鐘步行，不但老師同學前往都便利，\r\n" + 
//				"\r\n" + 
//				"並可品嚐道地粵式中華料理、食尚新穎的西式自助餐及忠於原味的日式宴席!\r\n" + 
//				"\r\n" + 
//				"謝師宴所需更一應俱全，提供免費停車、螢幕投影、音響、舞台及海報指引；\r\n" + 
//				"\r\n" + 
//				"達一定數量，更加贈空飄氣球、馬卡龍好禮、紅酒等，CP值爆高!");
////		bean.setCreatetime(new java.util.Date());
//		bean.setDateline(dateline);
//		bean.setFeed(500);
//		bean.setForm("Activityform");
//		bean.setHostid(1);
//		bean.setJudges(101);
//		bean.setNumberlimit(100);
//		
//		File file = new File("wegether/act011.jpg");
//		bean.setPicture(PictureConvert.converFileToByte(file));
//		bean.setRank1(3.0);
//		bean.setRank2(3.0);
//		bean.setRank3(3.0);
//		 bean.setState(0);
//		bean.setTitle("EEIT102 謝師宴");
//		ActivityBean result = activityDAOHibernate.insert(bean);

		// update
//		ActivityBean bean = new ActivityBean();
//		String beginDate = "2018-10-06 18:30:00";
//		 String endDate = "2018-10-06 21:00:00";
//		 String dateLine ="2018-09-28";
//		 Date actbegin = null, actend = null, dateline=null;
//		 try {
//			 actbegin = simpleDateFormat.parse(beginDate);
//			 actend = simpleDateFormat.parse(endDate);
//			 dateline = simpleDateFormat.parse(dateLine);
//		 } catch (ParseException e) {
//		 e.printStackTrace();
//		 }
//		bean.setActbegin(actbegin);
//		bean.setActend(actend);
//		bean.setAddr("台北市 中山北路二段37之一號");
//		bean.setCity(102);
//		bean.setClasstype("聚餐活動");
//		bean.setClick(97);
//		bean.setContent("鳳凰花開，驪歌輕唱，諄諄教誨，師恩難忘！\r\n" + 
//				"\r\n" + 
//				"台北老爺大酒店為莘莘學子及辛勞師長們，推出最新2018年謝師宴專案\r\n" + 
//				"\r\n" + 
//				"中西日三種風格任選，5月31日前訂席>>享早鳥優惠10%服務費減免\r\n" + 
//				" \r\n" + 
//				"台北老爺離中山捷運站僅5分鐘步行，不但老師同學前往都便利，\r\n" + 
//				"\r\n" + 
//				"並可品嚐道地粵式中華料理、食尚新穎的西式自助餐及忠於原味的日式宴席!\r\n" + 
//				"\r\n" + 
//				"謝師宴所需更一應俱全，提供免費停車、螢幕投影、音響、舞台及海報指引；\r\n" + 
//				"\r\n" + 
//				"達一定數量，更加贈空飄氣球、馬卡龍好禮、紅酒等，CP值爆高!");
//		bean.setCreatetime(new java.util.Date());
//		bean.setDateline(dateline);
//		bean.setFeed(500);
//		bean.setForm("Activityform");
//		bean.setHostid(1);
//		bean.setJudges(101);
//		bean.setNumberlimit(100);
//		
//		File file = new File("wegether/act011.jpg");
//		bean.setPicture(PictureConvert.converFileToByte(file));
//		bean.setRank1(3.0);
//		bean.setRank2(3.0);
//		bean.setRank3(3.0);
//		 bean.setState(0);
//		bean.setTitle("EEIT102 謝師宴");
//		bean.setId(8);
//		 ActivityBean result = activityDAOHibernate.update(bean);

		// delete
		// boolean result = activityDAOHibernate.delete(7);

		// selectByState
		// List<ActivityBean> result = activityDAOHibernate.selectByState(0);

		// selectByAddress
		// List<ActivityBean> result = activityDAOHibernate.selectByAddress(0,"仁愛");

		// selectByTime
		// String begin = "2018-08-01";
		// String end = "2018-09-10";
		// Date t1 = null, t2 = null;;
		// try {
		// t1 = simpleDateFormat.parse(begin);
		// t2 = simpleDateFormat.parse(end);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		//
		// List<ActivityBean> result = activityDAOHibernate.selectByTime(0,t1,t2);

		// selectByActbegin
		// String input = "2018-09-09";
		// Date t = null;
		// try{
		// t = simpleDateFormat.parse(input);
		// }catch(ParseException e){
		// e.printStackTrace();
		// }
		// List<ActivityBean> result = activityDAOHibernate.selectByActbegin(0,t);

		// selectByCity
		// List<ActivityBean> result = activityDAOHibernate.selectByCity(0,112);

		// selectByTitle
		// List<ActivityBean> result = activityDAOHibernate.selectByTitle(0,"大賞楓");

		// selectByClasstype
		// List<ActivityBean> result = activityDAOHibernate.selectByClasstype(0,"戶外");

		// selectByHost
		// List<ActivityBean> result = activityDAOHibernate.selectByNickname(0,"安");

		// selectByFeed
		// List<ActivityBean> result = activityDAOHibernate.selectByFees(0,1000);

		// selectByClick
		// List<ActivityBean> result = activityDAOHibernate.selectByClick(0,90);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>ActivityTest</p>");

		String relativelyPath = System.getProperty("user.dir");
		out.println("relativelyPath" + relativelyPath);
		// result.forEach(temp->{
		// out.println(temp+"<br><br>");
		// out.println("===============<br><br>");
		// });
		//
		System.out.println(result);
//		out.println(result);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public static byte[] fileToByte(File img) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes = null;
		try {
			BufferedImage bi;
			bi = ImageIO.read(img);
			ImageIO.write(bi, "jpg", baos);
			bytes = baos.toByteArray();
			System.err.println(bytes.length);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			baos.close();
		}
		return bytes;
	}
}
