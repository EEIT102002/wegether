package Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.MemberBean;

import model.dao.MemberDAO;

@Service
@SuppressWarnings("unchecked")
public class FbService {
	@Autowired
	MemberDAO memberDAO;
	private SimpleDateFormat simpleDateFormat;

	public MemberBean fbLogin(String account, String name, MemberBean bean) throws Exception {
		// MemberBean bean = memberDAO.selectByAccount(account);
		//
		//
		// if (bean != null) { // 資料庫有資料：登入
		// System.out.println("aaa");
		// memberDAO.selectByAccount(bean.getAccount());
		// return bean;
		// } else { // 資料庫無資料：註冊
		// }
		//

		MemberBean checkuser = memberDAO.selectByAccount(bean.getAccount());
		System.out.println(checkuser);

		if (checkuser == null) {
			bean.setAccount(account);
			bean.setName(name);
			bean.setPwd("E".getBytes());
			bean.setBirthday(simpleDateFormat.parse("1999-01-01"));
			System.out.println("temp:" + bean);
			return memberDAO.insert(bean);
		} else {
			System.out.println("相同帳號");
			return memberDAO.insert(bean);
		}
		
	}
}
