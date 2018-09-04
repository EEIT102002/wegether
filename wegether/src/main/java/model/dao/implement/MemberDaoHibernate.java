package model.dao.implement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MemberBean;
import model.dao.MemberDAO;

@Repository
public class MemberDaoHibernate implements MemberDAO {
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
				this.getSession().save(memberBean);
				return true;
			}
		}
		return false;
	}
}
