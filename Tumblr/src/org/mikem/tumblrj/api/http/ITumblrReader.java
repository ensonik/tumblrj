package org.mikem.tumblrj.api.http;

import org.mikem.tumblrj.api.exceptions.TumblrJException;
import org.mikem.tumblrj.api.model.TumbleLog;
import org.mikem.tumblrj.api.model.TumblePost;
import org.mikem.tumblrj.api.model.User;
import org.mikem.tumblrj.api.util.Credentials;
import org.mikem.tumblrj.api.util.TumblrReadOptions;

public interface ITumblrReader {
	TumbleLog read(TumblrReadOptions readOptions, Credentials credentials) throws TumblrJException;
	void delete(String postId, Credentials credentials) throws TumblrJException;
	User getUserInformation(Credentials credentials) throws TumblrJException;
	TumblePost write(TumblePost post, Credentials credentials) throws TumblrJException;
}
