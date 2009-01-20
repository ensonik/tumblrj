package org.mikem.tumblr.exceptions;

public class TumblrPostNotFoundException extends TumblrJException {
	private static final long serialVersionUID = 2886447453664241655L;
	
	public TumblrPostNotFoundException(Exception e) {
		super(e);
	}

	public TumblrPostNotFoundException(String message) {
		super(message);
	}

}
