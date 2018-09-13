package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BlacklistBean;
import model.BlacklistId;
import model.dao.BlacklistDAO;

@Service
public class BlackListService {
	@Autowired
	private BlacklistDAO blacklistDAO;

	public BlacklistBean insert(int bid,int mid) {
		BlacklistBean result = null;

		if (mid != bid && bid != 0) {
			BlacklistId id = new BlacklistId();// NEW TrackmemberBean 來放TrackmemberId id
			id.setBlackid(bid);
			id.setMemberid(mid);
			System.out.println("bid="+bid);
			System.out.println("mid="+mid);
			BlacklistBean bean = new BlacklistBean();
			bean.setId(id);						//把id加入BlacklistBean bean中

			// 寫入資料
			try {
				result = blacklistDAO.insert(bean);// 加黑名單
			} catch (Exception e) {

			}
			
		}
		return result;
	}

	public boolean delete(int bid,int mid) {
		boolean result = false;
		
		if (bid != mid) {
			BlacklistId id = new BlacklistId();// NEW TrackmemberBean 來放TrackmemberId id
			id.setBlackid(bid);; // 系統抓資料
			id.setMemberid(mid);;
			BlacklistBean bean = new BlacklistBean();// NEW TrackmemberBean 來放TrackmemberId id
			bean.setId(id);;

			// 寫入資料
			try {
				result = blacklistDAO.delete(bean);// 取消追蹤
			} catch (Exception e) {

			}
		}
		return result;
	}
}
