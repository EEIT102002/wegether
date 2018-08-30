package model.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.NoticeBean;

@Repository
public class NoticeDAOHibernate implements NoticeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private String selectByMemberIdHql = 
			"from NoticeBean where memberid = :id order by noticetime desc";
	@Override
	public List<NoticeBean> selectByMemberId(Integer id, Integer first){
		Query<NoticeBean> query = getSession().createQuery(selectByMemberIdHql, NoticeBean.class);
		query.setParameter("id", id);
		query.setFirstResult(first);
		query.setMaxResults(10);
		return query.list();
	}

}
