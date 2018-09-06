package Service;

import java.util.Arrays;
import java.util.Map;

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

	public MemberBean login(String account, String password) {
		MemberBean bean = memberDAO.selectByAccount(account);
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

	public boolean changePassword(String username, String oldPassword, String newPassword) {
		MemberBean bean = this.login(username, oldPassword);
		if (bean != null) {
			if (newPassword != null && newPassword.length() != 0) {
				byte[] pass1 = newPassword.getBytes();
				bean.setPwd(pass1);
				return memberDAO.update(bean);
			}
		}
		return false;
	}

	public MemberInfoBean setMemberInfo(MemberBean bean) {

		MemberBean result = memberDAO.select(bean.getId());

		if (result == null) {
			return null;
		}
		int i = 0;
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
		if (i > 0) {
			return memberInfoDAO.select(result.getId());
		} else {
			return null;
		}
	}
}
