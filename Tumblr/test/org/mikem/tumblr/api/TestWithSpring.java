package org.mikem.tumblr.api;

import junit.framework.Assert;

import org.junit.Test;
import org.mikem.tumblr.api.model.TumbleLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestWithSpring {

	@Test
	public void testRead() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrService service = (TumblrService) context.getBean("service");
		TumbleLog log = service.read();
		
		Assert.assertNotNull(log);		
	}
	
	
	
	
}
