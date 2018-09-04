package model.dao.implement;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
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
	
	private final String selectByTime = "from ActivityBean where Convert(DateTime,dateline) >=Convert(DateTime,:begin)"+
			"and Convert(DateTime,dateline)<= Convert(DateTime,:end) and state=:state ";
	@Override
	public List<ActivityBean> selectByTime(int state, Date begin, Date end) {
		System.out.println(selectByTime);
		return this.getSession().createQuery(selectByTime,ActivityBean.class)
				.setParameter("state", state)
				.setParameter("begin", begin)
				.setParameter("end", end)
				.list();
	}


	private final String selectByActbegin = "from ActivityBean  WHERE state=:state and Convert(DateTime,actbegin) =Convert(DateTime,:actbegin)";
	@Override
	public List<ActivityBean> selectByActbegin(int state, Date actbegin) {
		return this.getSession().createQuery(selectByActbegin, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("actbegin", actbegin)
				.list();
	}

	private final String selectByCity = "from ActivityBean  WHERE state=:state and city =:city ";
	@Override
	public List<ActivityBean> selectByCity(int state, int city) {
		return this.getSession().createQuery(selectByCity, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("city",city)
				.list();
	}

	private final String selectByAddress = "from ActivityBean  WHERE state=:state and addr like :addr ";
	@Override
	public List<ActivityBean> selectByAddress(int state, String addr) {
		return this.getSession().createQuery(selectByAddress, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("addr","%"+addr+"%")
				.list();
	}

	private final String selectByTitle = "from ActivityBean  WHERE state=:state and title like :title ";
	@Override
	public List<ActivityBean> selectByTitle(int state, String title) {
		return this.getSession().createQuery(selectByTitle, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("title","%"+title+"%")
				.list();
	}

	private final String selectByClasstype = "from ActivityBean  WHERE state=:state and classtype like :classtype ";
	@Override
	public List<ActivityBean> selectByClasstype(int state, String classtype) {
		return this.getSession().createQuery(selectByClasstype, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("classtype","%"+classtype+"%")
				.list();
	}

	private final String selectByNickname = "select act from ActivityBean act , MemberBean mem  where act.state=:state and act.hostid = mem.id and mem.nickname like :nickname";
	@Override
	public List<ActivityBean> selectByNickname(int state, String nickname) {
		return this.getSession().createQuery(selectByNickname, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("nickname","%"+nickname+"%")
				.list();
	}

	private final String selectByFees = "from ActivityBean  WHERE state=:state and feed <= : fees ORDER BY feed  ";
	@Override
	public List<ActivityBean> selectByFees(int state, int fees) {
		return this.getSession().createQuery(selectByFees, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("fees",fees)
				.list();
	}

	private final String selectByClick = "from ActivityBean  WHERE state=:state and click >  :click ORDER BY click DESC ";
	@Override
	public List<ActivityBean> selectByClick(int state, int click) {
		return this.getSession().createQuery(selectByClick, ActivityBean.class)
				.setParameter("state", state)
				.setParameter("click",click)
				.list();
	}
	
	
	//indexPage;state=0:活動搜尋 ; state=1:心得PO文搜尋
	private  String selectOfIndex = "select * from Activity  WHERE ";
	@Override
	public List<ActivityBean> selectOfIndex(int state, int city, String beginDate, String endDate, String classtype, String title) {
		
		if(beginDate!=null && endDate!=null && beginDate.length()!=0 && endDate.length()!=0) {
			selectOfIndex = selectOfIndex +"dateline BETWEEN '"+beginDate+"' AND '"+endDate+"' and state="+ state;
		}else selectOfIndex = selectOfIndex +"state="+state;		
		
		if(city!=0) selectOfIndex = selectOfIndex+" and city="+city;		
		
		if(classtype!=null && classtype.length()!=0) selectOfIndex = selectOfIndex+" and classtype=\'"+classtype+"\'";		
		
		if(title!=null && title.length()!=0) selectOfIndex = selectOfIndex+" and title like \'%"+title+"%\'";
		
		return this.getSession().createNativeQuery(selectOfIndex,ActivityBean.class).list();
	}
	


}