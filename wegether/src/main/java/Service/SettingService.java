package Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.MemberBean;
import model.MemberInfoBean;
import model.SettingBean;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;
import model.dao.SettingDAO;
@Service
public class SettingService {

	@Autowired
	private SettingDAO settingDAO;
	@Autowired
	private SessionFactory sessionFactory;
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public SettingBean setSetting(SettingBean bean) {

		SettingBean result = settingDAO.select(bean.getMemberid());

		if (result == null) {
			return null;
		}
		int i = 0;
		if (bean.getBirthday() != null ) {
			result.setBirthday(bean.getBirthday());
			i++;
		}
		if (bean.getSex() != null ) {
			result.setSex(bean.getSex());
			i++;
		}
		if (bean.getJob() != null ) {
			result.setJob(bean.getJob());
			i++;
		}
		if (bean.getCity() != null ) {
			result.setCity(bean.getCity());
			i++;
		}
		if (bean.getAddr() != null ) {
			result.setAddr(bean.getAddr());
			i++;
		}
		if (bean.getTel() != null ) {
			result.setTel(bean.getTel());
			i++;
		}
		if (bean.getContent() != null ) {
			result.setContent(bean.getContent());
			i++;
		}
		if (bean.getFavorite() != null ) {
			result.setFavorite(bean.getFavorite());
			i++;
		}
		if (bean.getShowhost() != null ) {
			result.setShowhost(bean.getShowhost());
			i++;
		}
		if (bean.getShowactivity() != null ) {
			result.setShowactivity(bean.getShowactivity());
			i++;
		}
		if (bean.getShowarticle() != null ) {
			result.setShowarticle(bean.getShowarticle());
			i++;
		}
		if (bean.getRankscore() != null ) {
			result.setRankscore(bean.getRankscore());
			i++;
		}
		if (bean.getInvite() != null ) {
			result.setInvite(bean.getInvite());
			i++;
		}
		if (bean.getAddfriend() != null ) {
			result.setAddfriend(bean.getAddfriend());
			i++;
		}
		if (bean.getActivitychange() != null ) {
			result.setActivitychange(bean.getActivitychange());
			i++;
		}
		if (bean.getFriendarticle() != null ) {
			result.setFriendarticle(bean.getFriendarticle());
			i++;
		}
		if (bean.getFriendactivity() != null ) {
			result.setFriendactivity(bean.getFriendactivity());
			i++;
		}	
		if (bean.getActivitymsg() != null ) {
			result.setActivitymsg(bean.getActivitymsg());
			i++;
		}
		if (bean.getArticlemsg() != null ) {
			result.setArticlemsg(bean.getArticlemsg());
			i++;
		}
		
		getSession().flush();
		getSession().evict(result);
		result.setMemberBean(null);
		if (i > 0) {
			return result;
		} else {
			return null;
		}
	}
	
}
	