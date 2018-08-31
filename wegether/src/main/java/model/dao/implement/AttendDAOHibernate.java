package model.dao.implement;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.AttendBean;
import model.dao.AttendDAO;



@Repository
public class AttendDAOHibernate implements AttendDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public AttendBean select(int id) {

		return this.getSession().get(AttendBean.class, id);
	}

	@Override
	public List<AttendBean> select() {

		return this.getSession().createQuery(
				"from AttendBean", AttendBean.class).list();
	}

	@Override
	public AttendBean insert(AttendBean bean) {
		if(bean!=null) {			
				this.getSession().save(bean);
				return bean;
			
		}
		return null;
	}

	@Override
	public AttendBean update(AttendBean bean) {
		AttendBean temp = this.getSession().get(AttendBean.class, bean.getId());
		if(temp!=null) {
			temp.setActivityid(bean.getActivityid());
			temp.setMemberid(bean.getMemberid());
			temp.setCreatetime(bean.getCreatetime());
			temp.setForm(bean.getForm());
			temp.setRank1(bean.getRank1());
			temp.setRank2(bean.getRank2());
			temp.setRank3(bean.getRank3());
			temp.setState(bean.getState());
			return temp;
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		AttendBean temp = this.getSession().get(AttendBean.class, id);
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
