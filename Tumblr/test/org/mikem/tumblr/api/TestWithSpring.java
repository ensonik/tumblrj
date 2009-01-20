package org.mikem.tumblr.api;

import junit.framework.Assert;

import org.junit.Test;
import org.mikem.tumblr.api.model.RegularPost;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.TumblePost;
import org.mikem.tumblr.api.util.Credentials;
import org.mikem.tumblr.exceptions.InvalidTumblrCredentialsExeption;
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
	
	@Test(expected=InvalidTumblrCredentialsExeption.class) 
	public void testInvalidCredentialsException() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrService service = (TumblrService) context.getBean("service");
		
		service.write(new RegularPost(), null);
	}
	
	
	@Test
	public void testWrite() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-spring-context.xml");
		TumblrService service = (TumblrService) context.getBean("service");
		Credentials credentials = (Credentials) context.getBean("credentials");
		
		RegularPost post = new RegularPost();
		post.setBody("Test post");
		post.setPrivatePost(true);
		post.setTitle("Test post title");
		
		TumblePost savedPost = service.write(post, credentials);
	
		Assert.assertNotNull(savedPost);
		Assert.assertNotNull(savedPost.getId());
	}
}
