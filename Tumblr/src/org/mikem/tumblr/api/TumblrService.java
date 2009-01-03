package org.mikem.tumblr.api;

import org.mikem.tumblr.api.http.ITumblrReader;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.exceptions.TumblrJException;

public class TumblrService {
	private ITumblrReader reader;
	
	public User getUserInformation(String email, String password) throws TumblrJException {
		return reader.getUserInformation(email, password);
	}
	
	public void delete(String postId) throws TumblrJException {
		reader.delete(postId);
	}
	
	public TumbleLog read(TumblrReadOptions readOptions) throws TumblrJException {
		return this.reader.read(readOptions);
	}
	
	public TumbleLog read() throws TumblrJException {
		return this.reader.read(null);
	}

	public void setReader(ITumblrReader reader) {
		this.reader = reader;
	}
	
}
