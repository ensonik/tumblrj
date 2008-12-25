package org.mikem.tumblr.api.model;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.mikem.tumblr.api.util.XmlUtil;

public class TumbleLog {
	private String name;
	private String cname;
	private String timezone;
	private String title;
	private String description;
	private List<TumbleFeed> feeds = new ArrayList<TumbleFeed>();
	private Integer start;
	private Integer total;
	private List<TumblePost> posts = new ArrayList<TumblePost>();
	
	@SuppressWarnings("unchecked")
	public TumbleLog(Document document) throws Exception {
		Element root = document.getRootElement();
		this.name = XmlUtil.getXPathValue(root, "//tumblr/tumblelog/@name");
		this.cname = XmlUtil.getXPathValue(root, "//tumblr/tumblelog/@cname");
		this.timezone = XmlUtil.getXPathValue(root, "//tumblr/tumblelog/@timezone");
		this.title = XmlUtil.getXPathValue(root, "//tumblr/tumblelog/@title");
		this.description = XmlUtil.getXPathValue(root, "//tumblr/tumblelog");
		this.start = XmlUtil.getXPathValueAsInteger(root, "//tumblr/posts/@start");
		this.total = XmlUtil.getXPathValueAsInteger(root, "//tumblr/posts/@total");
		
		List<Node> feeds = (List<Node>) root.selectNodes("//tumblr/tumblelog/feeds/feed");
		for (Node feedNode : feeds) {
			this.feeds.add(new TumbleFeed((Element) feedNode));
		}
		
		List<Node> posts = (List<Node>) root.selectNodes("//tumblr/posts/post");
		for (Node postNode : posts) {
			this.posts.add(TumblePost.createPostFromXml((Element) postNode));
		}
	}

	public TumbleFeed findFeedById(String id) {
		if (feeds == null || feeds.isEmpty()) {
			return null;
		}
		
		return this.feeds.get(this.feeds.indexOf(new TumbleFeed(id)));
	}
	
	public TumblePost findPostById(String id) {
		if (posts == null || posts.isEmpty()) {
			return null;
		}
		
		return this.posts.get(this.posts.indexOf(new TumblePost(id)));
	}
	
	public List<TumbleFeed> getFeeds() {
		return feeds;
	}
	public void setFeeds(List<TumbleFeed> feeds) {
		this.feeds = feeds;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<TumblePost> getPosts() {
		return posts;
	}
	public void setPosts(List<TumblePost> posts) {
		this.posts = posts;
	}
}
