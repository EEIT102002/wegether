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
	public ServiceBean selectid(Integer id) {

		return this.getSession().get(ServiceBean.class, id);
	}
	
	private final String st = "from ServiceBean  WHERE memberid = :memberid ORDER BY id DESC";
	@Override
	public List<ServiceBean> selectMemberId(Integer memberid) {
		
		return this.getSession().createQuery(st, ServiceBean.class).setParameter("memberid", memberid).list();
	}

	private final String st1="from ServiceBean as a where a.title like :title";
	@Override
	public List<ServiceBean> selectTitle(String title) {
		
		return this.getSession().createQuery(st1, ServiceBean.class).setParameter("title","%"+title+"%").list();
	}

	
	private final String st2 = "from ServiceBean  WHERE classtype = :classtype ORDER BY id DESC";
	@Override
	public List<ServiceBean> selectClassType(Integer classtype) {
		
		return this.getSession().createQuery(st2, ServiceBean.class).setParameter("classtype", classtype).list();
	}

	private final String st3="from ServiceBean as a where a.content like :content";
	@Override
	public List<ServiceBean> selectContent(String content) {
		
		return this.getSession().createQuery(st3, ServiceBean.class).setParameter("content", "%"+content+"%").list();
	}

	@Override
	public List<ServiceBean> select() {

		return this.getSession().createQuery("from ServiceBean", ServiceBean.class).list();
	}

	@Override
	public ServiceBean insert(ServiceBean bean) {
		if (bean != null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}

	@Override
	public ServiceBean update(Integer id, Integer memberid, java.util.Date asktime, String title, Integer classtype,
			String content) {
		ServiceBean temp = this.getSession().get(ServiceBean.class, id);
		if (temp != null) {
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
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		}

		return false;
	}

}
