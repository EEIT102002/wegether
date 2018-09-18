package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ActivityBean;
import model.AttendBean;
import model.dao.ActivityDAO;
import model.dao.AttendDAO;

@Service
public class AttendService {
	@Autowired
	private AttendDAO attendDAO;
	
	@Autowired
	private ActivityDAO activityDAO;
	
	private Integer attNo = 0;
	
	public AttendBean attend(int activityid, int memberid, String answer) {
		AttendBean bean = new AttendBean();
		bean.setActivityid(activityid);
		bean.setMemberid(memberid);
		bean.setForm(answer);
		bean.setState(0);
		return attendDAO.insert(bean);
	}
	
	public Map<String, Integer> attendCheck(int activityid, int memberid) {
		ActivityBean bean = activityDAO.selectId(activityid);
		Map<String, Integer> resultMap = new HashMap<String,Integer>();
		if(bean.getHostid()==memberid) {
			resultMap.put("attNo",1);
		}else {
			List<AttendBean> result = attendDAO.selectByActID(activityid);
			result.forEach(att->{
				if(att.getMemberid()==memberid) {		
					resultMap.put("state",att.getState());
					resultMap.put("attNo",2);
					attNo=2;
				}else if(attNo!=2){
					resultMap.put("attNo",3);
				}
			});
		}
		return resultMap;
	}
	
	
}
