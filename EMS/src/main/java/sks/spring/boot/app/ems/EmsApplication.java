package sks.spring.boot.app.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableSwagger2
@EnableHystrix
public class EmsApplication {


	
	public static void main(String[] args) {
		
		
		SpringApplication.run(EmsApplication.class, args);
		
		
	}
	
}
