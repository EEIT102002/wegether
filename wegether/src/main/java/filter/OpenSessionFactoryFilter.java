package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

@WebFilter("/*")
public class OpenSessionFactoryFilter implements Filter {
	
	private SessionFactory sessionFactory;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		try {
//			HttpServletRequest req = (HttpServletRequest) request;
//			HttpServletResponse res = (HttpServletResponse) response;
			
			sessionFactory.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
			chain.doFilter(request, response);		
		}
	}

	@Override
	public void destroy() {	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		ApplicationContext context = (ApplicationContext)arg0.getServletContext()
				 .getAttribute(
						 WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		
	}

}
