package org.mikem.tumblr.api.http;

import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.TumblePost;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.exceptions.TumblrJException;

public interface ITumblrReader {
	TumbleLog read(TumblrReadOptions readOptions) throws TumblrJException;
	void delete(String postId) throws TumblrJException;
	User getUserInformation(String email, String password) throws TumblrJException;
	void write(TumblePost post, String email, String password) throws TumblrJException;
}
