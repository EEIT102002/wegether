package model.dao;

import java.util.List;

import model.BlacklistBean;

public interface BlacklistDAO {
	public List<BlacklistBean> selectByMember(int fanid);
	public BlacklistBean selectByMemberidAndBlackid(int mid,int bid);
	public BlacklistBean insert(BlacklistBean bean);

	public boolean delete(BlacklistBean bean);
}
