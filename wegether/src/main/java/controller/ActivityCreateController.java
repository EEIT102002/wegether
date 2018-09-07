package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import model.ActivityBean;
import model.dao.ActivityDAO;

@Controller
@SessionAttributes(names = { "errMsgs", "colVal" })
public class ActivityCreateController {

	@Autowired
	private ActivityDAO activityDAO;

	@RequestMapping(path = { "/actCreate.controller" }, method = RequestMethod.POST)
	public String actCreate(Model model, ActivityBean activityBean, BindingResult bindingResult,
			@RequestParam(value = "picture", required = false) MultipartFile file,
			@RequestParam(value = "startTime", required = false) String startDate,
			@RequestParam(value = "startTimepicker", required = false) String starttime,
			@RequestParam(required = false) String dateline,
			@RequestParam(value = "endTime", required = false) String endDate,
			@RequestParam(value = "endTimepicker", required = false) String endTime)
			throws ParseException, IOException {
		System.out.println("actCreate()");

		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errMsgs", errors);

		Map<String, String> col = new HashMap<>();
		model.addAttribute("colVal", col);

		if (activityBean != null) {
			col.put("title", activityBean.getTitle());

			if (file != null) { // 圖片判定
				byte[] pic = file.getBytes();
				activityBean.setPicture(pic);
			} else {
				FileInputStream defultPic = new FileInputStream("/wegether/src/main/webapp/images/actcreate.jpg");
				byte[] pic = new byte[defultPic.available()];
				activityBean.setPicture(pic);
			}

			if (dateline.isEmpty()) { // 截止日期判定
				errors.put("deathline", "請輸入截止日期");
				return "actCreateErr.page";
			}
		}

		String startDateTime = startDateFormat(startDate, starttime);
		String endDateTime = endDateFormat(endDate, endTime);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd k:mm");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		Date aa = simpleDateFormat.parse(startDateTime);
		Date cc = simpleDateFormat.parse(endDateTime);
		Date bb = simpleDateFormat2.parse(dateline);

		System.out.println("startDateTime = " + startDateTime);
		System.out.println("after parse startDateTime = " + aa);
		System.out.println("endDateTime = " + endDateTime);
		System.out.println("after parse endDateTime = " + cc);

		activityBean.setHostid(3);
		activityBean.setActbegin(aa);
		activityBean.setActend(cc);
		activityBean.setDateline(bb);
		System.out.println(startDate);
		System.out.println(starttime);
		System.out.println(activityBean);

		activityDAO.insert(activityBean);

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
}