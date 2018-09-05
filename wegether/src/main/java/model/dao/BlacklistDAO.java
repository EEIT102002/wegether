package model.dao;

import java.util.List;

import model.BlacklistBean;
import model.TrackmemberBean;

public interface BlacklistDAO {
	public List<BlacklistBean> selectByMember(int fanid);
}
