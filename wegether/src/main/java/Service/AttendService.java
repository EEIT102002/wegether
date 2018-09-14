package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AttendBean;
import model.dao.AttendDAO;

@Service
public class AttendService {
	@Autowired
	private AttendDAO attendDAO;
	
	public AttendBean attend(int activityid, int memberid, String answer) {
		AttendBean bean = new AttendBean();
		bean.setActivityid(activityid);
		bean.setMemberid(memberid);
		bean.setForm(answer);
		bean.setState(0);
		return attendDAO.insert(bean);
	}
}
