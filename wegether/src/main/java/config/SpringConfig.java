package config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import model.ActivityBean;
import model.AttendBean;

@Configurable
@ComponentScan(basePackages= {"model"})
public class SpringConfig {

	@Bean
	public DataSource dataSource() {
		try {
			JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
			bean.setJndiName("java:comp/env/jdbc/wegether");
			bean.setProxyInterface(DataSource.class);
			bean.afterPropertiesSet();
			return (DataSource) bean.getObject();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder =
				new LocalSessionFactoryBuilder(dataSource());
		builder.configure("hibernate.cfg.xml");
		return builder.buildSessionFactory();
	}
	@Bean 
	public Integer msgSelectLimit() {
		return 10; 
	}
	@Bean 
	public Integer noticeSelectLimit() {
		return 10;
	}
	
	@Bean 
	public String offsetSql() {
		return 	"offset :offset_first row fetch next :offset_max rows only ";
	}
	
	@Bean 
	public String noticeSelectByMemberIdSql() {
		return "select {n.*} from Notice n where memberid = :id order by noticetime desc "
				+offsetSql();
	}
	
	public String msgSelectSql(String tabelid) {
		return "select {m.*} from Msg m where "+tabelid+" = :id order by msgtime desc ";
	}
	
	
	@Bean 
	public String msgSelectByActivitySql() {
		return msgSelectSql("activityid");
	}
	
	@Bean 
	public String msgSelectByArticleSql() {
		return msgSelectSql("articleid");
	}
}
