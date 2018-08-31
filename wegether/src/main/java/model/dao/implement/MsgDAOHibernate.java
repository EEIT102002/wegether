package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MsgBean;
import model.NoticeBean;
import model.dao.MsgDAO;
import querylanguage.Select;

@Repository
public class MsgDAOHibernate implements MsgDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Integer msgSelectLimit;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private NativeQuery<?> getSelectQuery(String sql, int id) {
		return getSession().createSQLQuery(sql)
				.addEntity("m", MsgBean.class)
				.setParameter("id", id);
	}
	
	private NativeQuery<?> getSelectQuery(String sql, int id, int first) {
		return getSelectQuery(sql, id)
				.setParameter("offset_first", first)
				.setParameter("offset_max", msgSelectLimit);
	}
	
	@SuppressWarnings("unchecked")
	private List<MsgBean> getBeanList(NativeQuery<?> nq) {
		return (List<MsgBean>) nq.list();
	}

	@Override
	public List<MsgBean> selectByActivity(Integer id) {
		return getBeanList(getSelectQuery(Select.msgByActivity, id));
	}

	@Override
	public List<MsgBean> selectByArticle(Integer id) {
		return getBeanList(getSelectQuery(Select.msgByArticle, id));
	}

	@Override
	public List<MsgBean> selectByActivity(Integer id, Integer first) {
		return getBeanList(getSelectQuery(Select.msgByActivityOffset, id, first));
	}

	@Override
	public List<MsgBean> selectByArticle(Integer id, Integer first) {
		return getBeanList(getSelectQuery(Select.msgByArticleOffset, id, first));
	}

	@Override
	public Boolean insert(MsgBean msgBean) {
		try {
			getSession().flush();
			getSession().save(msgBean);
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			getSession().beginTransaction();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delete(Integer id) {

		MsgBean delete = getSession().get(MsgBean.class, id);
		if (delete != null) {
			getSession().delete(delete);
			return true;
		}
		return null;
	}

}
