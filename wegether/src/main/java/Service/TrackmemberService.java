package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ServiceBean;
import model.TrackmemberBean;
import model.TrackmemberId;
import model.dao.ServiceDAO;
import model.dao.TrackmemberDAO;
import model.dao.implement.ServiceDAOHibernate;

@Service
public class TrackmemberService {
	@Autowired
	private TrackmemberDAO trackmemberDAO;

	public TrackmemberBean insert(int mid, int fid) {
		TrackmemberBean result = null;
		if (mid != fid && mid != 0) {
			TrackmemberId id = new TrackmemberId();
			id.setFanid(fid); // 追蹤者ID
			id.setMemberid(mid);// 被追蹤ID
			TrackmemberBean bean = new TrackmemberBean();// NEW TrackmemberBean 來放TrackmemberId id
			bean.setId(id);

			// 寫入資料
			try {
				result = trackmemberDAO.insert(bean);// 加追蹤
			} catch (Exception e) {
			}
		}
		return result;
	}

	public boolean delete(int mid, int fid) {
		boolean result = false;

		if (mid != fid) {
			TrackmemberId id = new TrackmemberId();
			id.setFanid(fid); // 追蹤者ID
			id.setMemberid(mid);// 被追蹤ID
			TrackmemberBean bean = new TrackmemberBean();// NEW TrackmemberBean 來放TrackmemberId id
			bean.setId(id);

			// 寫入資料
			try {
				result = trackmemberDAO.delete(bean);// 取消追蹤
			} catch (Exception e) {
			}
		}
		return result;
	}
}
