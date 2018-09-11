//package Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import model.MemberBean;
//import model.MemberInfoBean;
//import model.dao.implement.MemberDAOHibernate;
//import model.dao.implement.MemberInfoDAOHibernate;
//@Service
//public class MemberRegisterService {
//	@Autowired
//	private MemberDAOHibernate memberDAOHibernate ;
//
//	public List<MemberBean> select(MemberBean bean) {
//		List<MemberBean> result = null;
//		if(bean!=null && bean.getId()!=0) {
//			MemberBean temp = memberDAOHibernate.select(bean.getId());
//			if(temp!=null) {
//				result = new ArrayList<MemberBean>();
//				result.add(temp);
//			}
//		} else {
//			result = memberDAOHibernate.select(); 
//		}
//		return result;
//	}
//	public MemberBean insert(MemberBean bean) {
//		MemberBean result = null;
//		if(bean!=null) {
//			result = memberDAOHibernate.insert(bean);
//		}
//		return result;
//	}
//	public MemberBean update(MemberBean bean) {
//		MemberBean result = null;
//		if(bean!=null) {
//			result =memberDAOHibernate.update(bean.getAccount(),bean.getPwd(), bean.getPhoto(),
//					bean.getName(), bean.getNickname(), bean.getBirthday(), 
//					bean.getSex(), bean.getJob(), bean.getCity(), bean.getAddr(), 
//					bean.getTel(), bean.getContent(), bean.getFavorite());
//		}
//		return result;
//	}
////	public boolean delete(MemberBean bean) {
////		boolean result = false;
////		if(bean!=null) {
////			result = memberDAOHibernate.delete(bean.getId());
////		}
////		return result;
////	}
//}
