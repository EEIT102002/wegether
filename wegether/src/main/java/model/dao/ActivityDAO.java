package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import model.ActivityBean;

public interface ActivityDAO {
	public abstract ActivityBean select(int id);
	
//	public abstract List<ActivityBean> selectByState(int first);
//	
//	public abstract List<ActivityBean> selectByTime(Date begin, Date end);
//	
//	public abstract List<ActivityBean> selectByActbegin(Date Actbegin);
	
	
	
	public abstract List<ActivityBean> select();

	public abstract ActivityBean insert(ActivityBean bean);

	public abstract ActivityBean update(ActivityBean bean);

	public abstract boolean delete(int id);
}

