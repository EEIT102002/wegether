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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import model.ArticleBean;
import model.MsgBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.ArticleDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;
import pictureconvert.TimeConvert;

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
	
	@Autowired
	private MsgDAO msgDAO;

	
	@GetMapping( path= {"/article.controller/{activityid}"}, produces= {"application/json"})
	public ResponseEntity<?> getInfo(@PathVariable int activityid){
		
		
		List<Object[]> articleList = new ArrayList<>();
		List<ArticleBean> articleBeans = articleDAO.selectByActivityId(activityid);
		if (articleBeans.size() != 0) {
			articleBeans.forEach(art -> {
				Calendar arttime = Calendar.getInstance();
				Object[] obj = new Object[6];
				obj[0] = art.getMemberid();				
				obj[1] = memberDAO.select(art.getMemberid()).getNickname();
				obj[2] = TimeConvert.timeConvertString(art.getCreatetime());
				obj[3] = art.getContent();
				obj[4] = art.getId();
				
				List<Integer> picArticleId = new ArrayList<Integer>();
				List<PictureBean>  picArticle = pictureDAO.selectByArticle(art.getId());
				picArticle.forEach(pic->{
					picArticleId.add(pic.getId());
				});
				obj[5] = picArticleId;

				articleList.add(obj);

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
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);	
		}
	}
		
	
}
