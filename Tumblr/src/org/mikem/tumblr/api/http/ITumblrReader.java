package org.mikem.tumblr.api.http;

import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.TumblrReadOptions;

public interface ITumblrReader {
	TumbleLog read(TumblrReadOptions readOptions) throws Exception;
	void delete(String postId) throws Exception;
	User getUserInformation() throws Exception;
}
