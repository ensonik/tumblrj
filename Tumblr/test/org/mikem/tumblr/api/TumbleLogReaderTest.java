package org.mikem.tumblr.api;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.mikem.tumblr.api.model.ConversationPost;
import org.mikem.tumblr.api.model.LinkPost;
import org.mikem.tumblr.api.model.PhotoPost;
import org.mikem.tumblr.api.model.QuotePost;
import org.mikem.tumblr.api.model.RegularPost;
import org.mikem.tumblr.api.model.TumbleFeed;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.VideoPost;
import org.mikem.tumblr.api.util.TumblrType;

public class TumbleLogReaderTest {

	@Test
	public void readVideo() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read("test/org/mikem/tumblr/resources/video.xml");
        
        TumbleLog log = new TumbleLog(document);
    	
        VideoPost post = (VideoPost) log.findPostById("68390729");

        this.testTumbleLogBaseInfo(log);
        Assert.assertEquals(1, log.getPosts().size());
		
        Assert.assertEquals(post.getCaption(), "Java Posse live episode");
        Assert.assertEquals(post.getSource(), "http://www.youtube.com/watch?v=n6CrBlrdz3E");
    }
	
	@Test
	public void readPhoto() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read("test/org/mikem/tumblr/resources/photos.xml");
        
        TumbleLog log = new TumbleLog(document);
    	
        PhotoPost post = (PhotoPost) log.findPostById("66329950");

        this.testTumbleLogBaseInfo(log);
        Assert.assertEquals(2, log.getPosts().size());
		
        Assert.assertEquals(5, post.getPhotos().size());
        Assert.assertEquals("http://media.tumblr.com/h2FhIRxAehthkm38nCqLRRCxo1_400.gif", post.getPhotos().get(500).getUrl());
        
        PhotoPost post2 = (PhotoPost) log.findPostById("66329887");
        Assert.assertEquals("Test i<i>mage</i>", post2.getCaption());
	}
	
	@Test
	public void readRegular() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read("test/org/mikem/tumblr/resources/regular.xml");
        
        TumbleLog log = new TumbleLog(document);
    	
        RegularPost post = (RegularPost) log.findPostById("66329517");

        this.testTumbleLogBaseInfo(log);
        Assert.assertEquals(1, log.getPosts().size());

        Assert.assertEquals("Test text", post.getTitle());
        Assert.assertEquals("<b>Test post information</b>", post.getBody());  
	}
	
	@Test
	public void readQuote() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read("test/org/mikem/tumblr/resources/quote.xml");
        
        TumbleLog log = new TumbleLog(document);
    	
        QuotePost post = (QuotePost) log.findPostById("66330000");

        this.testTumbleLogBaseInfo(log);
        Assert.assertEquals(1, log.getPosts().size());

        Assert.assertEquals("Here is my quote", post.getText());
        Assert.assertEquals("sr<strike>c</strike>", post.getSource());  
	}
	
	@Test
	public void readConversation() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read("test/org/mikem/tumblr/resources/conversation.xml");
        
        TumbleLog log = new TumbleLog(document);
    	
        ConversationPost post = (ConversationPost) log.findPostById("66330060");

        this.testTumbleLogBaseInfo(log);
        Assert.assertEquals(1, log.getPosts().size());
        Assert.assertEquals("http://ensonik.tumblr.com/post/66330060", post.getUrl());
        Assert.assertEquals(2, post.getLines().length);
        Assert.assertEquals("Dialogue!", post.getLines()[0]);
        Assert.assertEquals("Dialogue line 2", post.getLines()[1]);
        Assert.assertEquals("Me chatting title", post.getTitle());
        Assert.assertEquals("Dialoguetext", post.getText());
	}
	
	
	@Test
	public void readLinks() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read("test/org/mikem/tumblr/resources/links.xml");
        
        TumbleLog log = new TumbleLog(document);
        
        this.testTumbleLogBaseInfo(log);
        Assert.assertEquals(1, log.getPosts().size());

		LinkPost post = (LinkPost) log.findPostById("65974375");
		Assert.assertNotNull(post);
		Assert.assertEquals("http://ensonik.tumblr.com/post/65974375", post.getUrl());
		Assert.assertEquals(TumblrType.LINK, post.getType());
		Assert.assertEquals(1229818052, post.getUnixTimestamp());
		Assert.assertEquals("http://www.rolyn.co.uk/wub.htm", post.getFeedItem());
		Assert.assertEquals("336714", post.getFromFeedId());
		System.out.println(post.getDateGmt());
		// TODO: Assert GMT date
		
		Assert.assertEquals("Cool javascript effect", post.getLinkText());
		Assert.assertEquals("http://www.rolyn.co.uk/wub.htm", post.getLinkUrl());
	}

	
	private void testTumbleLogBaseInfo(TumbleLog log) {
		Assert.assertEquals(log.getName(), "ensonik");
		Assert.assertEquals(log.getCname(), "abc.com");
		Assert.assertEquals(log.getTitle(), "the higher you fly");
		Assert.assertEquals(log.getTimezone(), "US/Eastern");
		Assert.assertTrue(log.getDescription().startsWith("The future you have "));
		Assert.assertEquals(Integer.valueOf(1), log.getTotal());
		Assert.assertEquals(Integer.valueOf(0), log.getStart());
		Assert.assertEquals(2, log.getFeeds().size());
		
		TumbleFeed feed = log.findFeedById("336011");
		Assert.assertNotNull(feed);
		Assert.assertEquals("http://mikemclean.ca/muse/feed", feed.getUrl());
		Assert.assertEquals("link-description", feed.getType());
		Assert.assertEquals(1204, feed.getNextUpdateInSeconds());
		Assert.assertEquals("the lower you fall the higher you'll fly", feed.getTitle());
		Assert.assertEquals("true", feed.getErrorText());
	}
	
	
}
