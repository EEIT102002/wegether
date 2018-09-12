package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.BlacklistBean;
import model.dao.BlacklistDAO;
import querylanguage.QueryBean;

@Repository
public class BlacklistDAOHibernate implements BlacklistDAO {
	@Autowired
	QueryBean qb;
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<BlacklistBean> selectByMember(int memberid) {
		return getSession().createQuery("from BlacklistBean where memberid = :memberid", BlacklistBean.class)
				.setParameter("memberid", memberid).list();
	}

	@Override
	public List<BlacklistBean> insert(BlacklistBean bean) {
		if (bean != null) {
			this.getSession().save(bean);
			return selectByMember(bean.getId().getMemberid());
		}
		return null;
	}

	@Override
	public boolean delete(BlacklistBean bean) {
		BlacklistBean temp = this.getSession().get(BlacklistBean.class, bean.getId());
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
}
