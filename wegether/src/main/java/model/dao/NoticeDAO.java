package model.dao;

import java.util.List;

import model.NoticeBean;

public interface NoticeDAO {
	public abstract List<NoticeBean> selectByMemberId(Integer id, Integer first);
	public abstract List<NoticeBean> selectByActivityId(Integer id, Integer ntype);
	public abstract List<NoticeBean> selectByArticleId(Integer id, Integer ntype);
	public abstract List<NoticeBean> selectByFriendId(Integer id, Integer ntype);
	public abstract List<NoticeBean> selectByInviteId(Integer id, Integer ntype);
	public abstract List<NoticeBean> selectByAttendId(Integer id, Integer ntype);
	public abstract Integer selectCountByMemberId(Integer memberid);
	public abstract NoticeBean select(int id);
	public abstract boolean delete(int id);
	public abstract void delete(NoticeBean bean);
	public abstract void deleteByMember(int memberid);

}
