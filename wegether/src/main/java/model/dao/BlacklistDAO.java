package model.dao;

import java.util.List;

import model.BlacklistBean;

public interface BlacklistDAO {
	public List<BlacklistBean> selectByMember(int fanid);
	public List<BlacklistBean> insert(BlacklistBean bean);
	public boolean delete(BlacklistBean bean);
}
