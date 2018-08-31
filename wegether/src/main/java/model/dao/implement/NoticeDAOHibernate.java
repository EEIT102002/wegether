package model.dao.implement;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.NoticeBean;
import model.dao.NoticeDAO;

@Repository
public class NoticeDAOHibernate implements NoticeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private final String selectByMemberIdHql = 
			"from NoticeBean where memberid = :id order by noticetime desc";
	@Override
	public List<NoticeBean> selectByMemberId(Integer id, Integer first){
		
		return getSession().createQuery(selectByMemberIdHql, NoticeBean.class)
					.setParameter("id", id)
					.setFirstResult(first)
					.setMaxResults(10).list();
	}

}
