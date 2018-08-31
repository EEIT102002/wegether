package model.dao.implement;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MsgBean;
import model.NoticeBean;
import model.dao.NoticeDAO;
import querylanguage.Select;

@Repository
public class NoticeDAOHibernate implements NoticeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Integer noticeSelectLimit;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NoticeBean> selectByMemberId(Integer id, Integer first){
		
		return (List<NoticeBean>)getSession().createSQLQuery(Select.noticeByMember)
				.addEntity("n",NoticeBean.class)
				.setParameter("id", id)
				.setParameter("offset_first", first)
				.setParameter("offset_max", noticeSelectLimit)
				.list();
	}

}
