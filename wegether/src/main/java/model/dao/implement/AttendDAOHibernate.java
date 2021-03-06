package model.dao.implement;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.ActivityBean;
import model.AttendBean;
import model.FriendBean;
import model.dao.AttendDAO;
import querylanguage.QueryBean;



@Repository
public class AttendDAOHibernate implements AttendDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private QueryBean querybean;
	
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
	
	private final String selectByActID = "from AttendBean  WHERE activityid = :activityid ";
	@Override
	public List<AttendBean> selectByActID(int actID) {
		return this.getSession().createQuery(selectByActID, AttendBean.class)
				.setParameter("activityid",actID).list();		
	}
	
	private final String selectByActivityAndStateHql = selectByActID+" and state = :state";
	public List<AttendBean> selectByActivityAndState(int activityid , int state) {
		return this.getSession().createQuery(selectByActivityAndStateHql, AttendBean.class)
				.setParameter("activityid",activityid)
				.setParameter("state", state).list();		
	}
	
	private final String selectByActivityAndMember = "from AttendBean  WHERE activityid = :activityid and memberid = :memberid";
	public AttendBean selectByActivityAndMember(int activityid, int memberid) {
		return this.getSession().createQuery(selectByActivityAndMember, AttendBean.class)
				.setParameter("activityid",activityid)
				.setParameter("memberid",memberid).uniqueResult();		
	}
	
	private final String selectByMemID = "from AttendBean  WHERE memberid = :memberid ";
	@Override
	public List<AttendBean> selectByMemID(int memID) {
		return this.getSession().createQuery(selectByMemID, AttendBean.class)
				.setParameter("memberid",memID).list();
	}
	
	private final String Hqlattendmemberid = "FROM AttendBean WHERE MEMBERID = :MID AND STATE = 1";
	@Override
	public List<AttendBean> selectBymemberid(int memberid) {
		@SuppressWarnings("unchecked")
		Query<AttendBean> query = getSession().createQuery(Hqlattendmemberid);
		query.setParameter("MID", memberid);
		return query.list();
	}
	@Override
	public AttendBean insert(AttendBean bean) {
		if(bean!=null && selectByActivityAndMember(bean.getActivityid(), bean.getMemberid()) == null) {					
				this.getSession().save(bean);
			return bean;			
		}
		return null;
	}

	@Override
	public AttendBean update(AttendBean bean) {
		AttendBean temp = this.getSession().get(AttendBean.class, bean.getId());
		if(temp!=null) {
			getSession().merge(bean);
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
	
	@Override
	public Integer selectCountByActivityAndState(int activityid, int state) {
		return ((Long)getSession().createQuery(
					"select count(*) from AttendBean where activityid = :activityid and state = :state")
				.setParameter("activityid", activityid)
				.setParameter("state", state).uniqueResult()).intValue();

	}

}
