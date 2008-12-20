package org.mikem.tumblr.api.util;

public enum TumblrType {
	REGULAR("regular"),LINK("link");
	
	String value;

	TumblrType(String value) {
		this.value = value;
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
		throw new IllegalArgumentException("Can't bind to TumblrType from value: " + value);
	}
}
