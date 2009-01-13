package org.mikem.tumblr.api;

import junit.framework.Assert;

import org.junit.Test;
import org.mikem.tumblr.api.model.QuotePost;
import org.mikem.tumblr.api.model.TumbleFeed;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.api.util.TumblrType;

/**
 * This test case class isn't meant to be run standalone. Since it's a test
 * suite that hits the actual Tumblr servers, you'll need to change a few
 * of the included static variables to point it to your own log to get them to run.
 * 
 * @author Mike
 *
 */
public class ServiceReadTest extends BaseServiceTest {


	
	@Test
	public void testReadType() throws Exception {
		TumblrService service = getService(null);
		
		TumblrReadOptions readOptions = new TumblrReadOptions();
		readOptions.setType(TumblrType.QUOTE);
		
		TumbleLog log = service.read(readOptions);
		Assert.assertNotNull(log);
		Assert.assertEquals(1, log.getPosts().size());
		
		QuotePost post = (QuotePost) log.getPosts().get(0);
		Assert.assertTrue(post.getText().startsWith("If death"));
	}
	
	@Test
	public void testReadChunks() throws Exception {
		TumblrService service = getService(null);
	
		TumblrReadOptions readOptions = new TumblrReadOptions();
		readOptions.setStart(1);
		readOptions.setNum(1);
		
		// Get a post out and save it's id
		TumbleLog log = service.read(readOptions);
		Assert.assertEquals(1, log.getPosts().size());
		String postid = log.getPosts().get(0).getId();
		
		// Now get the second post, and make sure it's not the same id.
		readOptions.setStart(2);
		readOptions.setNum(1);
		log = service.read(readOptions);
		Assert.assertEquals(1, log.getPosts().size());
		Assert.assertNotSame(postid, log.getPosts().get(0).getId());
		
		// Read out a chunk of 3
		readOptions.setStart(2);
		readOptions.setNum(3);
		log = service.read(readOptions, null);
		Assert.assertEquals(3, log.getPosts().size());
	}
	
	@Test
	public void readById() throws Exception {
		TumblrService service = getService(null);
		
		TumblrReadOptions readOptions = new TumblrReadOptions();
		readOptions.setStart(1);
		readOptions.setNum(1);
		
		TumbleLog log = service.read(readOptions);
		String id = log.getPosts().get(0).getId();
		
		readOptions = new TumblrReadOptions();
		readOptions.setId(id);
		log = service.read(readOptions);
		Assert.assertEquals(id, log.getPosts().get(0).getId());
	}
	
	@Test
	public void testRead() throws Exception {
		TumblrService service = getService(null);
		TumbleLog log = service.read();
		
		Assert.assertEquals(log.getName(), this.configuration.getString("logname"));
		Assert.assertNull(log.getCname());
		Assert.assertEquals(log.getTitle(), "the higher you fly");
		Assert.assertEquals(log.getTimezone(), "US/Eastern");
		Assert.assertTrue(log.getDescription().startsWith("The future you have "));
		Assert.assertEquals(new Integer(10), log.getTotal());
		Assert.assertEquals(new Integer(0), log.getStart());
		Assert.assertEquals(2, log.getFeeds().size());
		
		TumbleFeed feed = log.findFeedById("336714");
		Assert.assertNotNull(feed.getId());
		Assert.assertEquals(feed.getUrl(), "http://feeds.delicious.com/v2/rss/ensonik?count=15");
		Assert.assertEquals(feed.getTitle(), "Delicious/ensonik");
		Assert.assertEquals(feed.getType(), "link");
	}
	

	
}
