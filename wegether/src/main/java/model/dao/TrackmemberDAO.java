package model.dao;

import java.util.List;

import model.TrackmemberBean;
import model.TrackmemberId;

public interface TrackmemberDAO {
	public List<TrackmemberBean> selectByFan(int fanid);
	public List<TrackmemberBean> selectBymemberid(int  memberid);
	public List<TrackmemberBean> insert(TrackmemberBean id);
	public boolean delete(TrackmemberBean id);
}
