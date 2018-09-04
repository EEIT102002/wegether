package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.TrackmemberBean;
import model.dao.TrackmemberDAO;
import querylanguage.QueryBean;
import querylanguage.Select;

@Repository
public class TrackmemberDAOHibernate implements TrackmemberDAO{
	@Autowired
	QueryBean qb;
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<TrackmemberBean> selectByFan(int fanid) {
		return getSession().createQuery(
				"from TrackmemberBean",TrackmemberBean.class)
//				.setParameter("fanid", fanid)
				.list();
	}

}
