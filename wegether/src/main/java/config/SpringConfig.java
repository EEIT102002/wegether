package config;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.web.socket.WebSocketSession;

import model.ActivityBean;
import model.AttendBean;

@Configurable
@ComponentScan(basePackages= {"model","querylanguage"})
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
	@Scope("prototype")
	public JSONObject newJson() {
		return new JSONObject();
	}
	
	@Bean
	@Scope("prototype")
	public JSONArray newJsonArray() {
		return new JSONArray();
	}
	
	@Bean
	public List<WebSocketSession> websocketList(){
		return new ArrayList<WebSocketSession>();
	}
	
}
