package org.mikem.tumblr.api.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class TumblrJProperties extends PropertiesConfiguration {
	private static final long serialVersionUID = -4582386493548577276L;
	
	public TumblrJProperties(String filename) throws ConfigurationException {
		this.setFileName(filename);
		this.load();
	}
	
	public TumblrJProperties() throws ConfigurationException {
		super("org/mikem/tumblr/api/util/default-tumblrj-config.properties");
	}

	public String getReadPath() {
		return getString("path.read", "http://{0}.tumblr.com/api/read");
	}
	
	public String getAuthenticationPath() {
		return getString("path.authenticate", "http://www.tumblr.com/api/authenticate");
	}
	
	public String getWritePath() {
		return getString("path.write", "http://www.tumblr.com/api/write");
	}
	
	public String getDeletePath() {
		return getString("path.delete", "http://www.tumblr.com/api/delete");	
	}
}
