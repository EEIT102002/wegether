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
import model.ArticleBean;
import model.dao.ActivityDAO;
import model.dao.ArticleDAO;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;

@RestController
public class indexSelectPoController {
	@Autowired
	ArticleDAO articleDAO;
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
	private int cn;
	// 先拿到 PO文 table中的 活動ID 再去 搜尋 活動table 前端搜尋條件 依樣下在活動table這
	
	@GetMapping( path= {"/select_po"}, produces= {"application/json;charset=UTF-8"})
	public ResponseEntity<?> getSelectPoInfo(String state_po, String cityselect_name_po, String start_date_name_po, String end_date_name_po, String type_select_name_po, String keyword_search_input_name_po){	
		
		
		System.out.println("state:"+state_po);
		System.out.println("start_date_name:"+start_date_name_po);
		System.out.println("end_date_name:"+end_date_name_po);
		System.out.println("type_select_name:"+type_select_name_po);
		System.out.println("keyword_search_input_name:"+keyword_search_input_name_po);
		System.out.println("cityselect_name:"+cityselect_name_po);
		
		if(cityselect_name_po==""&&start_date_name_po==""&&end_date_name_po==""&&type_select_name_po==null&&keyword_search_input_name_po =="") {
			List<String> result_fail = new ArrayList<>();
			result_fail.add("查無符合資料");
			System.out.println("if失敗");
			return new ResponseEntity<List<String>>(result_fail, HttpStatus.OK);
		}else {

		int st=Integer.parseInt(state_po);		
		if(cityselect_name_po=="") {
			cityselect_name_po="0";
			cn = Integer.parseInt(cityselect_name_po);
		}else {
			 cn = Integer.parseInt(cityselect_name_po);
		}
		
		List<Integer> resultForActid = articleDAO.selectAllForActid();
//		System.out.println(resultForActid.size());
		List<ActivityBean> result = activityDAO.selectOfIndexPo(st, cn, start_date_name_po, end_date_name_po, type_select_name_po, keyword_search_input_name_po,resultForActid);
		List<Object[]> resultlist = new ArrayList<>();
		if(result.size()>0) {
			result.forEach(bean->{
			Object[] obj = new Object[3];
	 	    StringBuilder sb = new StringBuilder();
	 		sb.append("data:image/jpg;base64,");
	 		sb.append(
	 				org.apache.commons.codec.binary.StringUtils.newStringUtf8(
						org.apache.commons.codec.binary.Base64.encodeBase64(	 										bean.getPicture(), false)));				
							sessionFactory.getCurrentSession().evict(bean);
				obj[2] = bean.getMemberBean().getNickname();
				bean.setMemberBean(null);
				bean.setArticleBean(null);
				bean.setPictureBean(null);
				bean.setAttendBean(null);
				bean.setInviteBean(null);
				
				System.out.println(bean+"<br>");
				obj[0]= bean;
				obj[1]=sb.toString();
				resultlist.add(obj);
				System.out.println("if成功");
				});//forEach end
				return new ResponseEntity<List<Object[]>>(resultlist, HttpStatus.OK);
			}//result.size() end
		else {
				List<String> result_fail = new ArrayList<>();
				result_fail.add("查無符合資料");
				System.out.println("if失敗");
				return new ResponseEntity<List<String>>(result_fail, HttpStatus.OK);
			}// sub_else end
		}//else end
	}//getSelectInfo end
}//indexSelectPoController end