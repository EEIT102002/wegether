package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.ActivityBean;
import model.dao.ActivityDAO;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;

@RestController
public class indexSelectController {
	@Autowired
	ActivityDAO activityDAO;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	MemberInfoDAO memberInfoDAO;
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	SettingDAO settingDAO;
	@Autowired
	SessionFactory sessionFactory;

	@GetMapping(path = { "/select_activity" }, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<?> getSelectInfo(String state, String cityselect_name, String start_date_name,
			String end_date_name, String type_select_name, String keyword_search_input_name) {
		// 城市變數字
		// 狀態活動1 心得2
		// 後端接值
		// 將結果傳回前端
//		System.out.println("state:"+state);
//		System.out.println("start_date_name:"+start_date_name);
//		System.out.println("end_date_name:"+end_date_name);
//		System.out.println("type_select_name:"+type_select_name);
//		System.out.println("keyword_search_input_name:"+keyword_search_input_name);
//		System.out.println("cityselect_name:"+cityselect_name);
		
		if(cityselect_name==""&&start_date_name==""&&end_date_name==""&&type_select_name==null&&keyword_search_input_name =="") {
			List<String> result_fail = new ArrayList<>();
			result_fail.add("查無符合資料");
			System.out.println("if失敗");
			return new ResponseEntity<List<String>>(result_fail, HttpStatus.OK);
		}else {
			Integer cn = null;
			int st = Integer.parseInt(state);
			if (cityselect_name == "") {
				cn = 0;
			} else {
				cn = Integer.parseInt(cityselect_name);
			}
			List<ActivityBean> result = activityDAO.selectOfIndex(st, cn, start_date_name, end_date_name, type_select_name,
					keyword_search_input_name);
			List<Object[]> resultlist = new ArrayList<>();
			if (result.size() > 0) {
				result.forEach(bean -> {
					Object[] obj = new Object[5];	
//					StringBuilder sb = new StringBuilder();
//					sb.append("data:image/jpg;base64,");
//					sb.append(org.apache.commons.codec.binary.StringUtils
//							.newStringUtf8(org.apache.commons.codec.binary.Base64.encodeBase64(bean.getPicture(), false)));
					sessionFactory.getCurrentSession().evict(bean);
					obj[0] = bean;
					obj[1] = "/wegether/activity/photo/"+bean.getId();
					obj[2] = bean.getMemberBean().getNickname();
					obj[3] = "/wegether/member/photo/"+bean.getMemberBean().getId();
					obj[4] = bean.getMemberBean().getId();
					bean.setMemberBean(null);
					bean.setArticleBean(null);
					bean.setPictureBean(null);
					bean.setAttendBean(null);
					bean.setInviteBean(null);
					bean.setPicture(null);
					resultlist.add(obj);
				});

				return new ResponseEntity<List<Object[]>>(resultlist, HttpStatus.OK);
			} else {
				List<String> result_fail = new ArrayList<>();
				result_fail.add("查無符合資料");
				System.out.println("if失敗");
				return new ResponseEntity<List<String>>(result_fail, HttpStatus.OK);

			}
		}
		
	}// end
}