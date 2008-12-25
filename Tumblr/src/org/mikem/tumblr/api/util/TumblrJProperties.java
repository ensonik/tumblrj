package org.mikem.tumblr.api.util;

import java.util.Properties;

public class TumblrJProperties extends Properties {
	private static final long serialVersionUID = -4582386493548577276L;

	public String getBaseUrl() {
		return getProperty("base.url");
	}
	
	public String getReadPath() {
		return getProperty("path.read");
	}
	
	public String getAuthenticationPath() {
		return getProperty("path.authenticate");
	}
	
	public String getDeletePath() {
		return getProperty("path.delete");	
	}
}
