package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.ActivityBean;
import model.AttendBean;
import model.FriendBean;
import model.MemberBean;
import model.PictureBean;
import model.TrackmemberBean;
import model.dao.PictureDAO;
import model.dao.implement.ActivityDAOHibernate;
import model.dao.implement.AttendDAOHibernate;
import model.dao.implement.FriendDAOHibernate;
import model.dao.implement.MemberDAOHibernate;
import model.dao.implement.PictureDAOHibernate;
import model.dao.implement.TrackmemberDAOHibernate;
import pictureconvert.PictureConvert;

@Controller
// @SessionAttributes(names={"mem","picbean"})
public class MemberPersonalController {
	@Autowired
	private MemberDAOHibernate memberDaoHibernate;
	@Autowired
	private FriendDAOHibernate friendDaoHibernate;
	@Autowired
	private AttendDAOHibernate attendDaoHibernate;
	@Autowired
	private TrackmemberDAOHibernate trackmemberDaoHibernate;
	@Autowired
	private PictureDAOHibernate pictureDaoHibernate;
	@Autowired
	private ActivityDAOHibernate activityDAOHibernate;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

	}

	@RequestMapping(path = { "/personal.controller" })
	public String processTest(Model model,String memberId) {

		MemberBean membean = memberDaoHibernate.select(Integer.parseInt(memberId));
		// 好友人數 參加次數 追蹤人數 主辦活動
		List<FriendBean> fribean = friendDaoHibernate.select(Integer.parseInt(memberId));
		List<AttendBean> attbean = attendDaoHibernate.selectBymemberid(Integer.parseInt(memberId));
		List<TrackmemberBean> trackbean = trackmemberDaoHibernate.selectBymemberid(Integer.parseInt(memberId));
		List<ActivityBean> actbean = activityDAOHibernate.selectBymemberid(Integer.parseInt(memberId));

		// 大頭照
		List<String> memPicList = new ArrayList<>();
		List<PictureBean> picbean = pictureDaoHibernate.selectByMember(Integer.parseInt(memberId));
		picbean.forEach(pic -> {
			memPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));

		});
		;
		// 參加活動
		List<String> attsum = new ArrayList<>();
		attbean.forEach(temp -> {// 會員所參加的活動id
			// System.out.println("activityID:"+temp.getActivityid());
			// System.out.println(activityDAOHibernate.selectId(temp.getActivityid()).getTitle());
			String temp2 = "";
			temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
					+ activityDAOHibernate.selectId(temp.getActivityid()).getTitle() + "</td></tr>"
					+ "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
					+ activityDAOHibernate.selectId(temp.getActivityid()).getContent() + "</td></tr>";
			attsum.add(temp2);
		});

		// 主辦活動
		List<String> hostsum = new ArrayList<>();
		actbean.forEach(temp -> {

			// System.out.println("主辦活動人:"+temp.getHostid());
			// System.out.println("主辦活動名稱:"+temp.getTitle());
			// System.out.println("主辦活動名稱:"+temp.getContent());
			//
			String temp2 = "";
			temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:" + temp.getTitle() + "</td></tr>"
					+ "<tr><td style=\"padding:10px\">" + "活動內容:<br>" + temp.getContent() + "</td></tr>";

			hostsum.add(temp2);

		});

//		System.out.println(attbean);
//		System.out.println(attbean.size());
		model.addAttribute("mem", membean);
		model.addAttribute("fribean", fribean.size());
		model.addAttribute("attbean", attbean.size());
		model.addAttribute("trackbean", trackbean.size());
		model.addAttribute("picbean", memPicList);
		model.addAttribute("attsum", attsum);
		model.addAttribute("hostsum", hostsum);
		// System.out.println("2");
		return "personal.success";
	}

}
