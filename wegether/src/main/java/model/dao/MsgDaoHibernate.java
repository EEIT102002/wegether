package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.MsgBean;
import model.MsgDao;

public class MsgDaoHibernate implements MsgDao {
	private SessionFactory sessionFactory;

	public MsgDaoHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public MsgBean select(int id) {
		return this.getSession().get(MsgBean.class, id);
	}

	@Override
	public boolean update(int id, String content) {
		MsgBean updateBean = this.getSession().get(MsgBean.class, id);
		if (updateBean != null) {
			updateBean.setContent(content);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		MsgBean deleteBean = this.getSession().get(MsgBean.class, id);
		if (deleteBean != null) {
			this.getSession().delete(deleteBean);
			return true;
		}
		return false;
	}

}
