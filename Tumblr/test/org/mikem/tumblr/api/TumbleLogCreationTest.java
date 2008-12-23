package org.mikem.tumblr.api;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.mikem.tumblr.api.TumbleLog;

public class TumbleLogCreationTest {

	@Test
	public void testCreateWithLinksOnly() throws Exception {
		SAXReader reader = new SAXReader();
        Document document = reader.read("test/org/mikem/tumblr/resources/links.xml");
        
        TumbleLog log = new TumbleLog(document);
        
        this.testTumbleLogBaseInfo(log);
	}

	
	private void testTumbleLogBaseInfo(TumbleLog log) {
		Assert.assertEquals(log.getName(), "ensonik");
		Assert.assertEquals(log.getCname(), "abc.com");
		Assert.assertEquals(log.getTitle(), "the higher you fly");
		Assert.assertEquals(log.getTimezone(), "US/Eastern");
		Assert.assertTrue(log.getDescription().startsWith("The future you have "));
		Assert.assertEquals(new Integer(1), log.getTotal());
		Assert.assertEquals(new Integer(0), log.getStart());
		Assert.assertEquals(2, log.getFeeds().size());
		
		TumbleFeed feed = log.findFeedById("336011");
		Assert.assertNotNull(feed);
	}
	
	
}
