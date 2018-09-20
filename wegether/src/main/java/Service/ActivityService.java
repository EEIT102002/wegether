package Service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import model.ActivityBean;
import model.AttendBean;
import model.MemberBean;
import model.dao.ActivityDAO;
import model.dao.AttendDAO;

@Service
@SuppressWarnings("unchecked")
public class ActivityService {
	@Autowired
	ActivityDAO activityDAO;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private AttendDAO attendDAO;

	public boolean checkHost(int memberid, int activityid) {
		ActivityBean bean = activityDAO.selectId(activityid);
		if (bean != null && bean.getHostid() == memberid) {
			return true;
		}
		return false;
	}

	public JSONArray hostNow(int hostid) {
		return toJsons(activityDAO.selectByHostNow(hostid));
	}

	public JSONArray hostHistory(int hostid) {
		return toJsons(activityDAO.selectByHostHistory(hostid));
	}

	public JSONArray attendNow(int memberid) {
		return toJsons(activityDAO.selectByAttendMemberStateNow(memberid, 1));
	}

	public JSONArray attendHistory(int memberid) {
		return toJsons(activityDAO.selectByAttendMemberStateHistory(memberid, 1));
	}

	public JSONArray applying(int memberid) {
		return toJsons(activityDAO.selectByAttendMemberStateNow(memberid, 0));
	}
	
	public JSONArray beInvited(int memberid) {
		return toJsons(activityDAO.selectByAttendMemberStateNow(memberid, 3));
	}

	public JSONArray toJsons(List<ActivityBean> beans) {
		JSONArray result = (JSONArray) applicationContext.getBean("newJsonArray");
		beans.forEach(x -> {
			JSONObject row = createRow();
			row.put("activityid", x.getId());
			row.put("title", x.getTitle());
			row.put("actbegin", x.getActbegin());
			row.put("accepts", attendDAO.selectCountByActivityAndState(x.getId(), 1));
			row.put("unresponds", attendDAO.selectCountByActivityAndState(x.getId(), 0));
			row.put("hostid", x.getHostid());
			row.put("city", x.getCity());
			row.put("addr", x.getAddr());
			row.put("hostname", x.getMemberBean().getNickname());
			result.add(row);
		});
		return result;
	}

	private JSONObject createRow() {
		JSONObject row = (JSONObject) applicationContext.getBean("newJson");
		return row;
	}
}
