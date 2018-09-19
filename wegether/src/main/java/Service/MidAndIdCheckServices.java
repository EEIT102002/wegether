package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BlacklistBean;
import model.FriendBean;
import model.ServiceBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;
import model.dao.ServiceDAO;
import model.dao.implement.ServiceDAOHibernate;

@Service
public class MidAndIdCheckServices {
	@Autowired
	private BlacklistDAO blacklistDAO;
	@Autowired
	private FriendDAO friendDAO;

	public int check(Integer id,Integer memberId) {
		int memberid=memberId;
		int status =0;// 狀態 1好友2非好友3黑名單4非會員未登入5自己
		System.out.println("login=" + id);
		System.out.println("member=" + memberid);

		// 判斷loginid與memberid(要查看的個人頁面)狀態關系
		// 檢查login狀態
		if (id == null) {
			System.out.println("未登入狀態4");
			status = 4;
		} else if (id == memberid) {
			status = 5;
			System.out.println("自己狀態5");
		} else if (id != null && id != memberid) {
			// 判斷是否在觀看者的黑名單上
			BlacklistBean blacklistBean = blacklistDAO.selectByMemberidAndBlackid(id, memberid);
			if (blacklistBean != null) {
				System.out.println("黑名單狀態3");
				status = 3;
			} else {
				// 判斷是否為好友
				FriendBean result = friendDAO.selectByMidAndFriendid(memberid, id);
				FriendBean result2 = friendDAO.selectByMidAndFriendid(id, memberid);
				if (result != null || result2 != null) {
					System.out.println("好友狀態1");
					status = 1;
					System.out.print("好友");
				} else {
					System.out.println("非好友狀態2");
					status = 2;
				}
			}
		}		
		
		
		return status;
	}

	
}
