package org.mikem.tumblrj.api;

import junit.framework.Assert;

import org.junit.Test;
import org.mikem.tumblrj.api.TumblrJService;
import org.mikem.tumblrj.api.exceptions.InvalidTumblrCredentialsExeption;
import org.mikem.tumblrj.api.model.RegularPost;
import org.mikem.tumblrj.api.model.TumbleLog;
import org.mikem.tumblrj.api.model.User;
import org.mikem.tumblrj.api.util.Credentials;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestWithSpring {

	@Test
	public void testReadWithDefaultConfig() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrJService service = (TumblrJService) context.getBean("easyService");
		TumbleLog log = service.read();
		Assert.assertNotNull(log);
		Assert.assertTrue(log.getPosts().size() > 0);
	}
	
	@Test
	public void testRead() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrJService service = (TumblrJService) context.getBean("service");
		TumbleLog log = service.read();
		
		Assert.assertNotNull(log);		
	}
	
	@Test(expected=InvalidTumblrCredentialsExeption.class) 
	public void testInvalidCredentialsException() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrJService service = (TumblrJService) context.getBean("service");
		
		service.write(new RegularPost(), null);
	}
	
	@Test
	public void testReadAuthenticationInformaiton() {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrJService service = (TumblrJService) context.getBean("service");
		Credentials credentials = (Credentials) context.getBean("credentials");
		
		User user = service.getUserInformation(credentials);
		Assert.assertNotNull(user);
		Assert.assertTrue(user.getUserTumblelogs().size() > 0);
	}
	
	
	@Test
	public void testWrite() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrJService service = (TumblrJService) context.getBean("service");
		Credentials credentials = (Credentials) context.getBean("credentials");
		
		RegularPost post = new RegularPost();
		post.setBody("Test post");
		post.setPrivatePost(true);
		post.setTitle("Test post title");
		
		RegularPost savedPost = (RegularPost) service.write(post, credentials);
	
		Assert.assertNotNull(savedPost);
		Assert.assertNotNull(savedPost.getId());
	}
}
