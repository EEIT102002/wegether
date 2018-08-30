package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MsgBean;

@Repository
public class MsgDAOHibernate implements MsgDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Integer selectLimit;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private final String selectActivityHql= "from MsgBean where activityid = :id order by msgtime";
	private final String selectArticleHql= "from MsgBean where activityid = :id order by msgtime";
	
	private Query<MsgBean> getSelectActivityQuery(){
		return getSession().createQuery(selectActivityHql, MsgBean.class);
	}
	
	private Query<MsgBean> getSelectArticleQuery(){
		return getSession().createQuery(selectArticleHql, MsgBean.class);
	}
	
	@Override
	public List<MsgBean> selectByActivity(Integer id) {
		return getSelectActivityQuery()
				.setParameter("id", id).list();
	}

	@Override
	public List<MsgBean> selectByArticle(Integer id) {
		return getSelectArticleQuery()
				.setParameter("id", id).list();
	}

	@Override
	public List<MsgBean> selectByActivity(Integer id, Integer first) {
		return getSelectActivityQuery()
				.setParameter("id", id)
				.setFirstResult(first)
				.setMaxResults(selectLimit).list();
	}

	@Override
	public List<MsgBean> selectByArticle(Integer id, Integer first) {
		// TODO Auto-generated method stub
		return getSelectArticleQuery()
				.setParameter("id", id)
				.setFirstResult(first)
				.setMaxResults(selectLimit).list();
	}

	@Override
	public Boolean insert(MsgBean msgBean) {
		try {
			getSession().save(msgBean);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean delete(Integer id) {
		
		MsgBean delete = getSession().get(MsgBean.class, id);
		if(delete != null) {
			getSession().delete(delete);
			return true;
		}
		return null;
	}

}
