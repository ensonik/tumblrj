package org.mikem.tumblr.api.read;

import org.mikem.tumblr.api.TumbleLog;
import org.mikem.tumblr.api.util.TumblrConnectionOptions;

public interface ITumblrReader {
	void setTumblrConnectionOptions(TumblrConnectionOptions connectionOptions);
	void setTumblrReadOptions(TumblrReadOptions readOptions);
	TumbleLog read() throws Exception;
}
