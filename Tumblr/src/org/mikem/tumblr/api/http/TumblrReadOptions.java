package org.mikem.tumblr.api.http;

import org.mikem.tumblr.api.util.TumblrType;

public class TumblrReadOptions {
	private boolean readPrivate = false;
	private int start;
	private int num;
	private String id;
	private TumblrType type = null;
	
	public boolean isReadPrivate() {
		return readPrivate;
	}
	public void setReadPrivate(boolean readPrivate) {
		this.readPrivate = readPrivate;
	}
	public TumblrType getType() {
		return type;
	}
	public void setType(TumblrType type) {
		this.type = type;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
