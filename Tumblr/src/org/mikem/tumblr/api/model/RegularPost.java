package org.mikem.tumblr.api.model;

import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Element;
import org.mikem.tumblr.api.util.TumblrType;
import org.mikem.tumblr.api.util.XmlUtil;

public class RegularPost extends TumblePost {
	private String title;
	private String body;
	
	public RegularPost() { 
		setType(TumblrType.REGULAR);
	}
	
	public RegularPost(Element node) throws Exception {
		super(node);
		this.body = XmlUtil.getXPathValue(node, "regular-body");
		this.title = XmlUtil.getXPathValue(node, "regular-title");
	}

	@Override
	protected void doSetupPostParams(PostMethod post) {
		post.addParameter("title", this.getTitle());
		post.addParameter("body", this.getBody());
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
