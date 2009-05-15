package org.mikem.tumblrj.api.model;

import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Element;
import org.mikem.tumblrj.api.util.TumblrType;
import org.mikem.tumblrj.api.util.XmlUtil;

public class LinkPost extends TumblePost {
	private String linkUrl;
	private String linkText;
	private String name;
	
	public LinkPost() { 
		this.setType(TumblrType.LINK);
	}
	
	public LinkPost(Element node) throws Exception {
		super(node);
		this.linkUrl = XmlUtil.getXPathValue(node, "link-url");
		this.linkText = XmlUtil.getXPathValue(node, "link-text");
	}
	
	@Override
	protected void doSetupPostParams(PostMethod post) {
		if (this.name != null) {
			post.addParameter("name", this.getName());
		}
		post.addParameter("url", this.getLinkUrl());
		post.addParameter("description", this.getLinkText());
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
