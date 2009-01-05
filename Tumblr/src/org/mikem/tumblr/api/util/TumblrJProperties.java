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
		this("tumblrj-config.properties");
	}
	
	public String getBaseUrl() {
		return getString("base.url");
	}
	
	public String getReadPath() {
		return getString("path.read");
	}
	
	public String getAuthenticationPath() {
		return getString("path.authenticate");
	}
	
	public String getWritePath() {
		return getString("path.write");
	}
	
	public String getDeletePath() {
		return getString("path.delete");	
	}
}
