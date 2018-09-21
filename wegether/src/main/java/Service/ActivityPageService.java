package Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ActivityBean;
import model.AttendBean;
import model.MemberBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.AttendDAO;
import model.dao.MemberDAO;
import model.dao.MsgDAO;
import model.dao.PictureDAO;
import pictureconvert.TimeConvert;

@Service
public class ActivityPageService {
	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private PictureDAO pictureDAO;

	@Autowired
	private AttendDAO attendDAO;

	@Autowired
	private MsgDAO msgDAO;

	
	
	public ActivityBean selectActBean(int activityid) {
		return activityDAO.selectId(activityid);
	}

	public Map<String, String> activityTime(int activityid) {
		
		Map<String, String> activityTime = new HashMap<String, String>();
		ActivityBean actBean = activityDAO.selectId(activityid);
		
		String actbegin = null;
		if (actBean.getActbegin() != null) {
			actbegin = TimeConvert.timeConvertString(actBean.getActbegin());
		}
		activityTime.put("actbegin", actbegin);
		
		String dateline = null;
		if (actBean.getDateline() != null) {
			dateline = TimeConvert.dateline(actBean.getDateline());
		}
		activityTime.put("dateline", dateline);

		return activityTime;
	}
	
	
	public List<Integer> actPicList(int activityid){
	
		List<Integer> actPicList = new ArrayList<>();
		List<PictureBean> actPicBeans = pictureDAO.selectByActivity(activityid);
		actPicBeans.forEach(pic->{
			actPicList.add(pic.getId());
		});

		return actPicList;
	}
	
	public MemberBean hostBean(int activityid) {
			return memberDAO.
					select(activityDAO.selectId(activityid).getHostid());
	}
	
	
	public 	List<Integer> attBeans(int activityid){
		List<Integer> attMemberList = new ArrayList<>();
		List<AttendBean> result = attendDAO.selectByActID(activityid);
		result.forEach(att->{
			attMemberList.add(att.getMemberid());
		});
		return attMemberList;
	}
	
}
