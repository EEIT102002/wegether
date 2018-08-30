package model;

import java.util.List;

public interface FriendDao {
	public abstract List<FriendBean> select(int memberid);

	public abstract FriendBean insert(FriendBean friendBean);

	public abstract boolean updateState(FriendBean friendBean);
}