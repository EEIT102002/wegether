package Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.ActivityBean;
import model.AttendBean;
import model.MemberBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.AttendDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;

@Service
public class ActivityPageService {
	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private PictureDAO pictureDAO;

	@Autowired
	private AttendDAO attendDAO;

	@Autowired
	private MsgDAO msgDAO;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	
	public String method(Model model, Integer actid,
			@RequestAttribute(name = "memberid", required = false) Integer memberid) {
		System.out.println("id :" + memberid);
		
		// flag= 0:未登入 1:主辦人 2:已報名者 3:未報名者
		Integer flag = 0;		
		if (memberid != null) flag = 3;
		// 時間轉換
		String[] week = { "(日)", "(一)", "(二)", "(三)", "(四)", "(五)", "(六)" };
		System.out.println("actid=" + actid);
		List<String> actPicList = new ArrayList<>();
		List<Map> memPicList = new ArrayList<>();

		List<Map> msgsList = new ArrayList<>();


		if (actid == null)	actid = 1;
		ActivityBean actBean = activityDAO.selectId(actid);

		MemberBean hostBean = memberDAO.select(actBean.getHostid());
		
		String hostPic;
		try {
			hostPic = PictureConvert.convertBase64Image(hostBean.getPhoto());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hostPic=null;
		}
		
		List<PictureBean> actPicBeans = pictureDAO.selectByActivity(actid);
		Set<AttendBean> attBeans = actBean.getAttendBean();
		
			
			if (attBeans.size() != 0) {
				for(AttendBean att :attBeans) {
					Map<String, String> attMap = new HashMap<String, String>();
					MemberBean attBean = memberDAO.select(att.getMemberid()); // 報名人員的照片名單
					String memberPic;
					try {
						memberPic = PictureConvert.convertBase64Image(attBean.getPhoto());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						memberPic=null;
					}
					attMap.put("memberPic", memberPic);
					attMap.put("memberId", att.getMemberid().toString());
					
					memPicList.add(attMap);
					if (flag != 2 && memberid == att.getMemberid())	flag = 2;
											
				};
			}
			
		if (memberid == actBean.getHostid()) flag = 1;

		if (actPicBeans.size() != 0)
			actPicBeans.forEach(pic -> {
				
				String actPic;
				try {
					actPic = PictureConvert.convertBase64Image(pic.getPicture());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					actPic=null;
				}
				actPicList.add(actPic);
			});

		
		String actbegin = null;
		Calendar actTime = Calendar.getInstance();
		if (actBean.getActbegin() != null) {
			actTime.setTime(actBean.getActbegin());
			int atMonth = actTime.get(Calendar.MONTH) + 1;
			int atDay = actTime.get(Calendar.DAY_OF_MONTH);
			int atWeek = actTime.get(Calendar.DAY_OF_WEEK);
			int atHour = actTime.get(Calendar.HOUR_OF_DAY);
			String atHourStr = Integer.toString(atHour);
			if (atHour < 10)
				atHourStr = "0" + atHourStr;

			int atMinute = actTime.get(Calendar.MINUTE);
			String atMinuteStr = Integer.toString(atMinute);
			if (atMinute < 10)
				atMinuteStr = "0" + atMinuteStr;

			actbegin = atMonth + " 月 " + atDay + " 日  " + week[atWeek - 1] + " " + atHourStr + " : " + atMinuteStr;
		}

		String dateline = null;
		Calendar dl = Calendar.getInstance();
		if (actBean.getDateline() != null) {
			dl.setTime(actBean.getDateline());
			int dlMonth = dl.get(Calendar.MONTH) + 1;
			int dlDay = dl.get(Calendar.DAY_OF_MONTH);
			int dlWeek = dl.get(Calendar.DAY_OF_WEEK);

			dateline = dlMonth + "/" + dlDay + week[dlWeek - 1];
		}

		model.addAttribute("actBean", actBean);
		model.addAttribute("hostBean", hostBean);

		if (actPicList.size() != 0)
			model.addAttribute("actPicList", actPicList);
		else
			model.addAttribute("actPicList", null);

		if (actPicList.size() != 0)
			model.addAttribute("actPicListSize", actPicList.size() - 1);
		else
			model.addAttribute("actPicListSize", null);

		if (hostPic!= null)
			model.addAttribute("hostPic", hostPic);
		else
			model.addAttribute("hostPicList", null);
		
		if (memPicList.size() != 0)
			model.addAttribute("memPicList", memPicList);
		else
			model.addAttribute("memPicList", null);

		if (attBeans.size() != 0)
			model.addAttribute("attedNumber", attBeans.size() + " 個人報名參加");// 報名人數
		else
			model.addAttribute("attedNumber", null);// 報名人數

		if (actbegin != null)
			model.addAttribute("actbegin", actbegin);
		else
			model.addAttribute("actbegin", null);

		if (dateline != null)
			model.addAttribute("dateline", dateline);
		else
			model.addAttribute("dateline", null);
		
		if (memberid != null)
			model.addAttribute("memberid", memberid);
		else
			model.addAttribute("memberid", null);


		model.addAttribute("flag", flag);
		

				return "activityPage";

	}
}
