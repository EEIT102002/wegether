package model.dao;

import java.util.List;

import model.AttendBean;

public interface AttendDAO {
	public abstract AttendBean select(int id);

	public abstract List<AttendBean> select();

	public abstract AttendBean insert(AttendBean bean);

	public abstract AttendBean update(Integer activityid, Integer memberid, java.util.Date createtime,
			String form, Integer rank1,	Integer rank2, Integer rank3, Integer state, int id);

	public abstract boolean delete(int id);
}
