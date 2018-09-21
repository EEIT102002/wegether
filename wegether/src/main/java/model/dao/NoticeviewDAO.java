package model.dao;

import java.util.List;

import model.NoticeviewBean;

public interface NoticeviewDAO {
	public abstract List<NoticeviewBean> selectByMemberId(Integer id, Integer first);
	public abstract List<NoticeviewBean> selectByActivityId(Integer id, Integer ntype);
	public abstract List<NoticeviewBean> selectByArticleId(Integer id, Integer ntype);
	public abstract List<NoticeviewBean> selectByFriendId(Integer id, Integer ntype);
	public abstract List<NoticeviewBean> selectByInviteId(Integer id, Integer ntype);
	public abstract List<NoticeviewBean> selectByAttendId(Integer id, Integer ntype);
}
