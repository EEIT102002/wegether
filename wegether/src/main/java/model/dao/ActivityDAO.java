package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import model.ActivityBean;

public interface ActivityDAO {
	public abstract ActivityBean select(int id);

	public abstract List<ActivityBean> select();

	public abstract ActivityBean insert(ActivityBean bean);

	public abstract ActivityBean update(Integer hostid,	Date createtime, String title,
			Integer city, String addr, byte[] picture, Date actbegin, Date actend,
			Date dateline, String classtype, String content, Integer numberlimit,
			Integer feed, Integer state, Double rank1, Double rank2, Double rank3,
			Integer judges, String form, Integer click, int id);

	public abstract boolean delete(int id);
}

