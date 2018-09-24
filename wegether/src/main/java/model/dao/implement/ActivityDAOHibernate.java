
package model.dao.implement;

import static org.hamcrest.CoreMatchers.nullValue;

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

		return this.getSession().createQuery("from ActivityBean", ActivityBean.class).list();
	}

	private final String selectAllState = "from ActivityBean  WHERE state=0 ORDER BY id DESC";

	@Override
	public List<ActivityBean> selectAllState() {

		return this.getSession().createQuery(selectAllState, ActivityBean.class).list();
	}

	@Override
	public ActivityBean insert(ActivityBean bean) {
		if (bean != null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}

	@Override
	public ActivityBean update(ActivityBean bean) {
		ActivityBean temp = this.getSession().get(ActivityBean.class, bean.getId());
		if (temp != null) {
			if (bean.getTitle() != null)
				temp.setTitle(bean.getTitle());
			if (bean.getCity() != null)
				temp.setCity(bean.getCity());
			if (bean.getClick() != null)
				temp.setClick(bean.getClick());
			if (bean.getContent() != null)
				temp.setContent(bean.getContent());
			if (bean.getAddr() != null)
				temp.setAddr(bean.getAddr());
			if (bean.getPicture() != null)
				temp.setPicture(bean.getPicture());
			if (bean.getActbegin() != null)
				temp.setActend(bean.getActbegin());
			if (bean.getActend() != null)
				temp.setActend(bean.getActend());
			if (bean.getDateline() != null)
				temp.setDateline(bean.getDateline());
			if (bean.getClasstype() != null)
				temp.setClasstype(bean.getClasstype());
			if (bean.getNumberlimit() != null)
				temp.setNumberlimit(bean.getNumberlimit());
			if (bean.getFeed() != null)
				temp.setFeed(bean.getFeed());
			return temp;
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		ActivityBean temp = this.getSession().get(ActivityBean.class, id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}

	private final String selectByState = "from ActivityBean  WHERE state = :state ";

	@Override
	public List<ActivityBean> selectByState(int state) {
		return this.getSession().createQuery(selectByState, ActivityBean.class).setParameter("state", state).list();
	}

	private final String selectByTime = "from ActivityBean where Convert(DateTime,dateline) >=Convert(DateTime,:begin)"
			+ "and Convert(DateTime,dateline)<= Convert(DateTime,:end) and state=:state ";

	@Override
	public List<ActivityBean> selectByTime(int state, Date begin, Date end) {
		System.out.println(selectByTime);
		return this.getSession().createQuery(selectByTime, ActivityBean.class).setParameter("state", state)
				.setParameter("begin", begin).setParameter("end", end).list();
	}

	private final String selectByActbegin = "from ActivityBean  WHERE state=:state and Convert(DateTime,actbegin) =Convert(DateTime,:actbegin)";

	@Override
	public List<ActivityBean> selectByActbegin(int state, Date actbegin) {
		return this.getSession().createQuery(selectByActbegin, ActivityBean.class).setParameter("state", state)
				.setParameter("actbegin", actbegin).list();
	}

	private final String selectByCity = "from ActivityBean  WHERE state=:state and city =:city ";

	@Override
	public List<ActivityBean> selectByCity(int state, int city) {
		return this.getSession().createQuery(selectByCity, ActivityBean.class).setParameter("state", state)
				.setParameter("city", city).list();
	}

	private final String selectByAddress = "from ActivityBean  WHERE state=:state and addr like :addr ";

	@Override
	public List<ActivityBean> selectByAddress(int state, String addr) {
		return this.getSession().createQuery(selectByAddress, ActivityBean.class).setParameter("state", state)
				.setParameter("addr", "%" + addr + "%").list();
	}

	private final String selectByTitle = "from ActivityBean  WHERE state=:state and title like :title ";

	@Override
	public List<ActivityBean> selectByTitle(int state, String title) {
		return this.getSession().createQuery(selectByTitle, ActivityBean.class).setParameter("state", state)
				.setParameter("title", "%" + title + "%").list();
	}

	private final String selectByClasstype = "from ActivityBean  WHERE state=:state and classtype like :classtype ";

	@Override
	public List<ActivityBean> selectByClasstype(int state, String classtype) {
		return this.getSession().createQuery(selectByClasstype, ActivityBean.class).setParameter("state", state)
				.setParameter("classtype", "%" + classtype + "%").list();
	}

	private final String selectByNickname = "select act from ActivityBean act , MemberBean mem  where act.state=:state and act.hostid = mem.id and mem.nickname like :nickname";

	@Override
	public List<ActivityBean> selectByNickname(int state, String nickname) {
		return this.getSession().createQuery(selectByNickname, ActivityBean.class).setParameter("state", state)
				.setParameter("nickname", "%" + nickname + "%").list();
	}

	private final String selectByFees = "from ActivityBean  WHERE state=:state and feed <= : fees ORDER BY feed  ";

	@Override
	public List<ActivityBean> selectByFees(int state, int fees) {
		return this.getSession().createQuery(selectByFees, ActivityBean.class).setParameter("state", state)
				.setParameter("fees", fees).list();
	}

	private final String selectByClick = "from ActivityBean  WHERE state=:state ORDER BY click DESC ";

	@Override
	public List<ActivityBean> selectByClick(int state) {
		return this.getSession().createQuery(selectByClick, ActivityBean.class)
				.setParameter("state", state).list();
	}

	// indexPage;state=0:活動搜尋 ; state=1:心得PO文搜尋
	private String selectOfIndex = "select * from Activity  WHERE ";

	@Override
	public List<ActivityBean> selectOfIndex(int state, int city, String beginDate, String endDate, String classtype,
			String title) {
		String selectOfIndex = "select * from Activity  WHERE ";
		if (beginDate != "" && endDate != "" && beginDate.length() != 0 && endDate.length() != 0) {

			selectOfIndex = selectOfIndex + " actbegin >='" + beginDate + "'AND actend <='" + endDate + "'and [state]="+ state;
		} else if (beginDate == "" && endDate != "") {
			selectOfIndex = selectOfIndex + "actend <='" + endDate + "'and [state]=" + state;
		} else if (endDate == "" && beginDate != "") {
			selectOfIndex = selectOfIndex + "actbegin >='" + beginDate + "'and [state]=" + state;
		} else
			selectOfIndex = selectOfIndex + "[state]=" + state;

		if (city != 0)
			selectOfIndex = selectOfIndex + " and city=" + city;

		if (classtype != null && classtype.length() != 0) {
			String[] CTArray = classtype.split(",");
			if (CTArray.length == 1) {
				selectOfIndex = selectOfIndex + " and classtype=\'" + CTArray[0] + "\'";
			} else {
				selectOfIndex = selectOfIndex + " and classtype in(\'" + CTArray[0] + "\'";
				for (int i = 1; i < CTArray.length; i++) {
					selectOfIndex += ",'" + CTArray[i] + "\'";
				}
				selectOfIndex += ")";
			}
		}
		if (title != "" && title.length() != 0)
			selectOfIndex = selectOfIndex + " and title like \'%" + title + "%\'";

		return this.getSession().createNativeQuery(selectOfIndex, ActivityBean.class).list();
	}

	@Override
	public List<ActivityBean> selectOfIndexPo(int state, int city, String beginDate, String endDate, String classtype,
			String title, List<Integer> Actid) {
		String selectOfIndex = "select * from Activity  WHERE ";
		if (beginDate != "" && endDate != "" && beginDate.length() != 0 && endDate.length() != 0) {
			selectOfIndex = selectOfIndex+ " actbegin >='" + beginDate + "'AND actend <='" + endDate + "'and [state]="+ state;
				
		} else if (beginDate == "" && endDate != "") {
			selectOfIndex = selectOfIndex + "actend <='" + endDate + "'and [state]=" + state;
		} else if (endDate == "" && beginDate != "") {
			selectOfIndex = selectOfIndex + "actbegin >='" + beginDate + "'and [state]=" + state;
		} else
			selectOfIndex = selectOfIndex + "[state]=" + state;
		if (city != 0)
			selectOfIndex = selectOfIndex + " and city=" + city;
		if (classtype != null && classtype.length() != 0) {
			String[] CTArray = classtype.split(",");
			if (CTArray.length == 1) {
				selectOfIndex = selectOfIndex + " and classtype=\'" + CTArray[0] + "\'";
			} else {
				selectOfIndex = selectOfIndex + " and classtype in(\'" + CTArray[0] + "\'";
				for (int i = 1; i < CTArray.length; i++) {
					selectOfIndex += ",'" + CTArray[i] + "\'";
				}
				selectOfIndex += ")";
			}

		}
		if (title != "" && title.length() != 0)
			selectOfIndex = selectOfIndex + " and title like \'%" + title + "%\'";
		if (Actid.size() == 1) {
			selectOfIndex = selectOfIndex + " and id=\'" + Actid.get(0) + "\'";
		} else {
			selectOfIndex = selectOfIndex + " and id  in(\'" + Actid.get(0) + "\'";
			for (int i = 1; i < Actid.size(); i++) {
				selectOfIndex += ",'" + Actid.get(i) + "\'";
			}
			selectOfIndex += ")";
		}
		return this.getSession().createNativeQuery(selectOfIndex, ActivityBean.class).list();
	}

	private final String Hqlactivitymemberid = "FROM ActivityBean WHERE hostid = :hostid ";

	@Override
	public List<ActivityBean> selectBymemberid(int memberid) {
		return this.getSession().createQuery(Hqlactivitymemberid, ActivityBean.class).setParameter("hostid", memberid)
				.list();
	}
	
	private final String SelectByHostNowHql = Hqlactivitymemberid+"and actbegin > GETDATE() and state <> 2 order by actbegin asc";
	@Override
	public List<ActivityBean> selectByHostNow(int hostid){
		return this.getSession().createQuery(SelectByHostNowHql, ActivityBean.class)
				.setParameter("hostid", hostid)
				.list();
	}
	
	private final String SelectByHostHistoryHql = Hqlactivitymemberid+"and actbegin < GETDATE() and state <> 2 order by actbegin desc";
	@Override
	public List<ActivityBean> selectByHostHistory(int hostid){
		return this.getSession().createQuery(SelectByHostHistoryHql, ActivityBean.class)
				.setParameter("hostid", hostid)
				.list();
	}

	private String HqlGetActivityId = "SELECT id FROM ActivityBean WHERE HOSTID = :HID AND ACTBEGIN = :AB AND DATELINE = :DL";

	@Override
	public int getActivityId(int memberid, Date actbegin, Date dateline) {
		Query query = this.getSession().createQuery(HqlGetActivityId);
		query.setParameter("HID", memberid);
		query.setParameter("AB", actbegin);
		query.setParameter("DL", dateline);
		List<Integer> obj = query.list();
		return obj.get(0);
	}
	
	private final String selectByAttendMemberNowSql= "select {act.*} " + 
			"from activity act " + 
			"join (select * from attend where memberid  = :memberid and [state] = :state) att " + 
			"on act.state <> 2 and att.activityid = act.id and act.actbegin > GETDATE() " + 
			"order by act.actbegin asc ";
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ActivityBean> selectByAttendMemberStateNow(int memberid, int state){
		return (List<ActivityBean>)getSession().createSQLQuery(selectByAttendMemberNowSql)
				.addEntity("act", ActivityBean.class)
				.setParameter("memberid", memberid)
				.setParameter("state", state)
				.list();
	}
	
	private final String selectByAttendMemberHistorySql= "select {act.*} " + 
			"from activity act " + 
			"join (select * from attend where memberid  = :memberid and [state] = :state ) att " + 
			"on act.state <> 2 and att.activityid = act.id and act.actbegin < GETDATE() " + 
			"order by act.actbegin desc ";
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ActivityBean> selectByAttendMemberStateHistory (int memberid, int state){
		return (List<ActivityBean>)getSession().createSQLQuery(selectByAttendMemberHistorySql)
				.addEntity("act", ActivityBean.class)
				.setParameter("memberid", memberid)
				.setParameter("state", state)
				.list();
	}
}