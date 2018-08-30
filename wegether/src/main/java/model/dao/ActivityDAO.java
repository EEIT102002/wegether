package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.sql.Select;

import model.ActivityBean;

public interface ActivityDAO {

	
	public abstract ActivityBean select(Integer id);
	public abstract List<ActivityBean>select();
	public abstract ActivityBean insert(ActivityBean bean);
	public abstract ActivityBean update(
			Integer id, Integer hostid, Date createtime, String title,
			Integer city, String addr, byte[] picture, Date actbegin,
			Date actend, Date dateline, String classtype, String content,
			Integer numberlimit, Integer feed, Integer state, BigDecimal rank1,
			BigDecimal rank2, BigDecimal rank3, Integer judges, String form,
			Integer click
			);
	public abstract boolean delete(Integer id);
}
