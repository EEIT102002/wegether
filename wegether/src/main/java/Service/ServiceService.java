package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ServiceBean;
import model.dao.ServiceDAO;
import model.dao.implement.ServiceDAOHibernate;

@Service
public class ServiceService {
	@Autowired
	private ServiceDAO serviceDAO;

	public List<ServiceBean> select(ServiceBean bean) {
		System.out.println("ServiceService");
		List<ServiceBean> result = null;
		if (bean.getMemberid() != null) {

			System.out.println(bean.getMemberid());
			result = serviceDAO.selectMemberId(bean.getMemberid());
			System.out.println(result);
		} else {
			result = serviceDAO.select();
		}
		return result;
	}

	public ServiceBean insert(ServiceBean bean) {
		ServiceBean result = null;

		if (bean.getMemberid() != null && bean.getClasstype() != null && bean.getTitle() != null
				&& bean.getContent() != null) {
			result = serviceDAO.insert(bean);
		}
		return result;
	}

	public ServiceBean update(ServiceBean bean) {
		ServiceBean result = null;
		if (bean != null) {
			result = serviceDAO.update(bean.getId(), bean.getMemberid(), bean.getAsktime(), bean.getTitle(),
					bean.getClasstype(), bean.getContent());
		}
		return result;
	}

	public boolean delete(ServiceBean bean) {
		boolean result = false;
		if (bean != null) {
			result = serviceDAO.delete(bean.getId());
		}
		return result;
	}
}
