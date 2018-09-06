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
	private static ServiceDAO serviceDAO;


	public List<ServiceBean> select(ServiceBean bean) {
		System.out.println("ServiceService");
		List<ServiceBean> result = null;
		if(bean!=null && bean.getId()!=0) {
			ServiceBean temp = serviceDAO.selectid(bean.getId());
			if(temp!=null) {
				result = new ArrayList<ServiceBean>();
				result.add(temp);
			}
		} else {
			result = serviceDAO.select(); 
			
		}
		return result;
	}
	public ServiceBean insert(ServiceBean bean) {
		ServiceBean result = null;
		if(bean!=null) {
			result = serviceDAO.insert(bean);
		}
		return result;
	}
	public ServiceBean update(ServiceBean bean) {
		ServiceBean result = null;
		if(bean!=null) {
			result = serviceDAO.update(bean.getId(), bean.getMemberid(),
					bean.getAsktime(), bean.getTitle(), bean.getClasstype(),bean.getContent());
		}
		return result;
	}
	public boolean delete(ServiceBean bean) {
		boolean result = false;
		if(bean!=null) {
			result = serviceDAO.delete(bean.getId());
		}
		return result;
	}
}
