package com.nowcoder.community;

import com.nowcoder.community.controller.AlphaController;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.AlphaDaoHibernateImpl;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	public ApplicationContext applicationContext;

	// When the program runs, the parameter applicationContext will be assigned.
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// Record context
		this.applicationContext = applicationContext;
	}

	// Test Spring container
	@Test
	public void testApplicationContext() {
		// System.out.println(applicationContext);

		// Single implement class
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		// Get specific bean
		// AlphaDao alphaDao2 = (AlphaDaoHibernateImpl)applicationContext.getBean("alphaHibernate"); or
		AlphaDao alphaDao2 = applicationContext.getBean("alphaHibernate", AlphaDao.class);
		System.out.println(alphaDao2.select());
	}

	// Check container related methods called
	@Test
	public void testBeanManagement() {
		// Default singleton
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		AlphaService alphaService2 = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService == alphaService2);  // when scope is prototype, it is false
	}

	// Get bean(third party class) from applicationContext
	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	// Get bean by annotation
	@Autowired
	public AlphaDao dao;

	@Autowired
	@Qualifier("alphaHibernate")
	public AlphaDao dao2;

	@Autowired
	public SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI() {
		System.out.println(dao.select());
		System.out.println(dao2.select());
		System.out.println(simpleDateFormat.format(new Date()));
	}
}
