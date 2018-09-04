package model.dao;

import java.util.List;

import model.ServiceBean;

public interface ServiceDAO {

	
	public abstract ServiceBean selectid(Integer id );
	
	public abstract List<ServiceBean> selectMemberId(Integer memberid );
	
	public abstract List<ServiceBean> selectTitle(String title );
	
	public abstract List<ServiceBean> selectClassType(Integer classtype );
	
	public abstract List<ServiceBean> selectContent(String content );

	public abstract List<ServiceBean> select();

	public abstract ServiceBean insert(ServiceBean bean);

	public abstract ServiceBean update(Integer id, Integer memberid,
			java.util.Date asktime, String title ,Integer classtype,String content);
	public abstract boolean delete(Integer id );
	
	//ServiceDAOHibernate
//	private Integer id ;
//	private Integer memberid ;
//	private java.util.Date asktime ;
//	private  String title ;
//	private Integer classtype ;
//	private String content ;
//	
}
