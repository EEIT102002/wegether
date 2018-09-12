package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.ActivityBean;
import model.dao.ActivityDAO;

@Controller
public class ActivityEditController {

	@Autowired
	ActivityDAO activityDAO;

	@RequestMapping("/actEdit.getBean.controller")
	public String getBeanToJsp(Model model, int actid) throws UnsupportedEncodingException {
		// actid = 55; //整合時記得拿掉
		// System.out.println("actid:"+actid);
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
		else if (startHour >= 1 && startHour <= 11)
			startTime = startHour + ":" + startTime.substring(3, 5) + " AM";
		else if (startHour == 12)
			startTime = startHour + ":" + startTime.substring(3, 5) + " PM";
		else
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
				endTime = endHour + ":" + endTime.substring(3, 5) + " AM";
			else if (endHour == 12)
				endTime = endHour + ":" + endTime.substring(3, 5) + " PM";
			else
				endTime = (endHour - 12) + ":" + endTime.substring(3, 5) + " PM";
			model.addAttribute("endTimepicker", endTime);
		}

		String deathLine = result.getDateline().toString();
		model.addAttribute("deathLine", deathLine.substring(0, 10));

		return "actGetBeanSuc.page";
	}

	@RequestMapping(path = { "/actEdit.edit.controller" }, method = RequestMethod.POST)
	public String activityEdit(Model model, ActivityBean activityBean, BindingResult bindingResult,
			@RequestParam(value = "picture", required = false) MultipartFile file,
			@RequestParam(value = "startTime", required = false) String startDate,
			@RequestParam(value = "startTimepicker", required = false) String starttime,
			@RequestParam(required = false) String dateline,
			@RequestParam(value = "endTime", required = false) String endDate,
			@RequestParam(value = "endTimepicker", required = false) String endTime)
			throws ParseException, IOException {

		System.out.println(activityBean);
		System.out.println(dateline);
		System.out.println("content=" + activityBean.getContent());

		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errMsgs", errors);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd k:mm");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		byte[] pic = null;
		if (file != null) { // 圖片判定
			pic = file.getBytes();
			activityBean.setPicture(pic); // 塞圖片
		}

		if (activityBean.getTitle() == null || activityBean.getTitle().isEmpty()) // 標題判定
			errors.put("title", "請輸入聚會標題");

		if (activityBean.getClasstype() == null || activityBean.getClasstype().isEmpty()) // 活動類別判定
			activityBean.setClasstype(null);

		if (starttime != null && !":".equals(starttime.substring(2, 3))) // 開始日期判定
			errors.put("starDateTime", "請輸入正確時間 ex 07:30 PM");
		else if (startDate == null || starttime == null)
			errors.put("starDateTime", "請輸入開始時間");
		else if (startDate != null) {
			String[] year = startDate.split("-");
			if (Integer.parseInt(year[0]) >= 2200)
				errors.put("starDateTime", "請輸入正確年份");
		}

		System.out.println("startDate: " + startDate);
		System.out.println("starttime: " + starttime);
		System.out.println("endDate: " + endDate);
		System.out.println("endTime: " + endTime);

		String[] year2 = null;
		if (endDate != null)
			year2 = endDate.split("-");
		if ((endTime != null) && !":".equals(endTime.substring(2, 3))) // 結束日期判定
			errors.put("endDateTime", "請輸入正確時間 ex 07:30 PM");
		else if ((endDate != null) && Integer.parseInt(year2[0]) >= 2200)
			errors.put("endDateTime", "請輸入正確年份");
		else if (endDate == null || endTime == null)
			activityBean.setActend(null);

		if (activityBean.getContent().isEmpty()) // 內容判定
			errors.put("content", "請輸入詳細描述");

		if (dateline.isEmpty()) // 截止日期判定
			errors.put("deathline", "請輸入截止日期");

		if (errors != null && !errors.isEmpty()) {
			System.out.println("has error msgs");
			System.out.println(errors);
			return "actEditErr.page";
		}

		String startDateTime = startDateFormat(startDate, starttime);
		Date aa = simpleDateFormat.parse(startDateTime);
		// Date bb = simpleDateFormat2.parse(dateline);
		//
		// if (endDate.isEmpty() || endTime.isEmpty()) {
		// activityBean.setActend(null);
		// } else if (!endDate.isEmpty() && !endTime.isEmpty()) {
		// String endDateTime = endDateFormat(endDate, endTime);
		// Date cc = simpleDateFormat.parse(endDateTime);
		// activityBean.setActend(cc);
		// }

		activityBean.setTitle(activityBean.getTitle());
		activityBean.setActbegin(aa);
		// activityBean.setDateline(bb);
		//
		// System.out.println(startDate);
		// System.out.println(starttime);
		// System.out.println(activityBean);
		//
		// activityDAO.update(activityBean);

		return "actEditErr.page";
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
