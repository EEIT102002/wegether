package Service;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private Pattern pwdcheck = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=^\\S*$).{8,16}$");
	private Pattern telcheck =  Pattern.compile("[0-9]{10}");
	
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
	
	public void checkPwdRule(String pwd, String pwdrepeat, Map<String, String> errors ){
		
		if(!pwd.equals(pwdrepeat)) {
			errors.put("notrepeat", "重複密碼不相同");
		}
		if(!pwdcheck.matcher(pwd).matches()) {
			errors.put("repeat", "違反密碼規則: 至少有一個數字、小寫字母、大寫字母、非空白特殊符號，長度為8~16");
		}
		
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

	public MemberInfoBean setMemberInfo(MemberBean bean, Map<String, Object> errors) {

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
			if(bean.getNickname().trim().length()!= 0) {
				result.setNickname(bean.getNickname().trim());
				i++;
			}else {
				errors.put("error", "暱稱不能為空白");
			}	
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
			if(telcheck.matcher(bean.getTel()).matches()) {
				result.setTel(bean.getTel());
				i++;
			}else {
				errors.put("error", "手機號碼錯誤");
			}
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
