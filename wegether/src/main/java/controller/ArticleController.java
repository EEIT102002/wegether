package controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.ArticleBean;
import model.MsgBean;
import model.PictureBean;
import model.dao.ArticleDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import model.dao.PictureDAO;
import pictureconvert.TimeConvert;

@RestController
public class ArticleController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PictureDAO pictureDAO;
	
	@Autowired
	private ArticleDAO articleDAO;
	
	@Autowired
	private MsgDAO msgDAO;
	
	
	@GetMapping( path= {"/article.controller/{activityid}"}, produces= {"application/json"})
	public ResponseEntity<?> getInfo(@PathVariable int activityid){
		System.out.println("article.controller.get:"+activityid);
		
		List<Object[]>  articleList = new ArrayList<Object[]>();
		List<ArticleBean> articleBeans = articleDAO.selectByActivityId(activityid);
		if (articleBeans.size() != 0) {
			articleBeans.forEach(art-> {
				Object[] articleObj = new Object[7];
				
				articleObj[0] = art.getMemberid();				
				articleObj[1] = memberDAO.select(art.getMemberid()).getNickname();
				articleObj[2] = TimeConvert.timeConvertString(art.getCreatetime());
				articleObj[3] = art.getContent();
				articleObj[4] = art.getId();
				
				List<Integer> picArticleId = new ArrayList<Integer>();
				List<PictureBean>  picArticle = pictureDAO.selectByArticle(art.getId());
				picArticle.forEach(pic->{
					picArticleId.add(pic.getId());
				});
				articleObj[5] = picArticleId;
				
				List<Object[]> msgBeansObj = new ArrayList<Object[]>();
				List<MsgBean> msgBeans = msgDAO.selectByArticle(art.getId());
				if (msgBeans.size() != 0) {
					msgBeans.forEach(msg-> {
						
						Object[] msgObj = new Object[5];
						msgObj[0] = msg.getMemberid();				
						msgObj[1] = memberDAO.select(msg.getMemberid()).getNickname();
						msgObj[2] = TimeConvert.timeConvertString(msg.getMsgtime());
						msgObj[3] = msg.getContent();
						msgObj[4] = msg.getId();
						msgBeansObj.add(msgObj);
					});
					}
				
				articleObj[6] = msgBeansObj;
				articleList.add(articleObj);
			});
			
			return new ResponseEntity<List<Object[]>>(articleList, HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
}
	
	
	
	@DeleteMapping(path= {"/article.controller/{articleid}"}, produces= {"application/json"})
	public ResponseEntity<?> delete(@PathVariable int articleid) throws URISyntaxException{
		
		ArticleBean bean = articleDAO.select(articleid);
		if(bean!=null) {
			Boolean result = articleDAO.delete(articleid);
			if(result!=null && result==true) {
				ResponseEntity<?> temp = getInfo(bean.getActivityid());
				return temp ;
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);	
			}
			
		}
		return null;
	}
}
