package model.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.NoticeBean;
import model.NoticeviewBean;
import model.dao.NoticeviewDAO;
import querylanguage.Select;


@Repository
public class NoticeviewDAOHibernate implements NoticeviewDAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Integer noticeSelectLimit;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NoticeviewBean> selectByMemberId(Integer id, Integer first) {
		return (List<NoticeviewBean>) getSession().createSQLQuery(Select.noticeviewByMember)
				.addEntity("n", NoticeviewBean.class)
				.setParameter("id", id).setParameter("offset_first", first)
				.setParameter("offset_max", noticeSelectLimit).list();
	}

	@Override
	public List<NoticeviewBean> selectByActivityId(Integer id, Integer ntype) {
		return selectyByIdAndType(Select.noticeviewByActivity, id,ntype);
	}

	@Override
	public List<NoticeviewBean> selectByArticleId(Integer id, Integer ntype) {
		return selectyByIdAndType(Select.noticeviewByArticle, id,ntype);

	}

	@Override
	public List<NoticeviewBean> selectByFriendId(Integer id, Integer ntype) {
		return selectyByIdAndType(Select.noticeviewByFriend, id,ntype);
	}

	@Override
	public List<NoticeviewBean> selectByInviteId(Integer id, Integer ntype) {
		return selectyByIdAndType(Select.noticeviewByInvite, id,ntype);
	}

	@Override
	public List<NoticeviewBean> selectByAttendId(Integer id, Integer ntype) {
		return selectyByIdAndType(Select.noticeviewByAttend, id,ntype);
	}
	
	private Query<NoticeviewBean> selectByHql(String hql) {
		return getSession().createQuery(hql, NoticeviewBean.class);
	}
	
	private List<NoticeviewBean> selectyByIdAndType(String hql, Integer id, Integer ntype){
		return selectByHql(hql).setParameter("id", id).setParameter("ntype", ntype).list();
	}

}
