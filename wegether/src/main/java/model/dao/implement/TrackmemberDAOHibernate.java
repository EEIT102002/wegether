package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
				"from TrackmemberBean where fanid = :fanid",TrackmemberBean.class)
				.setParameter("fanid", fanid)
				.list();
	}
     private final String Hqltrackmembermemberid = "FROM TrackmemberBean WHERE MEMBERID = :MID ";
	@Override
	public List<TrackmemberBean> selectBymemberid(int memberid) {
		@SuppressWarnings("unchecked")
		Query<TrackmemberBean> query = getSession().createQuery( Hqltrackmembermemberid);
		query.setParameter("MID", memberid);
		return query.list();
	}
	

}
