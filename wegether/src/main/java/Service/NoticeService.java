package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.MemberBean;
import model.NoticeBean;
import model.dao.MemberDAO;
import model.dao.NoticeDAO;

@Service
public class NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private MemberDAO memberDAO;

	public List<NoticeBean> getNotice(Integer id, Integer ntype) {
		switch (ntype) {
			case 1:
			case 2:
			// friend
				return noticeDAO.selectByFriendId(id, ntype);
			case 3:
			// invite
				return noticeDAO.selectByInviteId(id, ntype);
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				// attend
				return noticeDAO.selectByAttendId(id, ntype);
			case 10:
			case 15:
				// article
				return noticeDAO.selectByArticleId(id, ntype);
			case 11:
			case 12:
			case 13:
			case 14:
				// activity
				return noticeDAO.selectByActivityId(id, ntype);
		}
		return null;
	}
	
	public Integer unreadCount(Integer memberid) {
		MemberBean bean = memberDAO.select(memberid);
		if(bean != null) {
			return bean.getNotices();
		}
		return null;
	}

	public boolean clearNotices(Integer memberid) {
		MemberBean bean = memberDAO.select(memberid);
		if(bean != null) {
			bean.setNotices(0);
			return true;
		}
		return false;
	}
	
	public List<NoticeBean> selectNotices(int memberid, int first) {
		return  noticeDAO.selectByMemberId(memberid, first);
	}
	
	public Integer noticesCount(Integer memberid) {
		return noticeDAO.selectCountByMemberId(memberid);
	}
}
