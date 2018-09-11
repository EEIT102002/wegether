package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import model.ActivityBean;
import model.FriendBean;
import model.MemberBean;

public interface ActivityDAO {
	public abstract ActivityBean selectId(int id);
	
	public abstract List<ActivityBean> selectBymemberid(int memberid);
	
	public abstract List<ActivityBean> selectAll();
		
	public abstract List<ActivityBean> selectByState(int state);

	public abstract List<ActivityBean> selectByTime(int state, Date begin, Date end);

	public abstract List<ActivityBean> selectByActbegin(int state, Date Actbegin);
	
	public abstract List<ActivityBean> selectByCity(int state, int city);
	
	public abstract List<ActivityBean> selectByAddress(int state, String addr);
	
	public abstract List<ActivityBean> selectByTitle(int state, String title);
	
	public abstract List<ActivityBean> selectByClasstype(int state, String classtype);
	
	public abstract List<ActivityBean> selectByNickname(int state, String nickname);
	
	public abstract List<ActivityBean> selectByFees(int state, int fees);
	
	public abstract List<ActivityBean> selectByClick(int state, int click);

	public abstract ActivityBean insert(ActivityBean bean);

	public abstract ActivityBean update(ActivityBean bean);

	public abstract boolean delete(int id);
	
	//indexPage;state=0:活動搜尋 ; state=1:心得PO文搜尋	
	public abstract List<ActivityBean> selectOfIndex(int state, int city, String beginDate, String endDate, String classtype, String title);

	public abstract List<ActivityBean> selectOfIndexPo(int state, int city, String beginDate, String endDate, String classtype,
			String title, List<Integer> Actid);

	public abstract List<ActivityBean> selectAllState();
}

