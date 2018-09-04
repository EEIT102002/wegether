package config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.XmlViewResolver;

import model.dao.ArticleDAO;
import model.dao.implement.ArticleDAOHibernate;

@Configuration
@ComponentScan(basePackages = { "controller" })
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
	@Autowired
	private ServletContext servletContext;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		XmlViewResolver vr = new XmlViewResolver();
		vr.setLocation(new ServletContextResource(servletContext, "/WEB-INF/spring-mvc-views.xml"));
		registry.viewResolver(vr);
	}

}
