package Service;

import java.util.ArrayList;
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

	public List<TrackmemberBean> insert(int mid) {
		List<TrackmemberBean> result = null;
		int fid = 2; // 手動帶入FanId

		TrackmemberId id = new TrackmemberId();
		id.setFanid(2); // 後端抓Fanid
		id.setMemberid(mid);

		if (mid != fid) {
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

	public boolean delete(int mid) {
		boolean result = false;
		TrackmemberId id = new TrackmemberId();
		int fid = 2; // 手動帶入FanId

		if (mid != fid) {
			id.setFanid(fid); // 系統抓資料
			id.setMemberid(mid);
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
