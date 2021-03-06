package org.mikem.tumblrj.api;

import junit.framework.Assert;

import org.junit.Test;
import org.mikem.tumblrj.api.TumblrJService;
import org.mikem.tumblrj.api.model.ConversationPost;
import org.mikem.tumblrj.api.model.LinkPost;
import org.mikem.tumblrj.api.model.PhotoPost;
import org.mikem.tumblrj.api.model.QuotePost;
import org.mikem.tumblrj.api.model.RegularPost;

public class ServiceWriteTest extends BaseServiceTest {

	@Test
	public void testWritePhotoWithSource() throws Exception {
		TumblrJService service = getService(null);
		
		PhotoPost post = new PhotoPost();
		post.setCaption("Photo caption");
		post.setClickThroughURL("http://www.mikemclean.ca");
		post.setSourceUrl("http://www.google.ca/intl/en_ca/images/logo.gif");
		
		PhotoPost savedPost = (PhotoPost) service.write(post, getCredentials());
		Assert.assertNotNull(savedPost.getId());
	}
	
	
	@Test
	public void testWriteConversation() throws Exception {
		TumblrJService service = getService(null);
		
		ConversationPost post = new ConversationPost();
		post.setText("Conversation text\nLine 2 of conversation");
		post.setTitle("Conversation title");
		
		ConversationPost savedPost = (ConversationPost) service.write(post, getCredentials());
		Assert.assertNotNull(savedPost.getId());
		Assert.assertEquals(2, savedPost.getLines().length);
	}
	
	@Test
	public void testWriteQuote() throws Exception {
		TumblrJService service = getService(null);
		
		QuotePost post = new QuotePost();
		post.setSource("Tumblr");
		post.setText("Test quote");
		
		QuotePost savedPost = (QuotePost) service.write(post, getCredentials());
		Assert.assertNotNull(savedPost.getId());
	}
	
	@Test
	public void testWriteLink() throws Exception {
		TumblrJService service = getService(null);
		
		LinkPost post = new LinkPost();
		post.setLinkText("Test link text");
		post.setLinkUrl("http://www.mikemclean.ca");
		post.setName("Test link");
		
		LinkPost savedPost = (LinkPost) service.write(post, getCredentials());
		Assert.assertNotNull(savedPost.getId());
	}
	
	@Test
	public void testWriteRegular() throws Exception {
		TumblrJService service = getService(null);
		
		RegularPost post = new RegularPost();
		post.setBody("Regular post body");
		post.setTitle("Regular post title");
		
		service.write(post, getCredentials());
	}
	
}
