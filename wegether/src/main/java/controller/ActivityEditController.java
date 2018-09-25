package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import model.ActivityBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;

@Controller
public class ActivityEditController {

	@Autowired
	ActivityDAO activityDAO;

	@Autowired
	PictureDAO pictureDAO;

	@Autowired
	ApplicationContext context;

	@RequestMapping("/actEdit.getBean.controller")
	public String getBeanToJsp(Model model, Integer actid,
			@RequestAttribute(value = "memberid", required = false) Integer id) throws UnsupportedEncodingException {
		// actid = 55; //整合時記得拿掉
		// System.out.println("actid:"+actid);

		if (id == null) {
			model.addAttribute("loginFail", "請登入");
			return "index.success";
		}

		List<String> actPics = new ArrayList<>();
		List<Integer> actIds = new ArrayList<>();

		System.out.println("getBeanToJsp() actid = " + actid);
		ActivityBean result = activityDAO.selectId(actid);
		model.addAttribute("result", result);
		model.addAttribute("actid", actid);
		model.addAttribute("type", result.getClasstype());

		String startDateTime = result.getActbegin().toString();
		model.addAttribute("startTime", startDateTime.substring(0, 10));
		String startTime = startDateTime.substring(11, 16);
		int startHour = Integer.parseInt(startTime.substring(0, 2));
		if (startHour == 0)
			startTime = (startHour + 12) + ":" + startTime.substring(3, 5) + " AM";
		else if (startHour >= 1 && startHour <= 9)
			startTime = "0" + startHour + ":" + startTime.substring(3, 5) + " AM";
		else if (startHour == 10 || startHour == 11)
			startTime = startHour + ":" + startTime.substring(3, 5) + " AM";
		else if (startHour == 12)
			startTime = startHour + ":" + startTime.substring(3, 5) + " PM";
		else if (startHour >= 13 && startHour <= 21)
			startTime = "0" + (startHour - 12) + ":" + startTime.substring(3, 5) + " PM";
		else if (startHour == 22 || startHour == 23)
			startTime = (startHour - 12) + ":" + startTime.substring(3, 5) + " PM";
		model.addAttribute("startTimepicker", startTime);

		if (result.getActend() != null) { // 塞結束時間
			String endDateTime = result.getActend().toString();
			model.addAttribute("endTime", endDateTime.substring(0, 10));
			String endTime = endDateTime.substring(11, 16);
			int endHour = Integer.parseInt(endTime.substring(0, 2));
			if (endHour == 0)
				endTime = (endHour + 12) + ":" + endTime.substring(3, 5) + " AM";
			else if (endHour >= 1 && endHour <= 11)
				endTime = "0" + endHour + ":" + endTime.substring(3, 5) + " AM";
			else if (endHour == 11)
				endTime = endHour + ":" + endTime.substring(3, 5) + " AM";
			else if (endHour == 12)
				endTime = endHour + ":" + endTime.substring(3, 5) + " PM";
			else
				endTime = "0" + (endHour - 12) + ":" + endTime.substring(3, 5) + " PM";
			model.addAttribute("endTimepicker", endTime);
		}

		String deathLine = result.getDateline().toString();
		model.addAttribute("deathLine", deathLine.substring(0, 10));

		if (result.getPicture() != null) {
			String pic = PictureConvert.convertBase64Image(result.getPicture());
			model.addAttribute("actOnePic", pic);
		}

		// PictureBean pictureBean = (PictureBean) context.getBean("pictureBean");
		List<PictureBean> beanlist = pictureDAO.selectByActivity(actid);
		int len = beanlist.size();
		for (int i = 1; i < len; i++) {
			PictureBean oneBean = beanlist.get(i);
			String picture = PictureConvert.convertBase64Image(oneBean.getPicture());
			actPics.add(picture);
			model.addAttribute("actAllPic", actPics);

			Integer oneId = oneBean.getId();
			actIds.add(oneId);
			model.addAttribute("actAllId", actIds);
		}

		return "actGetBeanSuc.page";
	}

	@RequestMapping(path = { "/actEdit.edit.controller" }, method = RequestMethod.POST)
	public String activityEdit(Model model, ActivityBean activityBean, BindingResult bindingResult,
			@RequestParam(value = "picture", required = false) MultipartFile file,
			@RequestParam(value = "startTime", required = false) String startDate,
			@RequestParam(value = "startTimepicker", required = false) String starttime,
			@RequestParam(required = false) String dateline,
			@RequestParam(value = "endTime", required = false) String endDate,
			@RequestParam(value = "endTimepicker", required = false) String endTime, HttpServletRequest request)
			throws ParseException, IOException {

		System.out.println(activityBean);
		System.out.println(dateline);
		System.out.println("content=" + activityBean.getContent().trim());

		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errMsgs", errors);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd k:mm");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		byte[] pic = null;
		if (file != null || !file.isEmpty()) { // 圖片判定
			System.out.println("has pic");
			System.out.println("file=" + file);
			pic = file.getBytes();
			activityBean.setPicture(pic); // 塞圖片

			List<PictureBean> pictureBean = pictureDAO.selectByActivity(activityBean.getId());
			PictureBean bean = (PictureBean) context.getBean("pictureBean");
			bean.setId(pictureBean.get(0).getId());
			bean.setPicture(pic);
			pictureDAO.update(bean);
		} else {
			System.out.println("has no pic");
		}

		if (activityBean.getTitle() == null || activityBean.getTitle().isEmpty()) // 標題判定
			errors.put("title", "請輸入聚會標題");

		if (activityBean.getClasstype() == null || activityBean.getClasstype().isEmpty()) // 活動類別判定
			activityBean.setClasstype(null);

		System.out.println("startDate: " + startDate);
		System.out.println("starttime.length: " + starttime.length());
		System.out.println("status of starttime= " + starttime.isEmpty());

		if (!starttime.isEmpty()) { // 開始日期判定
			if (!":".equals(starttime.substring(2, 3)))
				errors.put("starDateTime", "請輸入正確時間 ex 07:30 PM");
		} else if (startDate.isEmpty() || starttime.isEmpty())
			errors.put("starDateTime", "請輸入開始時間");
		else if (startDate != null) {
			String[] year = startDate.split("-");
			if (Integer.parseInt(year[0]) >= 2200)
				errors.put("starDateTime", "請輸入正確年份");
		}

		System.out.println("endDate: " + endDate);
		System.out.println("endTime: " + endTime);

		String[] year2 = null;
		int year3 = 0;
		if (!endDate.isEmpty()) {
			year2 = endDate.split("-");
			System.out.println("year2[0]= " + year2[0]);
			year3 = Integer.parseInt(year2[0]);
		}
		if (!endTime.isEmpty()) { // 結束日期判定
			System.out.println("endtime is not empty");
			if (!":".equals(endTime.substring(2, 3)))
				errors.put("endDateTime", "請輸入正確時間 ex 07:30 PM");
		} else if (endDate != null && year3 >= 2200) {
			errors.put("endDateTime", "請輸入正確年份");
		}

		System.out.println("before content");
		if (activityBean.getContent() == null) // 內容判定
			errors.put("content", "請輸入詳細描述");

		System.out.println("before dateline");
		if (dateline == null) // 截止日期判定
			errors.put("deathline", "請輸入截止日期");

		if (!errors.isEmpty()) {
			System.out.println("has error msgs");
			System.out.println(errors);
			return "actEditErr.page";
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
		activityBean.setId(activityBean.getId());
		activityBean.setTitle(StringEscapeUtils.unescapeHtml(activityBean.getTitle()));
		activityBean.setContent(StringEscapeUtils.unescapeHtml(activityBean.getContent().trim()));
		if (activityBean.getAddr() != null || !activityBean.getAddr().isEmpty())
			activityBean.setAddr(StringEscapeUtils.unescapeHtml(activityBean.getAddr()));
		if (activityBean.getClasstype() != null || !activityBean.getClasstype().isEmpty())
			activityBean.setClasstype(StringEscapeUtils.unescapeHtml(activityBean.getClasstype()));
		activityBean.setActbegin(aa);
		activityBean.setDateline(bb);

		ActivityBean updateResult = activityDAO.update(activityBean);

		if (updateResult == null)
			return "actEditErr.page";

		request.setAttribute("id", activityBean.getId());
		request.setAttribute("ntype", 13);
		return "actEditSuc.page";
	}

	@RequestMapping("/insertOtherPics.do")
	@ResponseBody
	public PictureBean create(@RequestParam(value = "pics", required = false) MultipartFile[] picture, int activitiId)
			throws URISyntaxException, IOException {
		PictureBean result;
		for (int i = 0; i < picture.length; i++) {
			PictureBean pictureBean = (PictureBean) context.getBean("pictureBean");
			picture[i].getBytes();
			pictureBean.setActivityid(activitiId);
			pictureBean.setPicture(picture[i].getBytes());
			result = pictureDAO.insert(pictureBean);
			return result;
		}
		return null;
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
