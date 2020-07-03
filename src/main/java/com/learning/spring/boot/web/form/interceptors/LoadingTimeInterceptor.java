package com.learning.spring.boot.web.form.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor{

	private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);
			
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("Metodo del controlador: " + metodo.getMethod().getName());
		}		
		
		logger.info("LoadingTimeInterceptor: preHandle() start...");
		long initTime = System.currentTimeMillis();
		request.setAttribute("initTime", initTime);
		
		Random random = new Random();
		Integer delay = random.nextInt(500);
		Thread.sleep(delay);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		long initTime = System.currentTimeMillis();
		long finishTime = (Long) request.getAttribute("initTime");
		long timeLapsed = initTime - finishTime;
		
		if(handler instanceof HandlerMethod && modelAndView != null) {
			modelAndView.addObject("timeLapsed", timeLapsed);
		}		
		logger.info("Loading time: " + timeLapsed + "ms");
		logger.info("LoadingTimeInterceptor: posHandle() finish...");
	}

	
}
