package querylanguage;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QueryBean {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public <T> NativeQuery<T> getSelectQuery(String sql, int id ,Class<T> bean) {
		return getSession().createSQLQuery(sql)
				.addEntity("m", bean)
				.setParameter("id", id);
	}
	
	public <T> NativeQuery<T> getSelectQuery(String sql, int id, int first, int SelectLimit,Class<T> bean) {
		return getSelectQuery(sql, id, bean)
				.setParameter("offset_first", first)
				.setParameter("offset_max", SelectLimit);
	}
	
	public <T> List<T> getBeanList(NativeQuery<T> nq) {
		return (List<T>) nq.list();
	}
}
