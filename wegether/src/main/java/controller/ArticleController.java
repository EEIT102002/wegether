package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import model.ArticleBean;
import model.MsgBean;
import model.dao.ActivityDAO;
import model.dao.ArticleDAO;
import model.dao.MemberDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;

//@Controller
@RestController
public class ArticleController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PictureDAO pictureDAO;
	
	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private ArticleDAO articleDAO;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@GetMapping( path= {"/article.controller"}, produces= {"application/json"})
	public ResponseEntity<?> getInfo(ArticleBean bean){
		System.out.println("ArticleBean="+bean);
		
//		if(bean.getContent()!=null && bean.getContent().length()!=0 && !bean.getContent().equals("對聚會有任何疑問嘛？留個言吧！") ) {
//			if(bean.getContent().startsWith("deleteMsgId")) {
//				msgDAO.delete(Integer.parseInt(bean.getContent().substring(12)));
//			}else {
//				java.util.Date now = new java.util.Date();
//				bean.setMsgtime(now);
//				Boolean result = msgDAO.insert(bean);
//			}
//		}
		
		List<Object[]> articleList = new ArrayList<>();
		List<ArticleBean> articleBeans = articleDAO.selectByActivityId(bean.getActivityid());
		if (articleBeans.size() != 0) {
			System.out.println("articleBeans.size()="+articleBeans.size());
			articleBeans.forEach(art -> {
				Calendar arttime = Calendar.getInstance();
				Object[] obj = new Object[6];
				obj[0] = art.getMemberid();				
			
				String picMemStr = PictureConvert
						.convertBase64Image(pictureDAO.selectByMember(art.getMemberid()).get(0).getPicture());
				obj[1] = picMemStr;
				obj[2] = memberDAO.select(art.getMemberid()).getNickname();
				
				arttime.setTime(art.getCreatetime());
				int artMonth = arttime.get(Calendar.MONTH) + 1;
				int artDay = arttime.get(Calendar.DAY_OF_MONTH);
				int artHour = arttime.get(Calendar.HOUR_OF_DAY);
				String artHourStr = Integer.toString(artHour);
			
				if (artHour < 10)
					artHourStr = "0" + artHourStr;

				int artMinute = arttime.get(Calendar.MINUTE);
				String artMinuteStr = Integer.toString(artMinute);
				if (artMinute < 10)
					artMinuteStr = "0" + artMinuteStr;

				String arttimeStr = artMonth + " 月 " + artDay + " 日  " + artHourStr + " : " + artMinuteStr;
				obj[3] = arttimeStr;
				obj[4] = art.getContent();
				obj[5] = art.getId();
				System.out.println("artID="+art.getId());
				articleList.add(obj);

			});
			return new ResponseEntity<List<Object[]>>(articleList, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
