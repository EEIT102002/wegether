package model.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.ServiceBean;

@Repository
public class ServiceDAOHibernate implements ServiceDAO {

	 @Autowired
		private SessionFactory sessionFactory;
	    
	    public Session getSession() {
	    	return sessionFactory.getCurrentSession();
	    }
		@Override
		public ServiceBean select(Integer id) {
			
			return this.getSession().get(ServiceBean.class, id);
		}

		@Override
		public List<ServiceBean> select() {
			
			return this.getSession().createQuery("from ServiceBean",ServiceBean.class).list();
		}

		@Override
		public ServiceBean insert(ServiceBean bean) {
			if(bean!=null) {
				this.getSession().save(bean);
				return bean;
			}
			return null;
		}

		@Override
		public ServiceBean update(Integer id, Integer memberid,
				java.util.Date asktime, String title ,Integer classtype,String content) {
			ServiceBean temp = this.getSession().get(ServiceBean.class, id);
			if(temp!=null) {
				temp.setId(id);
				temp.setMemberid(memberid);
				temp.setAsktime(asktime);
				temp.setTitle(title);
				temp.setClasstype(classtype);
				temp.setContent(content);
				return temp;
			}
			return null;
		}
		   

		@Override
		public boolean delete(Integer id) {
			ServiceBean temp = this.getSession().get(ServiceBean.class, id);
			if(temp!=null) {
				this.getSession().delete(temp);
				return true;
			}
			
			return false;
		}

	}
