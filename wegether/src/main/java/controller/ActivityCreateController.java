package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.ActivityBean;
import model.dao.ActivityDAO;

@Controller
@SessionAttributes(names = { "errMsgs", "colVal" })
public class ActivityCreateController {

	@Autowired
	private ActivityDAO activityDAO;

	@RequestMapping(path = { "/actCreate.controller" })
	public String actCreate(Model model, ActivityBean activityBean, BindingResult bindingResult,
			@RequestParam("startTime") String startDate, @RequestParam("startTimepicker") String startTime,
			@RequestParam String dateline, @RequestParam("endTime") String endDate,
			@RequestParam("endTimepicker") String endTime, @RequestParam String picture) throws ParseException, FileNotFoundException {
		System.out.println("actCreate()");

		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errMsgs", errors);

		Map<String, String> col = new HashMap<>();
		model.addAttribute("colVal", col);

		if (activityBean != null) {
			col.put("title", activityBean.getTitle());

			byte[] pic = activityBean.getPicture();
			System.out.println("pic = "+pic);

			Encoder encoder = Base64.getEncoder();
			String afterEncode = encoder.encodeToString(pic);
			System.out.println("String afterEncode = "+afterEncode);
			
			afterEncode.getBytes();
			activityBean.setPicture(afterEncode.getBytes());

			if (dateline.isEmpty()) {
				errors.put("deathline", "請輸入截止日期");
				return "actCreateErr.page";
			}
			System.out.println("a=" + activityBean.getCity());
			return "actCreateSuc.page";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd k:mm");

		String pmam = startTime.substring(6, 8);
		String pmam2 = endTime.substring(6, 8);
		String hour = startTime.substring(0, 2);
		String hour2 = endTime.substring(0, 2);
		int hourNum = 0;
		String format = null;
		String format2 = null;
		if ("PM".equals(pmam) && Integer.parseInt(hour) != 12) {
			hourNum = Integer.parseInt(hour) + 12;
			format = startDate + " " + hourNum + startTime.substring(2, 5);
		} else if ("AM".equals(pmam) && Integer.parseInt(hour) == 12) {
			format = startDate + " " + hourNum + startTime.substring(2, 5);
		} else
			format = startDate + " " + startTime;

		if ("PM".equals(pmam2) && Integer.parseInt(hour2) != 12) {
			hourNum = Integer.parseInt(hour2) + 12;
			format2 = endDate + " " + hourNum + endTime.substring(2, 5);
		} else if ("AM".equals(pmam2) && Integer.parseInt(hour2) == 12) {
			hourNum = Integer.parseInt(hour2) - 12;
			format2 = endDate + " " + hourNum + endTime.substring(2, 5);
		} else
			format2 = endDate + " " + endTime;

		System.out.println("format = " + format);
		System.out.println("format2 = " + format2);

		Date aa = simpleDateFormat.parse(format);
		System.out.println("after format = " + aa);
		Date cc = simpleDateFormat.parse(format2);
		Date bb = simpleDateFormat.parse(dateline);

		activityBean.setHostid(3);
		activityBean.setActbegin(aa);
		activityBean.setActend(cc);
		activityBean.setDateline(bb);
		System.out.println(startDate);
		System.out.println(startTime);
		System.out.println(activityBean);

		activityDAO.insert(activityBean);

		return "actCreateErr.page";
	}
}
