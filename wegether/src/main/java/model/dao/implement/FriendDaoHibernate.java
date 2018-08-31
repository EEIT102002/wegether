package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.FriendBean;
import model.dao.FriendDao;

@Repository
public class FriendDaoHibernate implements FriendDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private String HqlSelectAll = "FROM FriendBean WHERE MEMBERID = :MID AND STATE = 1";
	private String HqlUpdateOne = "UPDATE FriendBean SET STATE = :ST WHERE MEMBERID = :MID AND MEMBERIDF = :MIDF";

	@Override
	public List<FriendBean> select(int memberid) {
		Query<FriendBean> query = this.getSession().createQuery(HqlSelectAll);
		query.setParameter("MID", memberid);
		return query.list();
	}

	@Override
	public FriendBean insert(FriendBean friendBean) {
		if (friendBean != null) {
			getSession().save(friendBean);
			return friendBean;
		}
		return null;
	}

	@Override
	public boolean updateState(FriendBean friendBean) {
		if (friendBean != null) {
			Query update = this.getSession().createQuery(HqlUpdateOne);
			if (update != null) {
				update.setParameter("MID", friendBean.getMemberid());
				update.setParameter("MIDF", friendBean.getMemberidf());
				update.setParameter("ST", friendBean.getState());
				update.executeUpdate();
				return true;
			}
		}
		return false;
	}
}
