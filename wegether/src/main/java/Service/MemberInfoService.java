package Service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import model.BlacklistBean;
import model.FriendBean;
import model.MemberBean;
import model.TrackmemberBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.TrackmemberDAO;

@SuppressWarnings("unchecked")
@Service
public class MemberInfoService {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private TrackmemberDAO trackmemberDAO;
	@Autowired
	private BlacklistDAO blacklistDAO;
	
	public JSONArray searchFreindApply(int id, int first) {
		List<FriendBean> friendBeans = 	friendDAO.selectByMemberState(id, 0,0);
		if(friendBeans.size() > 0) {
			JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
			friendBeans.forEach(x->{
				JSONObject row = createRow(x.getMemberFBean());
				row.put("id", x.getId());
				result.add(row);
			});
			return result;
		}
		return null;
	}

	public JSONArray searchFreindApplyed(int id, int first) {
		List<FriendBean> friendBeans = 	friendDAO.selectByMemberFState(id, 0, first);
		if(friendBeans.size() > 0) {
			JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
			friendBeans.forEach(x->{
				JSONObject row = createRow(x.getMemberBean());
				row.put("id", x.getId());
				result.add(row);
			});
			return result;
		} 
		return null;
	}

	
	public JSONArray searchFreind(int id, int first) {
		List<FriendBean> friendBeans = 	friendDAO.selectByMemberState(id, 1,first);
		if(friendBeans.size() > 0) {
			JSONArray result = (JSONArray)applicationContext.getBean("newJsonArray");
			friendBeans.forEach(x->{
				JSONObject row = createRow(x.getMemberFBean());
				row.put("id", x.getId());
				result.add(row);
			});
			return result;
		}
		return null;
	}

	public JSONArray searchTrackmember(int id, int first) {
		List<TrackmemberBean> trackmemberBeans = trackmemberDAO.selectByFan(id);
		if (trackmemberBeans.size() > 0) {
			JSONArray result = (JSONArray) applicationContext.getBean("newJsonArray");
			trackmemberBeans.forEach(x -> {
				JSONObject row = createRow(x.getMemberByMemberid());
				result.add(row);
			});
			return result;
		} 
		return null;
	}

	public JSONArray searchBlacklist(int id, int first) {
		JSONArray result = (JSONArray) applicationContext.getBean("newJsonArray");
		List<BlacklistBean> blacklistBeans = blacklistDAO.selectByMember(id);

		if (blacklistBeans.size() > 0) {
			blacklistBeans.forEach(x -> {
				JSONObject row = createRow(x.getMemberByBlackid());
				result.add(row);
			});
			return result;
		} 
		return null;
	}
	
	private JSONObject createRow(MemberBean bean) {
		JSONObject row = (JSONObject)applicationContext.getBean("newJson");
		row.put("nickname", bean.getNickname());
		row.put("photoSrc","/wegether/member/photo/"+bean.getId());
		row.put("memberid",bean.getId());
		return row;
	}

}
