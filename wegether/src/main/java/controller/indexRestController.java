package controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.ActivityBean;
import model.BlacklistBean;
import model.FriendBean;
import model.MemberBean;
import model.MemberInfoBean;
import model.SettingBean;
import model.TrackmemberBean;
import model.dao.ActivityDAO;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;
import model.dao.TrackmemberDAO;
import model.dao.implement.ActivityDAOHibernate;
@RestController
public class indexRestController {
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
	
	@GetMapping( path= {"/activity"}, produces= {"application/json"})
	public ResponseEntity<?> getInfo(){
	
		List<ActivityBean> result = activityDAO.selectAllState();
//		System.out.println(result);
		List<Object[]> resultlist = new ArrayList<>();
		if(result.size()>0) {
			result.forEach(bean->{
			
				Object[] obj = new Object[3];
				StringBuilder sb = new StringBuilder();
				sb.append("data:image/jpg;base64,");
				sb.append(org.apache.commons.codec.binary.StringUtils
						.newStringUtf8(org.apache.commons.codec.binary.Base64.encodeBase64(bean.getPicture(), false)));
				sessionFactory.getCurrentSession().evict(bean);
				obj[0] = bean;
				obj[1] = sb.toString();
				obj[2] = bean.getMemberBean().getNickname();
				// System.out.println(bean.getMemberBean().getNickname());
				bean.setMemberBean(null);
				bean.setArticleBean(null);
				bean.setPictureBean(null);
				bean.setAttendBean(null);
				bean.setInviteBean(null);
				
	

				resultlist.add(obj);
			});
			return new ResponseEntity<List<Object[]>>(resultlist, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}