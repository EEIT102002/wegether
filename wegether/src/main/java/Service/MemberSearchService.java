package Service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import model.MemberInfoBean;
import model.dao.MemberInfoDAO;

@Service
public class MemberSearchService {
	@Autowired
	private MemberInfoDAO memberInfoDAO;
	@Autowired
	private ApplicationContext applicationContext;

	public JSONArray searchByNickname(String nickname) {		
		return createJSONArray(memberInfoDAO.selectByNickname(nickname));
	}
	public JSONArray searchByNicknameForFriend(String nickname, int id) {
		System.out.println("service nickname: "+nickname);
		System.out.println("id: "+id);
		return createJSONArray(memberInfoDAO.selectByNicknameForFriend(nickname,id));
	}
	
	public JSONArray searchByNicknameForTrack(String nickname, int id) {
		return createJSONArray(memberInfoDAO.selectByNicknameForTrack(nickname,id));
	}
	
	public JSONArray searchByNicknameForBlacklist(String nickname, int id) {
		return createJSONArray(memberInfoDAO.selectByNicknameForBlacklist(nickname,id));
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray createJSONArray(List<MemberInfoBean> beans) {
		if (beans.size() > 0) {
			JSONArray result = (JSONArray) applicationContext.getBean("newJsonArray");
			beans.forEach(x -> {
				JSONObject row = createRow(x);
				result.add(row);
			});
			return result;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject createRow(MemberInfoBean bean) {
		JSONObject row = (JSONObject)applicationContext.getBean("newJson");
		row.put("nickname", bean.getNickname());
		row.put("photo",bean.getId());
		row.put("memberid",bean.getId());
		return row;
	}
}
