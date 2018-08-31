package model.dao.implement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.ActivityBean;
import model.SettingBean;
import model.dao.SettingDAO;

@Repository
public class SettingDAOHibernate implements SettingDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	private SettingDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory= sessionFactory;
	}
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public SettingBean select(int memberid) {
		System.out.println("SettingBean  select");
		return this.getSession().get(SettingBean.class, memberid);
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public SettingBean update(SettingBean settingBean) {
			if(settingBean!=null) {
				SettingBean temp =this.getSession().get(SettingBean.class,settingBean.getMemberid());	
				if(temp!=null) {
				return (SettingBean) this.getSession().merge(settingBean);
				}
			}
			return null;
	}

}
