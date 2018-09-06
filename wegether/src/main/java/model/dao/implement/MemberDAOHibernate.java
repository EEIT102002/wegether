package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MemberBean;
import model.dao.MemberDAO;

@Repository
public class MemberDAOHibernate implements MemberDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public MemberBean select(int id) {
		return this.getSession().get(MemberBean.class, id);
	}

	@Override
	public List<MemberBean> select() {
		return this.getSession().createQuery("from MemberBean",MemberBean.class).list();
	}
	@Override
	public MemberBean insert(MemberBean memberBean) {
		if (memberBean != null) {
			this.getSession().save(memberBean);
			return memberBean;
		}
		return null;
	}

	@Override
	public boolean update(MemberBean memberBean) {
		if (memberBean != null) {
			MemberBean updateBean = this.getSession().get(MemberBean.class, memberBean.getId());
			if (updateBean != null) {
				this.getSession().merge(memberBean);
				return true;
			}
		}
		return false;
	}

	@Override
	public MemberBean selectByAccount(String account) {
		 List<MemberBean> result = getSession().createQuery(
				 "from MemberBean where account = :account",MemberBean.class)
				 .setParameter("account", account).list();
		 if(result.size()>0) {
			 return result.get(0);
		 }else {
			 return null;
		 }

	}

	
}
