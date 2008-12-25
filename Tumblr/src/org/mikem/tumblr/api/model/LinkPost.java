package org.mikem.tumblr.api.model;

import org.dom4j.Element;
import org.mikem.tumblr.api.util.XmlUtil;

public class LinkPost extends TumblePost {
	private String linkUrl;
	private String linkText;
	
	public LinkPost(Element node) throws Exception {
		super(node);
		this.linkUrl = XmlUtil.getXPathValue(node, "link-url");
		this.linkText = XmlUtil.getXPathValue(node, "link-text");
	}
	
	public LinkPost(String id) {
		super(id);
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public String getLinkText() {
		return linkText;
	}
}
