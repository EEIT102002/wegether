package controller;

import java.text.SimpleDateFormat;
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
@RestController
public class indexRestController {
	@Autowired
	ActivityDAO activityDAO;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	SettingDAO settingDAO;
	@Autowired
	SessionFactory sessionFactory;
	
	
	
	@GetMapping( path= {"/activity"}, produces= {"application/json"})
	public ResponseEntity<List<ActivityBean>> getInfo(){
			
		List<ActivityBean> result = activityDAO.selectAll();
//		System.out.println(result);

		if(result.size()>0) {
			result.forEach(bean->{

			sessionFactory.getCurrentSession().evict(bean);
			bean.setMemberBean(null);
			bean.setArticleBean(null);
			bean.setPictureBean(null);
			bean.setAttendBean(null);
			bean.setInviteBean(null);
			System.out.println(bean+"<br>");

//			setBe成nullll
			});

			
			return new ResponseEntity<List<ActivityBean>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

}