package model.dao.implement;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MemberBean;
import model.MemberInfoBean;
import model.MsgBean;
import model.dao.MemberInfoDAO;
import querylanguage.Select;

@Repository
public class MemberInfoDAOHibernate implements MemberInfoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Integer selectLimit = 10;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public MemberInfoBean select(int id) {
		
		return getSession().get(MemberInfoBean.class, id);
	}
	@Override
	public List<MemberInfoBean> selectByNickname(String nickname) {
		return getSession().createQuery(Select.memberInfoByNickname,MemberInfoBean.class)
				.setParameter("nickname", "%"+nickname+"%").list();
	}
	@Override
	public List<MemberInfoBean> selectByNicknameForFriend(String nickname, Integer id) {
		return getBeanList(getSelectQuery(Select.memberByNicknameForFriend, nickname, id));
	}

	@Override
	public List<MemberInfoBean> selectByNicknameForTrack(String nickname, Integer id) {
		return getBeanList(getSelectQuery(Select.memberByNicknameForTrack, nickname, id));
	}

	@Override
	public List<MemberInfoBean> selectByNicknameForBlacklist(String nickname, Integer id) {
		return getBeanList(getSelectQuery(Select.memberByNicknameForBlacklist, nickname, id));
	}
	
	private NativeQuery<?> getSelectQuery(String sql, String nickname, int id) {
		return getSession().createSQLQuery(sql)
				.addEntity("m", MemberInfoBean.class)
				.setParameter("nickname", "%"+nickname+"%")
				.setParameter("id", id);
	}
	
	private NativeQuery<?> getSelectQuery(String sql, String nickname,int id, int first) {
		return getSelectQuery(sql,nickname, id)
				.setParameter("offset_first", first)
				.setParameter("offset_max", selectLimit);
	}
	
	@SuppressWarnings("unchecked")
	private List<MemberInfoBean> getBeanList(NativeQuery<?> nq) {
		return (List<MemberInfoBean>) nq.list();
	}



}
