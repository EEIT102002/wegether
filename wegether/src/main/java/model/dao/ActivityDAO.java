package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import model.ActivityBean;
import model.MemberBean;

public interface ActivityDAO {
	public abstract ActivityBean selectId(int id);
	
	public abstract List<ActivityBean> selectAll();
		
	public abstract List<ActivityBean> selectByState(int state);

	public abstract List<ActivityBean> selectByTime(Date begin, Date end);

	public abstract List<ActivityBean> selectByActbegin(Date Actbegin);
	
	public abstract List<ActivityBean> selectByCity(int city);
	
	public abstract List<ActivityBean> selectByAddress(String addr);
	
	public abstract List<ActivityBean> selectByTitle(String title);
	
	public abstract List<ActivityBean> selectByClasstype(String classtype);
	
	public abstract List<ActivityBean> selectByNickname(String nickname);
	
	public abstract List<ActivityBean> selectByFees(int fees);
	
	public abstract List<ActivityBean> selectByClick(int click);

	public abstract ActivityBean insert(ActivityBean bean);

	public abstract ActivityBean update(ActivityBean bean);

	public abstract boolean delete(int id);
	
	//indexPage;state=0:活動搜尋 ; state=1:心得PO文搜尋
	public abstract List<ActivityBean> selectOfIndex(int state, int city);
	public abstract List<ActivityBean> selectOfIndex(int state, String title);
	public abstract List<ActivityBean> selectOfIndex(int state, Date begin, Date end);
	
	public abstract List<ActivityBean> selectOfIndex(int state, int city, String title);
	public abstract List<ActivityBean> selectOfIndex(int state, int city, Date begin, Date end);
	public abstract List<ActivityBean> selectOfIndex(int state, String title, Date begin, Date end);
	
	public abstract List<ActivityBean> selectOfIndex(int state, int city, String title, Date begin, Date end);
}

