package org.mikem.tumblr.api.model;

import java.lang.reflect.Constructor;
import java.util.Date;

import org.dom4j.Element;
import org.mikem.tumblr.api.util.TumblrType;
import org.mikem.tumblr.api.util.XmlUtil;

public class TumblePost {
	private String id;
	private String url;
	private Date dateGmt;
	private TumblrType type;
	private long unixTimestamp;
	private String feedItem;
	private String fromFeedId;
	
	/**
	 * Factory method that will create the proper type of TumblrPost based off the type
	 * found in the xml.
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public static TumblePost createPostFromXml(Element node) throws Exception {
		String xmlType = XmlUtil.getXPathValue(node, "@type");
		TumblrType type = TumblrType.fromString(xmlType);
		
		Constructor<? extends TumblePost> c = type.getImplementer().getConstructor(Element.class);
		return (TumblePost) c.newInstance(node);
	}
	
	public TumblePost(String id) {
		this.id = id;
	}
	
	public TumblePost(Element node) throws Exception {
		this.id = XmlUtil.getXPathValue(node, "@id");
		this.url = XmlUtil.getXPathValue(node, "@url");
		this.type = TumblrType.fromString(XmlUtil.getXPathValue(node, "@type"));
		this.dateGmt = XmlUtil.getXPathValueAsDate(node, "@date-gmt");
		this.unixTimestamp = Long.valueOf(XmlUtil.getXPathValue(node, "@unix-timestamp"));
		this.feedItem = XmlUtil.getXPathValue(node, "@feed-item");
		this.fromFeedId = XmlUtil.getXPathValue(node, "@from-feed-id");
	}
	
	public String getFeedItem() {
		return feedItem;
	}
	public String getFromFeedId() {
		return fromFeedId;
	}
	public String getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}
	public Date getDateGmt() {
		return dateGmt;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}
	public TumblrType getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof TumblePost)) {
			return false;
		}
		
		TumblePost argPost = (TumblePost) arg0;
		
		if (this.id == null || argPost.getId() == null) {
			return false;
		}
		
		return this.id.equals(argPost.getId());
	}
}
