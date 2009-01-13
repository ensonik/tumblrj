package org.mikem.tumblr.api.http;

import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.TumblePost;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.Credentials;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.exceptions.TumblrJException;

public interface ITumblrReader {
	TumbleLog read(TumblrReadOptions readOptions, Credentials credentials) throws TumblrJException;
	void delete(String postId, Credentials credentials) throws TumblrJException;
	User getUserInformation(Credentials credentials) throws TumblrJException;
	TumblePost write(TumblePost post, Credentials credentials) throws TumblrJException;
}
