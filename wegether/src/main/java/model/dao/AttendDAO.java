package model.dao;

import java.util.List;

import model.AttendBean;

public interface AttendDAO {
	public abstract AttendBean select(int id);

	public abstract List<AttendBean> select();

	public abstract AttendBean insert(AttendBean bean);

	public abstract AttendBean update(AttendBean bean);

	public abstract boolean delete(int id);
}
