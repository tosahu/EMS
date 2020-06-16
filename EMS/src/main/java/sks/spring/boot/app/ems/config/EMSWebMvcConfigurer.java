package sks.spring.boot.app.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import sks.spring.boot.app.ems.interceptor.EMSInterceptor;

@Component
public class EMSWebMvcConfigurer extends WebMvcConfigurerAdapter{
	@Autowired
	private EMSInterceptor emsInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registery) {
		
		registery.addInterceptor(emsInterceptor);
	}
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/employee").allowedOrigins("http://localhost:8080");
	}
	

}
