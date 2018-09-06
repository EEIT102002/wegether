package Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.MemberBean;
import model.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	public MemberBean login(String account, String password) {
		MemberBean bean = memberDAO.selectByAccount(account);
		if(bean!=null) {
			if(password!=null && password.length()!=0) {
				byte[] pass1 = password.getBytes();	
				byte[] pass2 = bean.getPwd();	
				if(Arrays.equals(pass1, pass2)) {
					return bean;
				}
			}
		}
		return null;
	}

	public boolean changePassword(String username, String oldPassword, String newPassword) {
		MemberBean bean = this.login(username, oldPassword);
		if(bean!=null) {
			if(newPassword!=null && newPassword.length()!=0) {
				byte[] pass1 = newPassword.getBytes();
				bean.setPwd(pass1);
				return memberDAO.update(bean);
			}
		}
		return false;
	}
}
