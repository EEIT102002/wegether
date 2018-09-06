package model.dao;

import java.util.List;

import model.TrackmemberBean;

public interface TrackmemberDAO {
	public List<TrackmemberBean> selectByFan(int fanid);
	public List<TrackmemberBean> selectBymemberid(int  memberid);
}
