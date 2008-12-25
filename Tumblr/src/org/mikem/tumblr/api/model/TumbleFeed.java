package org.mikem.tumblr.api.model;

import org.dom4j.Element;
import org.mikem.tumblr.api.util.XmlUtil;

public class TumbleFeed {
	private String id;
	private String url;
	private String type;
	private int nextUpdateInSeconds;
	private Boolean error;
	private String title;
	private String errorText;
	
	public TumbleFeed(String id) {
		this.id = id;
	}
	
	public TumbleFeed(Element node) {
		this.id = XmlUtil.getXPathValue(node, "@id");
		this.url = XmlUtil.getXPathValue(node, "@url");
		this.title = XmlUtil.getXPathValue(node, "@title");
		this.type = XmlUtil.getXPathValue(node, "@import-type");
		this.nextUpdateInSeconds = XmlUtil.getXPathValueAsInteger(node, "@next-update-in-seconds");
		this.errorText = XmlUtil.getXPathValue(node, "@error-text");
		this.error = XmlUtil.getXPathValueAsBoolean(node, "@error");
	}
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNextUpdateInSeconds() {
		return nextUpdateInSeconds;
	}
	public void setNextUpdateInSeconds(int nextUpdateInSeconds) {
		this.nextUpdateInSeconds = nextUpdateInSeconds;
	}
	public Boolean getError() {
		return error;
	}
	public void setError(Boolean error) {
		this.error = error;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TumbleFeed)) {
			return false;
		}
		
		TumbleFeed feedArg = (TumbleFeed) obj;
		if (feedArg.getId() == null || this.id == null) {
			return false;
		}
		
		return feedArg.getId().equals(this.getId());
	}
	
}
