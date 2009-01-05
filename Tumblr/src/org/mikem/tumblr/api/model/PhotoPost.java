package org.mikem.tumblr.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Element;
import org.dom4j.Node;
import org.mikem.tumblr.api.util.XmlUtil;

public class PhotoPost extends TumblePost {
	private String caption;
	private Map<Integer, Photo> photos = new HashMap<Integer, Photo>();
	private String sourceUrl;
	private String clickThroughURL;
	
	@SuppressWarnings("unchecked")
	public PhotoPost(Element node) throws Exception {
		super(node);
		this.caption = XmlUtil.getXPathValue(node, "photo-caption");
		
		List<Node> photoUrlNodes = (List<Node>) node.selectNodes("photo-url");
		for (Node photoNode : photoUrlNodes) {
			String url = photoNode.getText();
			Integer maxWidth = XmlUtil.getXPathValueAsInteger((Element) photoNode, "@max-width");
			photos.put(maxWidth, new Photo(url));
		}
	}

	@Override
	protected void doSetupPostParams(PostMethod post) {
		post.addParameter("source", this.getSourceUrl());
		if (this.caption != null) {
			post.addParameter("caption", this.getCaption());
		}
		if (this.clickThroughURL != null) {
			post.addParameter("click-through-url", this.getClickThroughURL().toString());
		}
	}
	
	public PhotoPost(String id) {
		super(id);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Map<Integer, Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Map<Integer, Photo> photos) {
		this.photos = photos;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getClickThroughURL() {
		return clickThroughURL;
	}

	public void setClickThroughURL(String clickThroughURL) {
		this.clickThroughURL = clickThroughURL;
	}

	
}
