package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import Service.ActivityFormService;
import model.ActivityBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.PictureDAO;

@Controller
@SessionAttributes(names = { "errMsgs" })
public class ActivityCreateController {

	@Autowired
	private ActivityDAO activityDAO;
	@Autowired
	private ActivityFormService activityFormService;

	@Autowired
	PictureDAO pictureDAO;

	@Autowired
	ApplicationContext context;

	@RequestMapping(path = { "/actCreate.controller" }, method = RequestMethod.POST)
	public String actCreate(ActivityBean activityBean, BindingResult bindingResult,
			@RequestParam(value = "picture", required = false) MultipartFile file,
			@RequestParam(value = "startTime", required = false) String startDate,
			@RequestParam(value = "startTimepicker", required = false) String starttime,
			@RequestParam(required = false) String dateline,
			@RequestParam(value = "endTime", required = false) String endDate,
			@RequestParam(value = "endTimepicker", required = false) String endTime,
			@RequestParam(value = "applyform", required = false) String applyform,
			@RequestParam(value = "multipicture", required = false) MultipartFile[] files,
			@RequestAttribute(value = "memberid", required = false) Integer id,
			@RequestParam(value = "setFormOrNot", required = false) String setForm, HttpServletRequest request,
			Model model) throws ParseException, IOException {
		if (id == null) {
			model.addAttribute("loginFail", "請登入");
			return "index.success";
		}

		// System.out.println(applyform);
		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errMsgs", errors);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd k:mm");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		byte[] pic = null;
		if (file != null || !file.isEmpty()) { // 圖片判定
			pic = file.getBytes();
			activityBean.setPicture(pic);
		} else {
			File defultPic = new File("..\\repository\\wegether\\src\\main\\webapp\\images\\actcreate.png");
			try {
				pic = fileToByte(defultPic);
				activityBean.setPicture(pic);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (activityBean != null) {

			if (activityBean.getTitle().isEmpty() || activityBean.getTitle() == null) // 標題判定
				errors.put("title", "請輸入聚會標題");

			if (!starttime.isEmpty() && !":".equals(starttime.substring(2, 3))) { // 開始日期判定
				errors.put("starDateTime", "請輸入正確時間 ex 07:30 PM");
			} else if (startDate == null || starttime == null || starttime.isEmpty()) {
				errors.put("starDateTime", "請輸入開始時間");
			} else if (!startDate.isEmpty()) {
				String[] year = startDate.split("-");
				if (Integer.parseInt(year[0]) >= 2200)
					errors.put("starDateTime", "請輸入正確年份");
			}

			if (!endTime.isEmpty() && !":".equals(endTime.substring(2, 3))) // 結束日期判定
				errors.put("endDateTime", "請輸入正確時間 ex 07:30 PM");
			else if (!endDate.isEmpty()) {
				String[] year2 = endDate.split("-");
				if (Integer.parseInt(year2[0]) >= 2200)
					errors.put("endDateTime", "請輸入正確年份");
			}

			if (activityBean.getContent() == null || activityBean.getContent().isEmpty()) // 內容判定
				errors.put("content", "請輸入詳細描述");

			if (dateline == null || dateline.isEmpty()) // 截止日期判定
				errors.put("deathline", "請輸入截止日期");

			if (errors != null && !errors.isEmpty()) {
				System.out.println("has error msgs");
				System.out.println(errors);
				return "actCreateErr.page";
			}
		}

		String startDateTime = startDateFormat(startDate, starttime);
		Date aa = simpleDateFormat.parse(startDateTime);
		Date bb = simpleDateFormat2.parse(dateline);

		if (endDate.isEmpty() || endTime.isEmpty()) {
			activityBean.setActend(null);
		} else if (!endDate.isEmpty() && !endTime.isEmpty()) {
			String endDateTime = endDateFormat(endDate, endTime);
			Date cc = simpleDateFormat.parse(endDateTime);
			activityBean.setActend(cc);
		}

		activityBean.setTitle(StringEscapeUtils.unescapeHtml(activityBean.getTitle()));
		if (activityBean.getAddr() != null || !activityBean.getAddr().isEmpty())
			activityBean.setAddr(StringEscapeUtils.unescapeHtml(activityBean.getAddr()));
		if (activityBean.getClasstype() != null || !activityBean.getClasstype().isEmpty())
			activityBean.setClasstype(StringEscapeUtils.unescapeHtml(activityBean.getClasstype()));
		activityBean.setContent(StringEscapeUtils.unescapeHtml(activityBean.getContent()));
		activityBean.setHostid(id);
		activityBean.setActbegin(aa);
		activityBean.setDateline(bb);

		 JSONObject formJson= null;		 
		 if (setForm != null && "yes".equals(setForm) && applyform != null) {
			 formJson = activityFormService.stringToJsonObject(applyform);
			 formJson.put("hasForm", true);
		 } else {
			 formJson = activityFormService.stringToJsonObject("{\"hasFrom\" : false}");
		 }
		activityBean.setForm( formJson.toString());

		System.out.println(startDate);
		System.out.println(starttime);
		System.out.println(activityBean);

		ActivityBean insertResult = activityDAO.insert(activityBean);

		if (insertResult == null) {
			errors.put("action", "Insert fail");
		} else {
			int activityid = activityDAO.getActivityId(id, aa, bb);
			System.out.println("activityid = " + activityid);

			PictureBean pictureBean = (PictureBean) context.getBean("pictureBean");
			pictureBean.setActivityid(activityid);
			pictureBean.setPicture(pic);
			pictureDAO.insert(pictureBean);

			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isEmpty()) {
						System.out.println("picture is empty");
					} else {
						byte[] pics = files[i].getBytes();
						PictureBean pictureBean2 = (PictureBean) context.getBean("pictureBean");
						pictureBean2.setActivityid(activityid);
						pictureBean2.setPicture(pics);
						pictureDAO.insert(pictureBean2);
					}
				}

			}
			request.setAttribute("id", activityBean.getId());
			request.setAttribute("ntype", 11);
		}
		return "actCreateSuc.page";

	}

	public String startDateFormat(String startDate, String starttime) {
		String pmam = starttime.substring(6, 8);
		String hour = starttime.substring(0, 2);
		int hourNum = 0;

		if ("PM".equals(pmam) && Integer.parseInt(hour) != 12) {
			hourNum = Integer.parseInt(hour) + 12;
			return startDate + " " + hourNum + starttime.substring(2, 5);
		} else if ("AM".equals(pmam) && Integer.parseInt(hour) == 12) {
			return startDate + " " + hourNum + starttime.substring(2, 5);
		} else
			return startDate + " " + starttime;
	}

	public String endDateFormat(String endDate, String endTime) {
		String pmam2 = endTime.substring(6, 8);
		String hour2 = endTime.substring(0, 2);
		int hourNum = 0;

		if ("PM".equals(pmam2) && Integer.parseInt(hour2) != 12) {
			hourNum = Integer.parseInt(hour2) + 12;
			return endDate + " " + hourNum + endTime.substring(2, 5);
		} else if ("AM".equals(pmam2) && Integer.parseInt(hour2) == 12) {
			return endDate + " " + hourNum + endTime.substring(2, 5);
		} else
			return endDate + " " + endTime;
	}

	public static byte[] fileToByte(File img) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes = null;
		try {
			BufferedImage bi;
			bi = ImageIO.read(img);
			ImageIO.write(bi, "png", baos);
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			baos.close();
		}
		return bytes;
	}
}