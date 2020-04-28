package com.nowcoder.community;

import com.nowcoder.community.controller.AlphaController;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.AlphaDaoHibernateImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

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
}
