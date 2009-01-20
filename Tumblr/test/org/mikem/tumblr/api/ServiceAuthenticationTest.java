package org.mikem.tumblr.api;

import junit.framework.Assert;

import org.junit.Test;
import org.mikem.tumblr.api.model.User;

public class ServiceAuthenticationTest extends BaseServiceTest {
	
	@Test
	public void testGetUserInformation() throws Exception {
		TumblrService service = getService(null);
		
		User user = service.getUserInformation(getCredentials());
		Assert.assertNotNull(user);
	}
}
