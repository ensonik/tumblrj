package org.mikem.tumblr.exceptions;

public class TumblrJException extends RuntimeException {
	private static final long serialVersionUID = 711719071686679477L;

	public TumblrJException(Exception e) {
		super(e);
	}
	
}
