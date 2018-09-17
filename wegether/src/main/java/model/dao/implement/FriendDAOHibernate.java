package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.FriendBean;
import model.dao.FriendDAO;
import querylanguage.QueryBean;
import querylanguage.Select;

@Repository
public class FriendDAOHibernate implements FriendDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private QueryBean querybean;
	private Integer selectLimit = 20;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private String HqlSelectAll = "FROM FriendBean WHERE MEMBERID = :MID AND STATE = 1";
	private String HqlSelectPK = "SELECT ID FROM FriendBean WHERE MEMBERID = :MID AND MEMBERIDF = :MIDF";

	@Override
	public List<FriendBean> select(int memberid) {
		@SuppressWarnings("unchecked")
		Query<FriendBean> query = getSession().createQuery(HqlSelectAll);
		query.setParameter("MID", memberid);
		return query.list();
	}

	@Override
	public FriendBean insert(FriendBean friendBean) {
		if (friendBean != null) {
			getSession().save(friendBean);
			return selectById(friendBean.getId());
		}
		return null;
	}

	@Override
	public boolean updateState(FriendBean friendBean) {
		
		if (friendBean != null) {
			Query update = this.getSession().createQuery(HqlSelectPK);
			if (update != null) {
				update.setParameter("MID", friendBean.getMemberid());
				update.setParameter("MIDF", friendBean.getMemberidf());
				this.getSession().get(FriendBean.class, update.toString());
				return true;
			}
		}
		return false;
	}

	@Override
	public List<FriendBean> selectByMemberState(int memberid, int state, int first) {
		return querybean.getBeanList(
				querybean.getSelectQuery(Select.friendByMemberState, memberid, first, selectLimit, FriendBean.class)
						.setParameter("state", state));

	}

	@Override
	public List<FriendBean> selectByMemberFState(int memberidf, int state, int first) {
		return querybean.getBeanList(
				querybean.getSelectQuery(Select.friendByMemberFState, memberidf, first, selectLimit, FriendBean.class)
						.setParameter("state", state));
	}

	@Override
	public FriendBean selectById(int id) {
		return getSession().get(FriendBean.class, id);
	}

	@Override
	public boolean delete(FriendBean friendBean) {
		FriendBean delete = getSession().get(FriendBean.class, friendBean.getId());
		if (delete != null && delete.getMemberid() == friendBean.getMemberid()) {
			getSession().delete(delete);
			return true;
		}
		return false;
	}

	@Override
	public Integer selectCountByMemberState(int memberid, int state) {
		return ((Long)getSession().createQuery(
				"select count(*) from FriendBean where memberid = :memberid and state = :state")
			.setParameter("memberid", memberid)
			.setParameter("state", state).uniqueResult()).intValue();
	}
	
	@Override
	public Integer selectCountByMemberFState(int memberidf, int state) {
		return ((Long)getSession().createQuery(
				"select count(*) from FriendBean where memberidf = :memberidf and state = :state")
			.setParameter("memberid", memberidf)
			.setParameter("state", state).uniqueResult()).intValue();
	}	
	

}
