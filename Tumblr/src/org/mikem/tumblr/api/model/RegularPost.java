package org.mikem.tumblr.api.model;

import org.dom4j.Element;
import org.mikem.tumblr.api.util.XmlUtil;

public class RegularPost extends TumblePost {
	private String title;
	private String body;
	
	
	public RegularPost(Element node) throws Exception {
		super(node);
		this.body = XmlUtil.getXPathValue(node, "regular-body");
		this.title = XmlUtil.getXPathValue(node, "regular-title");
	}
	
	public RegularPost(String id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}
	
	
	
}
