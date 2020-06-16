package sks.spring.boot.app.ems.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class EMSInterceptor implements HandlerInterceptor{

	@Override
	public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("It will be called before sending request to Backend Controller");
		request.setAttribute("extraParameter", "port");
		return true;
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.setStatus(2000);
		response.setHeader("language", "English");
		System.out.println("It will be called before sending response to Client");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
			System.out.println("It will be called after sending request to Client");
	}
	
}
