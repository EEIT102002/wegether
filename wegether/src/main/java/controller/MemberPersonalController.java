package controller;

import static org.hamcrest.CoreMatchers.instanceOf;

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

import Service.MidAndIdCheckServices;
import javassist.expr.NewArray;
import model.ActivityBean;
import model.AttendBean;
import model.BlacklistBean;
import model.FriendBean;
import model.MemberBean;
import model.MemberBeanTemp;
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
	private ActivityDAOHibernate activityDAOHibernate;
	@Autowired
	private SettingDAO settingDAO;
	@Autowired 
	MidAndIdCheckServices midAndIdCheckServices;

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
		//檢查是否有收到memberid
		//登入下拉選單看自己的個人頁面 只須給登入id
		if(memberId==null) {
			memberId=id;
		}
		//檢查memberid和id關西
		int status =midAndIdCheckServices.check(id, memberId);// 狀態 1好友2非好友3黑名單4非會員未登入5自己
		
		int memberid = memberId;

		System.out.println("login=" + id);
		System.out.println("member=" + memberid);
		System.out.println("檢查後狀態為" + status);
		
		if(status==3) {
			return "index.success";
		}
		
		// 搜尋頁面的隱私權設定
		SettingBean setting = settingDAO.select(memberid);
		MemberBeanTemp membean = new MemberBeanTemp();
		// 抓會員資料到暫存bean
		MemberBean membeansource = memberDaoHibernate.select(memberid);
		membean.setId(membeansource.getId());
		membean.setNickname(membeansource.getNickname());
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
		System.out.println("ID:"+membean.getId());
		System.out.println("暱稱:"+membean.getNickname());
		System.out.println("生日:"+membean.getBirthday());
		System.out.println("性別:"+membean.getSex());
		System.out.println("工作:"+membean.getJob());
		System.out.println("城市:"+membean.getCity());
		System.out.println("興趣:"+membean.getFavorite());
		System.out.println("自我介紹:"+membean.getContent());
		System.out.println("評分1:"+membean.getRank1());
		System.out.println("評分2:"+membean.getRank2());
		System.out.println("評分3:"+membean.getRank3());
		System.out.println("粉絲人數:"+membean.getFans());
		System.out.println("提醒:"+membean.getNotices());
		System.out.println("狀態:"+membean.getState());
		// 好友人數 參加次數 追蹤人數 主辦活動
		List<FriendBean> fribean = friendDaoHibernate.selectAllFriendByMemberid(memberid);// 抓好友資料
		List<AttendBean> attbean = attendDaoHibernate.selectBymemberid(memberid);// 抓參加資料
		List<TrackmemberBean> trackbean = trackmemberDaoHibernate.selectBymemberid(memberid);// 抓追蹤資料
		List<ActivityBean> actbean = activityDAOHibernate.selectBymemberid(memberid);// 抓活動資料

		// 大頭照

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
		
		//功能列
		List<String> FunctionColumn = new ArrayList<>();
		String Edit,Track,addFriend,blackList;
		Edit="1";
		Track="1";
		addFriend="1";
		blackList="1";
		
		switch (status) {
		case 5:
			
			FunctionColumn.add(Edit);
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			
			break;
			
		case 4:
			
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			
			break;			

			
		case 3:
			
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			
			break;			
			
		case 2:
			
			FunctionColumn.add("");
			FunctionColumn.add(Track);
			FunctionColumn.add(addFriend);
			FunctionColumn.add(blackList);
			
			break;			

		case 1:
			
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add(blackList);
			
			break;
			
		default:
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			FunctionColumn.add("");
			break;
		}
		
		// 主辦活動
		List<String> hostsum = new ArrayList<>();// 存放結果

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

					String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
							+ "<a href=\"activityPage.controller?actid=" + temp.getId() + "\"/>" + temp.getTitle()
							+ "</a>" + "</td></tr>" + "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
							+ temp.getContent() + "</td></tr>";
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

					String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
							+ "<a href=\"activityPage.controller?actid=" + temp.getId() + "\"/>" + temp.getTitle()
							+ "</a>" + "</td></tr>" + "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
							+ temp.getContent() + "</td></tr>";
					hostsum.add(temp2);
				});
			}
			break;

		case 0:
			actbean.forEach(temp -> {

				System.out.println("主辦活動人:" + temp.getHostid());
				System.out.println("主辦活動名稱:" + temp.getTitle());
				System.out.println("主辦活動名稱:" + temp.getContent());

				String temp2 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
						+ "<a href=\"activityPage.controller?actid=" + temp.getId() + "\"/>" + temp.getTitle() + "</a>"
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
				System.out.print("case 1 else結果"+attbean);
				attbean.forEach(temp -> {// 會員所參加的活動id
					 System.out.println("activityID:" + temp.getActivityid());
					 System.out.println(activityDAOHibernate.selectId(temp.getActivityid()).getTitle());

					String temp3 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
							+ "<a href=\"activityPage.controller?actid=" + temp.getId() + "\"/>"
							+ activityDAOHibernate.selectId(temp.getActivityid()).getTitle() + "</a></td></tr>"
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
				System.out.println("自己看");
				attbean.forEach(temp -> {// 會員所參加的活動id
					System.out.println("activityID:" + temp.getActivityid());

					System.out.println(activityDAOHibernate.selectId(temp.getActivityid()).getTitle());

					String temp3 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
							+ "<a href=\"activityPage.controller?actid=" + temp.getId() + "\"/>"
							+ activityDAOHibernate.selectId(temp.getActivityid()).getTitle() + "</a></td></tr>"
							+ "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
							+ activityDAOHibernate.selectId(temp.getActivityid()).getContent() + "</td></tr>";
					attsum.add(temp3);
				});
			}
			break;
		case 0:
			attbean.forEach(temp -> {// 會員所參加的活動id
				System.out.println("公開");
				System.out.println("activityID:" + temp.getActivityid());
				System.out.println(activityDAOHibernate.selectId(temp.getActivityid()).getTitle());

				String temp3 = "<tr><td style=\"padding:10px;font-weight:bold\">" + "活動標題:"
						+ "<a href=\"activityPage.controller?actid=" + temp.getId() + "\"/>"
						+ activityDAOHibernate.selectId(temp.getActivityid()).getTitle() + "</a></td></tr>"
						+ "<tr><td style=\"padding:10px\">" + "活動內容:<br>"
						+ activityDAOHibernate.selectId(temp.getActivityid()).getContent() + "</td></tr>";
				attsum.add(temp3);
			});
			break;
		}
		model.addAttribute("mem", membean);
		model.addAttribute("fribean", fribean.size());
		model.addAttribute("attbean", attbean.size());
		model.addAttribute("trackbean", trackbean.size());
		model.addAttribute("pic", memberId);
		model.addAttribute("attsum", attsum);
		model.addAttribute("hostsum", hostsum);
		model.addAttribute("fc",FunctionColumn);
		System.out.println(FunctionColumn);

		return "personal.success";
	}

}
