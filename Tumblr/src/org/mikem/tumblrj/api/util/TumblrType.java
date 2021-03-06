package org.mikem.tumblrj.api.util;

import org.mikem.tumblrj.api.model.ConversationPost;
import org.mikem.tumblrj.api.model.LinkPost;
import org.mikem.tumblrj.api.model.PhotoPost;
import org.mikem.tumblrj.api.model.QuotePost;
import org.mikem.tumblrj.api.model.RegularPost;
import org.mikem.tumblrj.api.model.TumblePost;
import org.mikem.tumblrj.api.model.VideoPost;

public enum TumblrType {
	LINK("link", LinkPost.class),
	CONVERSATION("conversation", ConversationPost.class),
	QUOTE("quote", QuotePost.class),
	REGULAR("regular", RegularPost.class),
	PHOTO("photo", PhotoPost.class),
	VIDEO("video", VideoPost.class);
	
	String value;
	Class<? extends TumblePost> implementer;

	TumblrType(String value, Class<? extends TumblePost> implementer) {
		if (implementer == null) {
			throw new IllegalArgumentException("Can't init TumblrType without an implementing class for that type");
		}
		
		this.value = value;
		this.implementer = implementer;
	}
	
	public Class<? extends TumblePost> getImplementer() {
		return this.implementer;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static TumblrType fromString(String value) {
		for (TumblrType type : TumblrType.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Can't bind to TumblrType from string value: " + value);
	}
}
