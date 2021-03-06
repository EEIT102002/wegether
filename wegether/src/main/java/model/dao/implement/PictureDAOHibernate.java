package model.dao.implement;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.PictureBean;
import model.dao.PictureDAO;

@Repository
public class PictureDAOHibernate implements PictureDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private PictureDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private final String selectByMemberHql = "from PictureBean where memberid = :id";
	private final String selectByActivityHql = "from PictureBean where activityid = :id";
	private final String selectByArticleHql = "from PictureBean where articleid = :id";

	@Override
	public List<PictureBean> selectByMember(Integer memberid) {
		Query<PictureBean> query = getSession().createQuery(selectByMemberHql, PictureBean.class);
		query.setParameter("id", memberid);
		return query.list();
	}

	@Override
	public List<PictureBean> selectByActivity(Integer actid) {
		Query<PictureBean> query = getSession().createQuery(selectByActivityHql, PictureBean.class);
		query.setParameter("id", actid);
		return query.list();
	}

	@Override
	public List<PictureBean> selectByArticle(Integer articleid) {
		Query<PictureBean> query = getSession().createQuery(selectByArticleHql, PictureBean.class);
		query.setParameter("id", articleid);
		return query.list();
	}

	@Override
	public PictureBean update(PictureBean pictureBean) {
		PictureBean temp = this.getSession().get(PictureBean.class, pictureBean.getId());
		if (temp != null)
			temp.setPicture(pictureBean.getPicture());
		return null;
	}

	@Override
	public boolean delete(int id) {
		PictureBean temp = this.getSession().get(PictureBean.class, id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}

	@Override
	public PictureBean insert(PictureBean picturebean) {
		if (picturebean != null) {
			this.getSession().save(picturebean);
			return Select(picturebean.getId());
		}
		return null;
	}

	@Override
	public PictureBean Select(int id) {
		PictureBean idresult = this.getSession().get(PictureBean.class, id);
		return idresult;
	}

}
