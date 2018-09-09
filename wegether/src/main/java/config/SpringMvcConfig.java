package config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.XmlViewResolver;

import interceptor.LoginInterceptor;

@Configuration
@ComponentScan(basePackages = { "controller", "webstock" })
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ApplicationContext context;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		XmlViewResolver vr = new XmlViewResolver();
		vr.setLocation(new ServletContextResource(servletContext, "/WEB-INF/spring-mvc-views.xml"));
		registry.viewResolver(vr);
	}

	@Bean
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSize(1000000);
		return cmr;
	}
	
	@Bean
	LoginInterceptor localInterceptor() {
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localInterceptor());
	}

}
