package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import model.ActivityBean;
import model.ActivityDAO;

public class ActivityDAOHibernate implements ActivityDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public ActivityBean select(Integer id) {
	
		return this.getSession().get(ActivityBean.class, id);
	}

	@Override
	public List<ActivityBean> select() {
		
		return this.getSession().createQuery("form ActivityBean",ActivityBean.class).list();
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

	@Override
	public ActivityBean update(Integer id, Integer hostid, Date createtime, String title, Integer city, String addr,
			byte[] picture, Date actbegin, Date actend, Date dateline, String classtype, String content,
			Integer numberlimit, Integer feed, Integer state, BigDecimal rank1, BigDecimal rank2, BigDecimal rank3,
			Integer judges, String form, Integer click) {

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
	public boolean delete(Integer id) {
		ActivityBean temp = this.getSession().get(ActivityBean.class, id);
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}

}
