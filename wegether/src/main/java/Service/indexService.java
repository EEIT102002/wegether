package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ActivityBean;
import model.dao.ActivityDAO;


@Service
public class indexService {
	@Autowired
	public ActivityDAO activityDAO;
	
	public List<ActivityBean> showDefault() {
		List<ActivityBean> bean = activityDAO.selectAll();
		
		return bean;
	}
}
