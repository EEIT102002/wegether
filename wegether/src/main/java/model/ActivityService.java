package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.dao.ActivityDAO;

public class ActivityService {

	@Autowired
	private ActivityDAO activityDAO;
	
	public List<ActivityBean> select(ActivityBean bean) {
		List<ActivityBean> result = null;
		if(bean!=null&& bean.getId()!=0) {
			ActivityBean temp=activityDAO.select(bean.getId());
			if(temp!=null) {
				result = new ArrayList<ActivityBean>();
				result.add(temp);
			}
		}else {
			result=activityDAO.select();
		}
		return result;
	}
	
	
}
