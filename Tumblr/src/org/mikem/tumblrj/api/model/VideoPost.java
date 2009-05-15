package org.mikem.tumblrj.api.model;

import java.text.ParseException;

import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Element;
import org.mikem.tumblrj.api.util.XmlUtil;

public class VideoPost extends TumblePost {
	private String caption;
	private String source;	
	
	public VideoPost(Element node) throws ParseException {
		super(node);
		this.caption = XmlUtil.getXPathValue(node, "video-caption");
		this.source = XmlUtil.getXPathValue(node, "video-source");
	}
	
	@Override
	protected void doSetupPostParams(PostMethod post) {
		
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	
}
