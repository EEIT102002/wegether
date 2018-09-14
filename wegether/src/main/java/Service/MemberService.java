package Service;

import java.util.Arrays;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.MemberBean;
import model.MemberInfoBean;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private MemberInfoDAO memberInfoDAO;
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public MemberBean login(String account, String pwd) {
		MemberBean bean = memberDAO.selectByAccount(account);
		return checkPwd(bean, pwd);
	}
	
	private MemberBean checkPwd(MemberBean bean , String password) {
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] pass1 = password.getBytes();
				byte[] pass2 = bean.getPwd();
				if (Arrays.equals(pass1, pass2)) {
					return bean;
				}
			}
		}
		return null;
	}

	//0:失敗, 1:成功 , 2:與舊密碼重複 , 3:違反密碼規則
	public Integer changePassword(Integer id, String oldPassword, String newPassword) {
		MemberBean bean = memberDAO.select(id);
		bean = checkPwd(bean, oldPassword);		
		if (bean != null) {
			if(oldPassword.equals(newPassword)) {
				return 2;
			}
			if (newPassword != null && newPassword.length() != 0) {
				byte[] pass1 = newPassword.getBytes();
				bean.setPwd(pass1);
				return memberDAO.update(bean)? 1:0;
			}else {
				return 3;
			}
		}
		return 0;
	}

	public MemberInfoBean setMemberInfo(MemberBean bean) {

		MemberBean result = memberDAO.select(bean.getId());

		if (result == null) {
			return null;
		}
		int i = 0;
		if(bean.getPhoto() != null) {
			result.setPhoto(bean.getPhoto());
			i++;
		}
		if (bean.getNickname() != null ) {
			result.setNickname(bean.getNickname());
			i++;
		}
		if (bean.getJob() != null) {
			result.setJob(bean.getJob());
			i++;
		}
		if (bean.getCity() != null) {
			result.setCity(bean.getCity());
			i++;
		}
		if (bean.getAddr() != null) {
			result.setAddr(bean.getAddr());
			i++;
		}
		if (bean.getTel() != null) {
			result.setTel(bean.getTel());
			i++;
		}
		if (bean.getContent() != null) {
			result.setContent(bean.getContent());
			i++;
		}
		if (bean.getFavorite() != null) {
			result.setFavorite(bean.getFavorite());
			i++;
		}
		getSession().flush();
		
		if (i > 0) {
			return memberInfoDAO.select(result.getId());
		} else {
			return null;
		}
	}
}
