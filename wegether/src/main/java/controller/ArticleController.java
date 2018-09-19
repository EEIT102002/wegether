package controller;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.ArticleBean;
import model.MsgBean;
import model.PictureBean;
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
		
//		if(bean.getContent()!=null && bean.getContent().length()!=0 ) {
//			if(bean.getContent().startsWith("deleteMsgId")) {
//				articleDAO.delete(Integer.parseInt(bean.getContent().substring(12)));
//				System.out.println("GOOD");
//			}
//		}
		
		List<Object[]> articleList = new ArrayList<>();
		List<ArticleBean> articleBeans = articleDAO.selectByActivityId(bean.getActivityid());
		if (articleBeans.size() != 0) {
			System.out.println("articleBeans.size()="+articleBeans.size());
			articleBeans.forEach(art -> {
				Calendar arttime = Calendar.getInstance();
				Object[] obj = new Object[7];
				obj[0] = art.getMemberid();				
			
				String picMemStr;
				try {
					picMemStr = PictureConvert
							.convertBase64Image(memberDAO.select(art.getMemberid()).getPhoto());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					picMemStr = null;
				}
				
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
				
				List<Integer> picArticleId = new ArrayList<Integer>();
				List<PictureBean>  picArticle = pictureDAO.selectByArticle(art.getId());
				picArticle.forEach(pic->{
					picArticleId.add(pic.getId());
				});
				obj[6] = picArticleId;

				articleList.add(obj);

			});
			return new ResponseEntity<List<Object[]>>(articleList, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping(path= {"/article.controller/{id}"}, produces= {"application/json"})
	public ResponseEntity<?> delete(@PathVariable int id) throws URISyntaxException{
		System.out.println("@DeleteMapping");
		ArticleBean bean = articleDAO.select(id);
		if(bean!=null) {
			Boolean result = articleDAO.delete(id);
			if(result!=null && result==true) {
				ResponseEntity<?> temp = getInfo(bean);
				System.out.println("@DeleteMapping=true");
				return temp ;
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);	
			}
		}else {
			System.out.println("@DeleteMapping=false");
			return new ResponseEntity(HttpStatus.NO_CONTENT);	
		}
	}
		
	
}
