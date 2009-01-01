package org.mikem.tumblr.api;

import org.mikem.tumblr.api.http.ITumblrReader;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.TumblrReadOptions;

public class TumblrService {
	private ITumblrReader reader;
	
	public User getUserInformation() throws Exception {
		return reader.getUserInformation();
	}
	
	public void delete(String postId) throws Exception {
		reader.delete(postId);
	}
	
	public TumbleLog read(TumblrReadOptions readOptions) throws Exception {
		return this.reader.read(readOptions);
	}
	
	public TumbleLog read() throws Exception {
		return this.reader.read(null);
	}

	public void setReader(ITumblrReader reader) {
		this.reader = reader;
	}
	
}
