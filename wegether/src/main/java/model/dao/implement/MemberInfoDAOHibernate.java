package model.dao.implement;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MemberInfoBean;
import model.dao.MemberInfoDAO;

@Repository
public class MemberInfoDAOHibernate implements MemberInfoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public MemberInfoBean select(int id) {
		
		return getSession().get(MemberInfoBean.class, id);
	}
	
}
