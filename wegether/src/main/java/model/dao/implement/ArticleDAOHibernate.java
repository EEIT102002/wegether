package model.dao.implement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import model.ArticleBean;
import model.dao.ArticleDAO;

@Repository
public class ArticleDAOHibernate implements ArticleDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ArticleBean select(int id) {
		return this.getSession().get(ArticleBean.class, id);
	}

	@Override
	public ArticleBean insert(ArticleBean articleBean) {
		if (articleBean != null) {
				this.getSession().save(articleBean);
				return articleBean;
		}
		return null;
	}

	@Override
	public boolean update(int id, String content) {
		ArticleBean updateBean = this.getSession().get(ArticleBean.class, id);
		if (updateBean != null) {
			updateBean.setContent(content);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		ArticleBean deleteBean = this.getSession().get(ArticleBean.class, id);
		if (deleteBean != null) {
			this.getSession().delete(deleteBean);
			return true;
		}
		return false;
	}

}
