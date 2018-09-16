package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.ActivityBean;
import model.MsgBean;
import model.dao.ActivityDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;

//@Controller
@RestController
public class MsgsController {
	@Autowired
	private MsgDAO msgDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PictureDAO pictureDAO;
	
	@Autowired
	private ActivityDAO activityDAO;


	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@GetMapping( path= {"/msgs.controller"}, produces= {"application/json"})
	public ResponseEntity<?> getInfo(MsgBean bean){
		//System.out.println(bean.getContent());
		
		if(bean.getContent()!=null && bean.getContent().length()!=0 && !bean.getContent().equals("對聚會有任何疑問嘛？留個言吧！") ) {
			if(bean.getContent().startsWith("deleteMsgId")) {
				msgDAO.delete(Integer.parseInt(bean.getContent().substring(12)));
			}else {
				java.util.Date now = new java.util.Date();
				bean.setMsgtime(now);
				Boolean result = msgDAO.insert(bean);
			}
		}
		
		List<Object[]> msgsList = new ArrayList<>();
		List<MsgBean> msgBeans = msgDAO.selectByActivity(bean.getActivityid());
		if (msgBeans.size() != 0) {
			msgBeans.forEach(msg -> {
				Calendar msgtime = Calendar.getInstance();
				Object[] obj = new Object[6];
				obj[0] = msg.getMemberid();				
			
				String picMemStr = PictureConvert
						.convertBase64Image(pictureDAO.selectByMember(msg.getMemberid()).get(0).getPicture());
				obj[1] = picMemStr;
				obj[2] = memberDAO.select(msg.getMemberid()).getNickname();
				
				msgtime.setTime(msg.getMsgtime());
				int msgMonth = msgtime.get(Calendar.MONTH) + 1;
				int msgDay = msgtime.get(Calendar.DAY_OF_MONTH);
				int msgHour = msgtime.get(Calendar.HOUR_OF_DAY);
				String msgHourStr = Integer.toString(msgHour);
			
				if (msgHour < 10)
					msgHourStr = "0" + msgHourStr;

				int msgMinute = msgtime.get(Calendar.MINUTE);
				String msgMinuteStr = Integer.toString(msgMinute);
				if (msgMinute < 10)
					msgMinuteStr = "0" + msgMinuteStr;

				String msgtimeStr = msgMonth + " 月 " + msgDay + " 日  " + msgHourStr + " : " + msgMinuteStr;
				obj[3] = msgtimeStr;
				obj[4] = msg.getContent();
				obj[5] = msg.getId();
			//	System.out.println("msgID="+msg.getId());
				msgsList.add(obj);

			});
			return new ResponseEntity<List<Object[]>>(msgsList, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
