package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.FriendBean;
import model.FriendDao;

public class FriendDaoHibernate implements FriendDao {
	private SessionFactory sessionFactory;

	public FriendDaoHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private String HqlSelectAll = "SELECT FROM FRIEND WHERE MEMBERID = :MID AND STATE = 1";
	private String HqlSelectOne = "SELECT FROM FRIEND WHERE MEMBERID = :MID AND MEMBERIDF = :MIDF";

	@Override
	public List<FriendBean> select(int memberid) {
		Query<FriendBean> query = this.getSession().createQuery(HqlSelectAll, FriendBean.class);
		query.setParameter("MID", memberid);
		return query.list();
	}

	@Override
	public FriendBean insert(FriendBean friendBean) {
		if (friendBean != null) {
			FriendBean select = this.getSession().get(FriendBean.class, friendBean.getId());
			if (select != null) {
				this.getSession().save(friendBean);
				return friendBean;
			}
		}
		return null;
	}

	@Override
	public boolean updateState(FriendBean friendBean) {
		Query update = this.getSession().createQuery(HqlSelectOne, FriendBean.class);
		update.setParameter("MID", friendBean.getMemberid());
		update.setParameter("MIDF", friendBean.getMemberidf());
		if (update != null) {
			((FriendBean) update).setState(friendBean.getState());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int memberid, int memberidf) {
		Query delete = this.getSession().createQuery(HqlSelectOne, FriendBean.class);
		delete.setParameter("MID", memberid);
		delete.setParameter("MIDF", memberidf);
		if (delete != null) {
			this.getSession().delete(delete);
			return true;
		}
		return false;
	}

}
