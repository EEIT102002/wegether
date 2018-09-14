package model.dao.implement;

import java.util.List;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.BlacklistBean;
import model.dao.BlacklistDAO;
import querylanguage.QueryBean;

@Repository
public class BlacklistDAOHibernate implements BlacklistDAO {
	@Autowired
	QueryBean qb;
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<BlacklistBean> selectByMember(int memberid) {
		return getSession().createQuery("from BlacklistBean where memberid = :memberid", BlacklistBean.class)
				.setParameter("memberid", memberid).list();
	}
	
	private String Hqlstr = "from BlacklistBean where memberid = :memberid and blackid = :blackid";
	@Override
	public BlacklistBean selectByMemberidAndBlackid(int mid, int bid) {
		Query<BlacklistBean> query = getSession().createQuery(Hqlstr);
		BlacklistBean result = query.setParameter("memberid", mid).setParameter("blackid", bid).uniqueResult();
		
		return result;
	}

	@Override
	public BlacklistBean insert(BlacklistBean bean) {
		BlacklistBean result=null;
		if (bean != null) {
			result= selectByMemberidAndBlackid(bean.getId().getMemberid(),bean.getId().getBlackid());
			if(result!=null) {
				this.getSession().save(bean);
				return result;
			}
		}
		return null;
	}

	@Override
	public boolean delete(BlacklistBean bean) {
		BlacklistBean temp = this.getSession().get(BlacklistBean.class, bean.getId());
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
}
