package controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.ActivityBean;
import model.AttendBean;
import model.BlacklistBean;
import model.FriendBean;
import model.MemberBean;
import model.MemberBeanTemp;
import model.PictureBean;
import model.SettingBean;
import model.TrackmemberBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.SettingDAO;
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
	@Autowired
	private SettingDAO settingDAO;
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private BlacklistDAO blacklistDAO;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

	}

	// @RequestMapping(path = { "/personal.controller" })
	// public String processTest(Model model, @RequestAttribute(name = "memberid",
	// required = false) Integer id) {
	//
	// System.out.println("memberId=" + id);
	//
	// MemberBean membean = memberDaoHibernate.select(id);// 抓會員資料
	//
	// // 好友人數 參加次數 追蹤人數 主辦活動
	// List<FriendBean> fribean = friendDaoHibernate.select(id);// 抓好友資料
	// List<AttendBean> attbean = attendDaoHibernate.selectBymemberid(id);// 抓參加資料
	// List<TrackmemberBean> trackbean =
	// trackmemberDaoHibernate.selectBymemberid(id);// 抓追蹤資料
	// List<ActivityBean> actbean = activityDAOHibernate.selectBymemberid(id);//
	// 抓活動資料
	//
	// // 大頭照
	// List<String> memPicList = new ArrayList<>();
	// List<PictureBean> picbean = pictureDaoHibernate.selectByMember(id);
	// picbean.forEach(pic -> {
	// memPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
	//
	// });
	//
	// // 參加活動
	// List<String> attsum = new ArrayList<>();
	// attbean.forEach(temp -> {// 會員所參加的活動id
	// // System.out.println("activityID:"+temp.getActivityid());
	// //
	// System.out.println(activityDAOHibernate.selectId(temp.getActivityid()).getTitle());
	// String temp2 = "";
	// temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
	// + activityDAOHibernate.selectId(temp.getActivityid()).getTitle() +
	// "</td></tr>"
	// + "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
	// + activityDAOHibernate.selectId(temp.getActivityid()).getContent() +
	// "</td></tr>";
	// attsum.add(temp2);
	// });
	//
	// // 主辦活動
	// List<String> hostsum = new ArrayList<>();
	// actbean.forEach(temp -> {
	//
	// // System.out.println("主辦活動人:"+temp.getHostid());
	// // System.out.println("主辦活動名稱:"+temp.getTitle());
	// // System.out.println("主辦活動名稱:"+temp.getContent());
	// //
	// String temp2 = "";
	// temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:" +
	// temp.getTitle() + "</td></tr>"
	// + "<tr><td style=\"padding:10px\">" + "活動內容:<br>" + temp.getContent() +
	// "</td></tr>";
	//
	// hostsum.add(temp2);
	//
	// });
	//
	// // System.out.println(attbean);
	// // System.out.println(attbean.size());
	// model.addAttribute("mem", membean);
	// model.addAttribute("fribean", fribean.size());
	// model.addAttribute("attbean", attbean.size());
	// model.addAttribute("trackbean", trackbean.size());
	// model.addAttribute("picbean", memPicList);
	// model.addAttribute("attsum", attsum);
	// model.addAttribute("hostsum", hostsum);
	// // System.out.println("2");
	// return "personal.success";
	// }

	// controller2

	@RequestMapping(path = { "/personal.controller" })
	public String PersonalController2(Model model, Integer memberId,
			@RequestAttribute(name = "memberid", required = false) Integer id) {

		int memberid = 1;// 被瀏覽的個人頁面
		int status = 1;// 狀態 1好友2非好友3黑名單4非會員未登入5自己
		System.out.println("login=" + id);
		System.out.println("member=" + memberid);

		// 判斷loginid與memberid(要查看的個人頁面)狀態關系
		// 檢查login狀態
		if (id == null) {
			status = 4;
		} else if (id != null && id != memberid) {
			// 判斷是否在觀看者的黑名單上
			BlacklistBean blacklistBean = blacklistDAO.selectByMemberidAndBlackid(id, memberid);
			if (blacklistBean != null) {
				status = 3;
			} else {
				// 判斷是否為好友
				FriendBean result = friendDAO.selectByMidAndFriendid(memberid, id);
				FriendBean result2 = friendDAO.selectByMidAndFriendid(id, memberid);
				if (result != null || result2 != null) {
					status = 1;
					System.out.print("好友");
				} else {
					status = 2;
				}
			}
		} else if (id == memberId) {
			status = 5;
		}
		System.out.println("狀態" + status);
		// 搜尋頁面的隱私權設定
		SettingBean setting = settingDAO.select(memberid);
		MemberBeanTemp membean = new MemberBeanTemp();
		// 抓會員資料到暫存bean
		MemberBean membeansource = memberDaoHibernate.select(memberid);
		membean.setNickname(membeansource.getNickname());
		// membean.setPhoto(membeansource.getPhoto());
		membean.setBirthday(membeansource.getBirthday());
		membean.setSex(membeansource.getSex());
		membean.setJob(membeansource.getJob());
		membean.setCity(membeansource.getCity());
		membean.setFavorite(membeansource.getFavorite());
		membean.setContent(membeansource.getContent());
		membean.setAddr(membeansource.getAddr());
		membean.setTel(membeansource.getTel());
		membean.setSignupdate(membeansource.getSignupdate());
		membean.setRank1(membeansource.getRank1());
		membean.setRank2(membeansource.getRank2());
		membean.setRank3(membeansource.getRank3());
		membean.setFans(membeansource.getFans());
		membean.setNotices(membeansource.getNotices());
		membean.setState(membeansource.getState());
		membean.setFbid(membeansource.getFbid());
		membean.setGoogleid(membeansource.getGoogleid());
		System.out.println(membean.getNickname());
		System.out.println(membean.getBirthday());
		System.out.println(membean.getSex());
		System.out.println(membean.getJob());
		System.out.println(membean.getCity());
		System.out.println(membean.getFavorite());
		System.out.println(membean.getContent());
		System.out.println(membean.getRank1());
		System.out.println(membean.getRank2());
		System.out.println(membean.getRank3());
		System.out.println(membean.getFans());
		System.out.println(membean.getNotices());
		System.out.println(membean.getState());
		System.out.println(membeansource.getPhoto());
		// 好友人數 參加次數 追蹤人數 主辦活動
		List<FriendBean> fribean = friendDaoHibernate.select(memberid);// 抓好友資料
		List<AttendBean> attbean = attendDaoHibernate.selectBymemberid(memberid);// 抓參加資料
		List<TrackmemberBean> trackbean = trackmemberDaoHibernate.selectBymemberid(memberid);// 抓追蹤資料
		List<ActivityBean> actbean = activityDAOHibernate.selectBymemberid(memberid);// 抓活動資料

		// 大頭照
		List<String> memPicList = new ArrayList<>();
		memPicList.add(PictureConvert.convertBase64Image(membeansource.getPhoto()));
		// List<PictureBean> picbean = pictureDaoHibernate.selectByMember(memberid);
		// picbean.forEach(pic -> {
		// memPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
		//
		// });

		// // 設定不顯示的資料
		// 生日
		switch (setting.getBirthday()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {
				System.out.println("生日設定為NULL不公開");
				membean.setBirthday(null);
			}
			break;
		case 2:
			if (status != 5) {
				membean.setBirthday(null);
			}
			break;
		default:
			break;
		}
		// 性別
		switch (setting.getSex()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {
				System.out.println("性別設定為NULL不公開");
				membean.setSex(null);
			}
			break;
		case 2:
			if (status != 5) {
				membean.setSex(null);
			}
			break;
		default:
			break;
		}
		// 職業
		switch (setting.getJob()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {
				System.out.println("職業設定為NULL不公開");
				membean.setJob(null);
			}
			break;
		case 2:
			if (status != 5) {
				membean.setJob(null);
			}
			break;
		default:
			break;
		}
		// 居住縣市
		switch (setting.getCity()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {
				System.out.println("居住縣市設定為NULL不公開");
				membean.setCity(null);
			}
			break;
		case 2:
			if (status != 5) {
				membean.setCity(null);
			}
			break;
		default:
			break;
		}
		// 喜好
		switch (setting.getFavorite()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {
				System.out.println("喜好設定為NULL不公開");
				membean.setFavorite(null);
			}
			break;
		case 2:
			if (status != 5) {
				membean.setFavorite(null);
			}
			break;
		default:
			break;
		}
		// 自我介紹
		switch (setting.getContent()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {
				System.out.println("自我介紹設定為NULL不公開");
				membean.setContent(null);
			}
			break;
		case 2:
			if (status != 5) {
				membean.setContent(null);
			}
			break;
		default:
			break;
		}
		// 評分
		switch (setting.getRankscore()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {
				System.out.println("評分設定為NULL不公開");
				membean.setRank1(null);
				membean.setRank2(null);
				membean.setRank3(null);
			}
			break;
		case 2:
			if (status != 5) {
				membean.setRank1(null);
				membean.setRank2(null);
				membean.setRank3(null);
			}
			break;
		default:
			break;
		}

		// 主辦活動
		List<String> hostsum = new ArrayList<>();// 存放結果
		// String temp2 = "";

		switch (setting.getShowhost()) {
		case 1:
			if (status == 2 || status == 3 || status == 4) {

				System.out.println("主辦活動紀錄設定為NULL不公開");
				String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">不公開</td></tr>";
				hostsum.add(temp2);
			} else {

				actbean.forEach(temp -> {

					System.out.println("主辦活動人:" + temp.getHostid());
					System.out.println("主辦活動名稱:" + temp.getTitle());
					System.out.println("主辦活動名稱:" + temp.getContent());

					String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:" + temp.getTitle()
							+ "</td></tr>" + "<tr><td style=\"padding:10px\">" + "活動內容:<br>" + temp.getContent()
							+ "</td></tr>";
					hostsum.add(temp2);
				});
			}
			break;

		case 2:
			if (status != 5) {
				System.out.println("主辦活動紀錄設定為NULL不公開");
				String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">不公開</td></tr>";
				hostsum.add(temp2);
			} else {
				actbean.forEach(temp -> {

					System.out.println("主辦活動人:" + temp.getHostid());
					System.out.println("主辦活動名稱:" + temp.getTitle());
					System.out.println("主辦活動名稱:" + temp.getContent());

					String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:" + temp.getTitle()
							+ "</td></tr>" + "<tr><td style=\"padding:10px\">" + "活動內容:<br>" + temp.getContent()
							+ "</td></tr>";
					hostsum.add(temp2);
				});
			}
			break;

		case 0:
			actbean.forEach(temp -> {

				System.out.println("主辦活動人:" + temp.getHostid());
				System.out.println("主辦活動名稱:" + temp.getTitle());
				System.out.println("主辦活動名稱:" + temp.getContent());

				String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:" + temp.getTitle()
						+ "</td></tr>" + "<tr><td style=\"padding:10px\">" + "活動內容:<br>" + temp.getContent()
						+ "</td></tr>";
				hostsum.add(temp2);
			});
			break;
		}

		// 參加過的活動
		List<String> attsum = new ArrayList<>();// 存放資料

		switch (setting.getShowactivity()) {
		case 1:

			if (status == 2 || status == 3 || status == 4) {
				System.out.println("參加活動紀錄設定為NULL不公開");
				String temp3 = "<tr><td style=\"padding:10px;font-weight:bold\">不公開</td></tr>";
				attsum.add(temp3);
			} else {
				attbean.forEach(temp -> {// 會員所參加的活動id
					System.out.println("activityID:" + temp.getActivityid());

					System.out.println(activityDAOHibernate.selectId(temp.getActivityid()).getTitle());

					String temp3 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
							+ activityDAOHibernate.selectId(temp.getActivityid()).getTitle() + "</td></tr>"
							+ "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
							+ activityDAOHibernate.selectId(temp.getActivityid()).getContent() + "</td></tr>";
					attsum.add(temp3);
				});
			}
			break;
		case 2:
			if (status != 5) {

				System.out.println("參加活動紀錄設定為NULL不公開");
				String temp3 = "<tr><td style=\"padding:10px;font-weight:bold\">不公開</td></tr>";
				attsum.add(temp3);
			} else {

				attbean.forEach(temp -> {// 會員所參加的活動id
					System.out.println("activityID:" + temp.getActivityid());

					System.out.println(activityDAOHibernate.selectId(temp.getActivityid()).getTitle());

					String temp3 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
							+ activityDAOHibernate.selectId(temp.getActivityid()).getTitle() + "</td></tr>"
							+ "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
							+ activityDAOHibernate.selectId(temp.getActivityid()).getContent() + "</td></tr>";
					attsum.add(temp3);
				});
			}
			break;
		default:
			break;
		}

		// System.out.println(attbean);
		// System.out.println(attbean.size());
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
