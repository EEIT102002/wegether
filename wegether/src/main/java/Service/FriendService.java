package Service;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Service.bean.LoginBean;
import model.FriendBean;
import model.dao.BlacklistDAO;
import model.dao.FriendDAO;

@Service
public class FriendService {
	@Autowired
	private FriendDAO friendDAO;


	
	public boolean changeStateByMemberF(FriendBean bean) {
		if(bean.getState() == null && bean.getMemberidf() == null) {
			return false;
		}
		FriendBean result = friendDAO.selectById(bean.getId());
		if(result != null && result.getMemberidf() == bean.getMemberidf() && result.getState() == 0) {
			bean.setState(bean.getState());
			return true;
		}else {
			return false;
		}		
	}
}
