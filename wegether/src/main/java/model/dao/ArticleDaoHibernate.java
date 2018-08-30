package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import model.ArticleBean;
import model.ArticleDao;

@Component
public class ArticleDaoHibernate implements ArticleDao {
	private SessionFactory sessionFactory;

	public ArticleDaoHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ArticleBean select(int id) {
		return this.getSession().get(ArticleBean.class, id);
	}

	@Override
	public ArticleBean insert(ArticleBean articleBean) {
		ArticleBean insert = this.getSession().get(ArticleBean.class, articleBean.getId());
		if (insert != null) {
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
