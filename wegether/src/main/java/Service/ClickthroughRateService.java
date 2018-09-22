package Service;

import java.util.List;

import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ActivityBean;
import model.MemberBean;
import model.NoticeBean;
import model.dao.ActivityDAO;
import model.dao.MemberDAO;
import model.dao.NoticeDAO;

@Service
public class ClickthroughRateService {
	@Autowired
	private ActivityDAO activityDAO;

	public boolean click(Integer actid) {
		Integer temp ;
		ActivityBean activityBean = new ActivityBean();
		activityBean = activityDAO.selectId(actid);
		temp = activityBean.getClick()+1;
		activityBean.setClick(temp);
		ActivityBean result = activityDAO.update(activityBean);
		System.out.println("點閱率+1");
		if(result!=null) {
			return true;
		}else
		return false;
	}
}
