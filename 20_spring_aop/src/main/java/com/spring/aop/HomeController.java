package com.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:PositionContext.xml");
		
		IPosition boss 	   = (IPosition)context.getBean("classBoss");
		IPosition manager  = (IPosition)context.getBean("classManager");
		IPosition employee = (IPosition)context.getBean("classEmployee");
	
		boss.work();
		manager.work();
		employee.work();
		
		/////////////////////////////////////
		
//		boss.getWorkTime();
//		manager.getWorkTime();
//		employee.getWorkTime();
		
//		boss.normal("사장" , 1000);
//		manager.normal("관리자" , 600);
//		employee.normal("직원" , 200);
		
		
		//////////////////////////////////////
		
//		employee.mistake();
		
		return "home";
	}
	
}
