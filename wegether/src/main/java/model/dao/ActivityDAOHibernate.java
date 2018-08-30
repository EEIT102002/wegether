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
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
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
				this.getSession().save(bean);
				return bean;
		}
		return null;
	}


	@Override
	public ActivityBean update(ActivityBean bean) {
		ActivityBean temp = this.getSession().get(ActivityBean.class, bean.getId());

		if(temp!=null) {
			temp.setHostid(bean.getHostid());
			temp.setCreatetime(bean.getCreatetime());
			temp.setTitle(bean.getTitle());
			temp.setCity(bean.getCity());
			temp.setAddr(bean.getAddr());
			temp.setPicture(bean.getPicture());
			temp.setActbegin(bean.getActbegin());
			temp.setActend(bean.getActend());
			temp.setDateline(bean.getDateline());
			temp.setClasstype(bean.getClasstype());
			temp.setContent(bean.getContent());
			temp.setNumberlimit(bean.getNumberlimit());
			temp.setFeed(bean.getFeed());
			temp.setState(bean.getState());
			temp.setRank1(bean.getRank1());
			temp.setRank2(bean.getRank2());
			temp.setRank3(bean.getRank3());
			temp.setJudges(bean.getJudges());
			temp.setForm(bean.getForm());
			temp.setClick(bean.getClick());
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

		

}
