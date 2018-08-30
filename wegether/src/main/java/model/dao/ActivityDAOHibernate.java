package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import model.ActivityBean;





@Repository
public class ActivityDAOHibernate implements ActivityDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {
		System.out.println("ActivityBean");
		
	}
	@Override
	public ActivityBean select(int id) {
		System.out.println("ActivityBean  select");

		return this.getSession().get(ActivityBean.class, id);
	
	}

	@Override
	public List<ActivityBean> select() {
		
		return this.getSession().createQuery(
				"from ActivityBean", ActivityBean.class).list();
	}

	@Override
	public ActivityBean insert(ActivityBean bean) {
		if(bean!=null) {
			int id = bean.getId();
			ActivityBean temp = this.getSession().get(ActivityBean.class, id);
			if(temp==null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	public ActivityBean update(Integer hostid, Date createtime, String title, Integer city, String addr, byte[] picture,
			Date actbegin, Date actend, Date dateline, String classtype, String content, Integer numberlimit,
			Integer feed, Integer state, Double rank1, Double rank2, Double rank3, Integer judges,
			String form, Integer click, int id) {
		ActivityBean temp = this.getSession().get(ActivityBean.class, id);
		if(temp!=null) {
			temp.setHostid(hostid);
			temp.setCreatetime(createtime);
			temp.setTitle(title);
			temp.setCity(city);
			temp.setAddr(addr);
			temp.setPicture(picture);
			temp.setActbegin(actbegin);
			temp.setActend(actend);
			temp.setDateline(dateline);
			temp.setClasstype(classtype);
			temp.setContent(content);
			temp.setNumberlimit(numberlimit);
			temp.setFeed(feed);
			temp.setState(state);
			temp.setRank1(rank1);
			temp.setRank2(rank2);
			temp.setRank3(rank3);
			temp.setJudges(judges);
			temp.setForm(form);
			temp.setClick(click);
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

	@Override
	public ActivityBean update(ActivityBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
