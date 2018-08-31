package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.AttendBean;



@Repository
public class AttendDAOHibernate implements AttendDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
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
			int id = bean.getId();
			AttendBean temp = this.getSession().get(AttendBean.class, id);
			if(temp==null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public AttendBean update(Integer activityid, Integer memberid, Date createtime, String form, Integer rank1,
			Integer rank2, Integer rank3, Integer state, int id) {
		AttendBean temp = this.getSession().get(AttendBean.class, id);
		if(temp!=null) {
			temp.setActivityid(activityid);
			temp.setMemberid(memberid);
			temp.setCreatetime(createtime);
			temp.setForm(form);
			temp.setRank1(rank1);
			temp.setRank2(rank2);
			temp.setRank3(rank3);
			temp.setState(state);
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
