package org.mikem.tumblrj.api;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Before;
import org.mikem.tumblrj.api.TumblrJService;
import org.mikem.tumblrj.api.http.TumblrConnectionOptions;
import org.mikem.tumblrj.api.http.TumblrHttpReader;
import org.mikem.tumblrj.api.util.Credentials;
import org.mikem.tumblrj.api.util.TumblrJProperties;

public abstract class BaseServiceTest {
	protected Configuration configuration = null;
	
	@Before
	public void before() throws Exception {
		this.configuration = new PropertiesConfiguration("private-test-config.properties");
	}
	
	protected Credentials getCredentials() {
		String email = configuration.getString("email");
		String password = configuration.getString("password");
		return new Credentials(email, password);
	}
	
	protected TumblrJService getService(TumblrConnectionOptions connectionOptions) throws Exception {
		if (connectionOptions == null) {
			connectionOptions = new TumblrConnectionOptions();
			connectionOptions.setName(configuration.getString("logname"));
		}
		
		TumblrHttpReader reader = new TumblrHttpReader();
		
		TumblrJProperties properties = new TumblrJProperties();
		reader.setProperties(properties);
		reader.setTumblrConnectionOptions(connectionOptions);
		
		TumblrJService service = new TumblrJService();
		service.setReader(reader);
		
		return service;
	}
	
}
