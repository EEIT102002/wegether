package model.dao.implement;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import model.ActivityBean;
import model.MemberBean;
import model.ServiceBean;
import model.dao.ActivityDAO;





@Repository
public class ActivityDAOHibernate implements ActivityDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ActivityBean selectId(int id) {

		return this.getSession().get(ActivityBean.class, id);
	
	}

	@Override
	public List<ActivityBean> selectAll() {
		
		return this.getSession().createQuery(
				"from ActivityBean", ActivityBean.class).list();
	}

	@Override
	public ActivityBean insert(ActivityBean bean) {
		if(bean!=null) {
				this.getSession().save(bean);
				return bean;
		}
		return null;
	}


	@Override
	public ActivityBean update(ActivityBean bean) {
		ActivityBean temp = this.getSession().get(ActivityBean.class, bean.getId());

		if(temp!=null) {
			this.getSession().merge(bean);
			return temp;
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		ActivityBean temp = this.getSession().get(ActivityBean.class, id);
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}

	
	private final String selectByState = "from ActivityBean  WHERE state = :state ";
	@Override
	public List<ActivityBean> selectByState(int state) {				
		return this.getSession().createQuery(selectByState, ActivityBean.class).setParameter("state",state).list();
	}
	
	private final String selectByTime = "from ActivityBean where Convert(DateTime,actbegin) >=Convert(DateTime,:begin)"+
			"and Convert(DateTime,actbegin)<= Convert(DateTime,:end)";
	@Override
	public List<ActivityBean> selectByTime(Date begin, Date end) {
		return this.getSession().createQuery(selectByTime,ActivityBean.class)
				.setParameter("begin", begin)
				.setParameter("end", end)
				.list();
	}


	private final String selectByActbegin = "from ActivityBean  WHERE  Convert(DateTime,actbegin) =Convert(DateTime,:actbegin)";
	@Override
	public List<ActivityBean> selectByActbegin(Date actbegin) {
		return this.getSession().createQuery(selectByActbegin, ActivityBean.class).setParameter("actbegin", actbegin).list();
	}

	private final String selectByCity = "from ActivityBean  WHERE city =  :city ";
	@Override
	public List<ActivityBean> selectByCity(int city) {
		return this.getSession().createQuery(selectByCity, ActivityBean.class).setParameter("city",city).list();
	}

	private final String selectByAddress = "from ActivityBean  WHERE addr like :addr ";
	@Override
	public List<ActivityBean> selectByAddress(String addr) {
		return this.getSession().createQuery(selectByAddress, ActivityBean.class).setParameter("addr","%"+addr+"%").list();
	}

	private final String selectByTitle = "from ActivityBean  WHERE title like :title ";
	@Override
	public List<ActivityBean> selectByTitle(String title) {
		return this.getSession().createQuery(selectByTitle, ActivityBean.class).setParameter("title","%"+title+"%").list();
	}

	private final String selectByClasstype = "from ActivityBean  WHERE classtype like :classtype ";
	@Override
	public List<ActivityBean> selectByClasstype(String classtype) {
		return this.getSession().createQuery(selectByClasstype, ActivityBean.class).setParameter("classtype","%"+classtype+"%").list();
	}

	private final String selectByNickname = "select act from ActivityBean act , MemberBean mem  where act.hostid = mem.id and mem.nickname like :nickname";
	@Override
	public List<ActivityBean> selectByNickname(String nickname) {
		return this.getSession().createQuery(selectByNickname, ActivityBean.class).setParameter("nickname","%"+nickname+"%").list();
	}

	private final String selectByFees = "from ActivityBean  WHERE feed <= : fees ORDER BY feed  ";
	@Override
	public List<ActivityBean> selectByFees(int fees) {
		return this.getSession().createQuery(selectByFees, ActivityBean.class).setParameter("fees",fees).list();
	}

	private final String selectByClick = "from ActivityBean  WHERE click >  :click ORDER BY click DESC ";
	@Override
	public List<ActivityBean> selectByClick(int click) {
		return this.getSession().createQuery(selectByClick, ActivityBean.class).setParameter("click",click).list();
	}
	
	

}
