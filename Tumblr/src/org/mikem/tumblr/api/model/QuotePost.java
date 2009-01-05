package org.mikem.tumblr.api.model;

import org.apache.commons.httpclient.methods.PostMethod;
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
	
	@Override
	protected void doSetupPostParams(PostMethod post) {
		post.addParameter("quote", this.getText());
		if (this.source != null) {
			post.addParameter("source", this.getSource());
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
