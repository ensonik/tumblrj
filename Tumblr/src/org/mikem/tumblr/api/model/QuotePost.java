package org.mikem.tumblr.api.model;

import org.dom4j.Element;
import org.mikem.tumblr.api.util.XmlUtil;

public class QuotePost extends TumblePost {
	private String text;
	private String source;
	
	public QuotePost(Element node) throws Exception {
		super(node);
		this.source = XmlUtil.getXPathValue(node, "quote-source");
		this.text = XmlUtil.getXPathValue(node, "quote-text");
	}
	
	public QuotePost(String id) {
		super(id);
	}

	public String getText() {
		return text;
	}

	public String getSource() {
		return source;
	}
	
	

}
