package org.mikem.tumblr.api;

import org.mikem.tumblr.api.read.TumblrReadOptions;
import org.mikem.tumblr.api.read.ITumblrReader;

public class TumblrService {
	private ITumblrReader reader;
	
	public TumbleLog read(TumblrReadOptions readOptions) throws Exception {
		this.reader.setTumblrReadOptions(readOptions);
		return this.reader.read();
	}

	public void setReader(ITumblrReader reader) {
		this.reader = reader;
	}
	
}
