package org.mikem.tumblr.api.model;

import org.dom4j.Element;

public class UserTumblelog {
	private String type;
	private String privateId;
	private String name;
	private String url;
	private String avatarUrl;
	private boolean isPrimary;

	public UserTumblelog(Element node) throws Exception {
		
	}
	
	public String getType() {
		return type;
	}
	public String getPrivateId() {
		return privateId;
	}
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	
	
}
