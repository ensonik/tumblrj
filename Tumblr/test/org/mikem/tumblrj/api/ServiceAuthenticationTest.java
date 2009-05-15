package org.mikem.tumblrj.api;

import junit.framework.Assert;

import org.junit.Test;
import org.mikem.tumblrj.api.TumblrJService;
import org.mikem.tumblrj.api.model.User;

public class ServiceAuthenticationTest extends BaseServiceTest {
	
	@Test
	public void testGetUserInformation() throws Exception {
		TumblrJService service = getService(null);
		
		User user = service.getUserInformation(getCredentials());
		Assert.assertNotNull(user);
	}
}
