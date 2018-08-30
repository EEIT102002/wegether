package model.dao;

import java.util.List;

import model.InviteBean;

public interface InviteDAO {
	public abstract InviteBean select(Integer id );

	public abstract List<InviteBean> select();

	public abstract InviteBean insert(InviteBean bean);

	public abstract InviteBean update(Integer id, Integer memberid,
			Integer invitedid, Integer activityid);
	public abstract boolean delete(Integer id );
	
//	private Integer id ;
//	private Integer memberid;
//	private Integer invitedid;
//	private Integer activityid;
//	
}
