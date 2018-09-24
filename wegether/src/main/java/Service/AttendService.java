package Service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import model.AttendBean;
import model.MemberBean;
import model.dao.AttendDAO;

@Service
@SuppressWarnings("unchecked")
public class AttendService {
	@Autowired
	private AttendDAO attendDAO;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ApplicationContext applicationContext;

	public AttendBean apply(int activityid, int memberid, String answer) {
		return insert(activityid, memberid, answer, 0);
	}

	public AttendBean invite(int activityid, int memberid) {
		return insert(activityid, memberid, "default", 3);
	}

	private AttendBean insert(int activityid, int memberid, String answer, int state) {
		AttendBean bean = new AttendBean();
		bean.setActivityid(activityid);
		bean.setMemberid(memberid);
		bean.setForm(answer);
		bean.setState(state);
		return attendDAO.insert(bean);
	}

	public AttendBean respond(int attendid, int hostid, int state) {
		AttendBean attendBean = attendDAO.select(attendid);
		if (attendBean != null && activityService.checkHost(hostid, attendBean.getActivityid())
				&& attendBean.getState() == 0) {
			attendBean.setState(state);
			return attendBean;
		}
		return null;
	}

	public AttendBean respondInvite(int attendid, int memberid, int state) {
		AttendBean attendBean = attendDAO.select(attendid);
		return respondInviteCheck(attendBean, memberid, state);
	}
	
	public AttendBean respondInviteByActivity(int activityid, int memberid, int state) {
		AttendBean attendBean = attendDAO.selectByActivityAndMember(activityid, memberid);
		return respondInviteCheck(attendBean, memberid, state);
	}
	
	private AttendBean respondInviteCheck(AttendBean attendBean, int memberid, int state) {
		if (attendBean != null && memberid == attendBean.getMemberid() && attendBean.getState() == 3) {
			attendBean.setState(state);
			return attendBean;
		}
		return null;
	}

	public List<AttendBean> unrespondApplys(int activityid, int memberid) {
		if (activityService.checkHost(memberid, activityid)) {
			return attendDAO.selectByActivityAndState(activityid, 0);
		}
		return null;
	}

	public List<AttendBean> inviteApplys(int activityid, int memberid) {
		if (activityService.checkHost(memberid, activityid)) {
			return attendDAO.selectByActivityAndState(activityid, 3);
		}
		return null;
	}

	public List<AttendBean> acceptApplys(int activityid, int memberid) {
		if (activityService.checkHost(memberid, activityid)) {
			return attendDAO.selectByActivityAndState(activityid, 1);
		}
		return null;
	}

	public List<AttendBean> rejectApplys(int activityid, int memberid) {
		if (activityService.checkHost(memberid, activityid)) {
			return attendDAO.selectByActivityAndState(activityid, 2);
		}
		return null;
	}

	public JSONArray attendToJsons(List<AttendBean> beans) {
		JSONArray result = (JSONArray) applicationContext.getBean("newJsonArray");
		beans.forEach(x -> {
			JSONObject row = rowAddAttendInfo(x);
			result.add(row);
		});
		return result;
	}

	public JSONObject attendToJson(AttendBean bean) {
		JSONObject row = (JSONObject) applicationContext.getBean("newJson");
		row.put("rank1", bean.getRank1());
		row.put("rank2", bean.getRank2());
		row.put("rank3", bean.getRank3());
		return row;
	}
	
	public JSONObject getAttend(int attendid, int memberid) {
		AttendBean bean = attendDAO.select(attendid);
		if(memberid == bean.getActivityBean().getHostid()) {
			return rowAddAttendInfo(bean);
		}
		return null;
	}
	
	private JSONObject rowAddAttendInfo(AttendBean bean) {
		JSONObject row = createRow(bean.getMemberBean());
		row.put("attendid", bean.getId());
		row.put("applyForm", bean.getForm());
		return row;
	}
	
	private JSONObject createRow(MemberBean bean) {
		JSONObject row = (JSONObject) applicationContext.getBean("newJson");
		row.put("nickname", bean.getNickname());
		row.put("memberid", bean.getId());
		return row;
	}

	public AttendBean attendCheck(int activityid, int memberid) {
		AttendBean bean = attendDAO.selectByActivityAndMember(activityid, memberid);
		return bean;
	}

	public boolean cancelAttend(Integer attendid, Integer memberid) {
		AttendBean attendBean = attendDAO.select(attendid);
		if (attendBean != null
				&& ((attendBean.getMemberid() == memberid && (attendBean.getState() == 0 || attendBean.getState() == 1))
						|| (attendBean.getState() == 3 && attendBean.getActivityBean().getHostid() == memberid))) {

			return attendDAO.delete(attendid);
		}
		return false;
	}

	public boolean cancelAttendByActivity(Integer activityid, Integer memberid) {
		AttendBean attendBean = attendDAO.selectByActivityAndMember(activityid, memberid);
		if (attendBean != null && attendBean.getMemberid() == memberid
				&& (attendBean.getState() == 0 || attendBean.getState() == 1)) {
			return attendDAO.delete(attendBean.getId());
		}
		return false;
	}
}
