package sks.spring.boot.app.ems.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class EMSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("It is calling before calling Dispatcher Servlet");
		System.out.println("getRemoteAddr " + request.getRemoteAddr());
		System.out.println("getRemoteHost  "+ request.getRemoteHost());
		System.out.println("getRemotePort " + request.getRemotePort());
		System.out.println("getServerPort " + request.getServerPort());
		
		chain.doFilter(request, response);
		
		
	}

}
