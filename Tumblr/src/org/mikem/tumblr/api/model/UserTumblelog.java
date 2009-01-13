package org.mikem.tumblr.api.model;

import org.dom4j.Element;
import org.mikem.tumblr.api.util.XmlUtil;

public class UserTumblelog {
	private String title;
	private String type;
	private String privateId;
	private String name;
	private String url;
	private String avatarUrl;
	private boolean isPrimary;

	public UserTumblelog(Element node) throws Exception {
		this.title = XmlUtil.getXPathValue(node, "@title");
		this.name = XmlUtil.getXPathValue(node, "@name");
		this.url = XmlUtil.getXPathValue(node, "@url");
		this.type = XmlUtil.getXPathValue(node, "@type");
		this.avatarUrl = XmlUtil.getXPathValue(node, "@avatar-url");
		this.isPrimary = XmlUtil.getXPathValueAsBoolean(node, "@is-primary");
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
	public String getTitle() {
		return title;
	}
}
