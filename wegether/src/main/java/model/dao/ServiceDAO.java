package model.dao;

import java.util.List;

import model.ServiceBean;

public interface ServiceDAO {
	public abstract ServiceBean select(Integer id );

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
