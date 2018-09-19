package model.dao;

import java.util.List;

import model.FriendBean;

public interface FriendDAO {
	public abstract List<FriendBean> select(int memberid);
	
	public abstract List<FriendBean> selectByMemberState(int memberid,int state , int first);
	
	public abstract List<FriendBean> selectByMemberFState(int memberidf, int state ,int first);
	
	public abstract FriendBean selectById(int id);
	
	public abstract FriendBean insert(FriendBean friendBean);

	public abstract boolean updateState(FriendBean friendBean);
	
	public abstract boolean delete(FriendBean friendBean);
	public abstract Integer selectCountByMemberState(int memberid, int state);
	public abstract Integer selectCountByMemberFState(int memberidf, int state);
}