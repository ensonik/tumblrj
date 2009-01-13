package org.mikem.tumblr.api;

import org.junit.Test;
import org.mikem.tumblr.api.model.RegularPost;

public class ServiceWriteTest extends BaseServiceTest {

	@Test
	public void testWriteRegular() throws Exception {
		TumblrService service = getService(null);
		
		RegularPost post = new RegularPost();
		post.setBody("Regular post body");
		post.setTitle("Regular post title");
		
		service.write(post, getCredentials());
	}
	
}
