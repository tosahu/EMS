package sks.spring.boot.app.ems.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class EMSConfiguration {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
		
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2).select()
		         .apis(RequestHandlerSelectors.basePackage("sks.spring.boot.app.ems")).build();
		
	}
	

}
