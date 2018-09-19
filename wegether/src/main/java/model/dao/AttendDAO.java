package model.dao;

import java.util.List;

import model.AttendBean;
import model.FriendBean;

public interface AttendDAO {
	public abstract AttendBean select(int id);
	public abstract AttendBean selectByActivityAndMember(int activityid, int memberid);
	public abstract List<AttendBean> selectBymemberid(int memberid);
	public abstract List<AttendBean> select();
	public abstract List<AttendBean> selectByActID(int actID);
	public abstract List<AttendBean> selectByMemID(int memID);
	public abstract AttendBean insert(AttendBean bean);
	public abstract AttendBean update(AttendBean bean);
	public abstract boolean delete(int id);
	public abstract List<AttendBean> selectByActivityAndState(int activityid , int state);
	public abstract Integer selectCountByActivityAndState(int activityid, int state);
		
}
