package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;

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
			@RequestParam(value = "endTimepicker", required = false) String endTime,
			@RequestParam(value="applyform", required = false)String applyform)
			throws ParseException, IOException {
		System.out.println("actCreate()");
		System.out.println(applyform);
		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errMsgs", errors);

		Map<String, String> col = new HashMap<>();
		model.addAttribute("colVal", col);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd k:mm");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		byte[] pic = null;
		if (!file.isEmpty()) { // 圖片判定
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
			col.put("title", activityBean.getTitle());

			if (activityBean.getTitle().isEmpty()) // 標題判定
				errors.put("title", "請輸入聚會標題");

			if (!starttime.isEmpty() && !":".equals(starttime.substring(2, 3))) // 開始日期判定
				errors.put("starDateTime", "請輸入正確時間 ex 07:30 PM");
			else if (startDate.isEmpty() || starttime.isEmpty())
				errors.put("starDateTime", "請輸入開始時間");

			if (!endTime.isEmpty() && !":".equals(endTime.substring(2, 3))) // 結束日期判定
				errors.put("endDateTime", "請輸入正確時間 ex 07:30 PM");

			if (activityBean.getContent().isEmpty()) // 內容判定
				errors.put("content", "請輸入詳細描述");

			if (dateline.isEmpty()) // 截止日期判定
				errors.put("deathline", "請輸入截止日期");

			if (errors != null && !errors.isEmpty()) {
				System.out.println("has error msgs");
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

		activityBean.setHostid(3);
		activityBean.setActbegin(aa);
		activityBean.setDateline(bb);

		activityBean.setForm(applyform);
		System.out.println(startDate);
		System.out.println(starttime);
		System.out.println(activityBean);

		activityDAO.insert(activityBean);

		return "actCreateErr.page";
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