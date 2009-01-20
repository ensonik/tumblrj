package org.mikem.tumblr.api;

import org.mikem.tumblr.api.http.ITumblrReader;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.TumblePost;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.Credentials;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.exceptions.TumblrJException;

public class TumblrService {
	private ITumblrReader reader;
	
	public User getUserInformation(Credentials credentials) throws TumblrJException {
		return reader.getUserInformation(credentials);
	}
	
	public void delete(String postId) throws TumblrJException {
		reader.delete(postId, new Credentials(null, null)); // FIXME Unimplemented / untested
	}
	
	public TumbleLog read(TumblrReadOptions readOptions, Credentials credentials) throws TumblrJException {
		return this.reader.read(readOptions, credentials);
	}
	
	public TumbleLog read(TumblrReadOptions readOptions) throws TumblrJException {
		return this.reader.read(readOptions, null);
	}
	
	public TumbleLog read() throws TumblrJException {
		return this.reader.read(null, null);
	}

	public void setReader(ITumblrReader reader) {
		this.reader = reader;
	}
	
	public TumblePost write(TumblePost post, Credentials credentials) {
		return reader.write(post, credentials);
	}
	
}
