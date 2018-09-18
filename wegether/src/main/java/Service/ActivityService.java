package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ActivityBean;
import model.dao.ActivityDAO;

@Service
public class ActivityService {
	@Autowired
	ActivityDAO activityDAO;
	
	public boolean checkHost(int memberid, int activityid) {
		ActivityBean bean = activityDAO.selectId(activityid);
		if(bean != null && bean.getHostid() == memberid) {
			return true;
		}
		return false;
	}
}
