package controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.MsgBean;
import model.dao.ActivityDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import pictureconvert.PictureConvert;
import pictureconvert.TimeConvert;

@RestController
public class ArticleMsgsController {
	@Autowired
	private MsgDAO msgDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private ActivityDAO activityDAO;



	@GetMapping( path= {"/articlemsgs.controller/{articleid}"}, produces= {"application/json"})
	public ResponseEntity<?> getInfo(
			@PathVariable(name="articleid", required = false) Integer articleid
			){
//				System.out.println("activityid="+id);
		List<Object[]> msgsList = new ArrayList<>();
		List<MsgBean> msgBeans = msgDAO.selectByArticle(articleid);
		
		if (msgBeans.size() != 0) {
			System.out.println("5");
			msgBeans.forEach(msg -> {
				System.out.println("6");
				System.out.println("GetmsgBean:"+msg);
				Object[] obj = new Object[5];
				obj[0] = msg.getMemberid();				
				obj[1] = memberDAO.select(msg.getMemberid()).getNickname();
				obj[2] = TimeConvert.timeConvertString(msg.getMsgtime());
				obj[3] = msg.getContent();
				obj[4] = msg.getId();
				msgsList.add(obj);

			});
			return new ResponseEntity<List<Object[]>>(msgsList, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping( path= {"/articlemsgs.controller"}, produces= {"application/json"})
	public ResponseEntity<?> insertInfo(MsgBean bean)throws URISyntaxException {
		ResponseEntity<?> temp = null;
		System.out.println("PostmsgBean:"+bean);
		
		if(bean.getContent()!=null && bean.getContent().length()!=0 && 
				 !bean.getContent().equals("對聚會有任何疑問嘛？留個言吧！") ) {

				java.util.Date now = new java.util.Date();
				bean.setMsgtime(now);
				Boolean result = msgDAO.insert(bean);
				System.out.println("1");
				if(result!=null && result==true) {
					System.out.println("2");
					temp = getInfo(bean.getArticleid());
					System.out.println("3");
					return temp ;
				} else {
					return new ResponseEntity(HttpStatus.NO_CONTENT);			
				}
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	
	@DeleteMapping(path= {"/articlemsgs.controller/{id}"}, produces= {"application/json"})
	public ResponseEntity<?> delete(@PathVariable int id) throws URISyntaxException{
		System.out.println("@DeleteMapping");
		MsgBean bean = msgDAO.selectById(id);
		if(bean!=null) {
			Boolean result = msgDAO.delete(id);
			if(result!=null && result==true) {
				ResponseEntity<?> temp = getInfo(bean.getArticleid());
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
