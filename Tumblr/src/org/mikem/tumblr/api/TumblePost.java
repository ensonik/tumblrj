package org.mikem.tumblr.api;

import java.net.URL;
import java.util.Date;

import org.dom4j.Document;

public class TumblePost {
	public String id;
	public URL url;
	public Date dateGmt;
	public Boolean bookmarklet;
	
	
	public TumblePost(Document document) {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public Date getDateGmt() {
		return dateGmt;
	}
	public void setDateGmt(Date dateGmt) {
		this.dateGmt = dateGmt;
	}
	public Boolean getBookmarklet() {
		return bookmarklet;
	}
	public void setBookmarklet(Boolean bookmarklet) {
		this.bookmarklet = bookmarklet;
	}
		
	
}
