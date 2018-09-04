package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.InviteBean;
import model.dao.InviteDAO;
import net.bytebuddy.asm.Advice.Return;

@Repository
public class InviteDAOHibernate implements InviteDAO {
	
    @Autowired
	private SessionFactory sessionFactory;
    
    private Session getSession() {
    	return sessionFactory.getCurrentSession();
    }
	@Override
	public InviteBean select(Integer id) {
		
		return this.getSession().get(InviteBean.class, id);
	}

	@Override
	public List<InviteBean> select() {
		
		return this.getSession().createQuery("from InviteBean",InviteBean.class).list();
	}

	@Override
	public InviteBean insert(InviteBean bean) {
		if(bean!=null) {
			
				this.getSession().save(bean);
				return bean;
		
		}
		
		return null;
	}

	@Override
	public InviteBean update(Integer id, Integer memberid, Integer invitedid, Integer activityid) {
		InviteBean temp = this.getSession().get(InviteBean.class, id);
		if(temp!=null) {
			temp.setId(id);
			temp.setMemberid(memberid);
			temp.setInvitedid(invitedid);
			temp.setActivityid(activityid);
			return temp;
		}
		return null;
	}
	   

	@Override
	public boolean delete(Integer id) {
		InviteBean temp = this.getSession().get(InviteBean.class, id);
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		
		return false;
	}

}

