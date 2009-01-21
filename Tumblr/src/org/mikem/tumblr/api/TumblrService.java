package org.mikem.tumblr.api;

import org.mikem.tumblr.api.http.ITumblrReader;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.TumblePost;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.Credentials;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.exceptions.TumblrJException;

/**
 * Service class that acts as a facade to the rest of the system. 
 * 
 * TumblrHttpReader an be used directly, but it might be a better idea
 * to configure and use this instead.
 * 
 * Direct usage would look something along the lines of:
 * 
 * TumblrConnectionOptions connectionOptions = new TumblrConnectionOptions();
 * connectionOptions.setName("blogname");
 * 
 * TumblrHttpReader reader = new TumblrHttpReader();
 * reader.setConnectionOptions(connectionOptions);
 * 
 * TumblrService service = new TumblrService()
 * service.setReader(reader);
 * 
 * service.read();
 * 
 * @author Mike
 *
 */
public class TumblrService {
	/**
	 * The rest of the class acts a facade to an implementation of ITumblrReader. Make sure
	 * to set this variable before making any calls.
	 */
	private ITumblrReader reader;
	
	/**
	 * Get user information. Equivalent to the Tumblr /api/credentials call.
	 * 
	 * @param credentials
	 * @return
	 * @throws TumblrJException
	 */
	public User getUserInformation(Credentials credentials) throws TumblrJException {
		return reader.getUserInformation(credentials);
	}
	
	
	/**
	 * Delete a post by id. Equivalient to the Tumblr /api/delete call.
	 * 
	 * @param postId
	 * @throws TumblrJException
	 */
	public void delete(String postId) throws TumblrJException {
		reader.delete(postId, new Credentials(null, null)); // FIXME Unimplemented / untested
	}
	
	/**
	 * Reads posts from the tumblr log  identified in the HttpConnectionOptions. Credentials are optional,
	 * but mind you, an exception will be thrown if private posts are requested and the credentials aren't set.
	 * 
	 * The ReadOptions are equally optional, but you may want to use them to ensure you filter the result set
	 * to avoid returning all posts.
	 * 
	 * This is the equivalent to the /api/read Tumblr api call.
	 *  
	 * @param readOptions
	 * @param credentials
	 * @return
	 * @throws TumblrJException
	 */
	public TumbleLog read(TumblrReadOptions readOptions, Credentials credentials) throws TumblrJException {
		return this.reader.read(readOptions, credentials);
	}
	
	/**
	 * Convenience method (since the Credentials argument can be null);
	 * 
	 * @param readOptions
	 * @return
	 * @throws TumblrJException
	 */
	public TumbleLog read(TumblrReadOptions readOptions) throws TumblrJException {
		return this.reader.read(readOptions, null);
	}

	/**
	 * Convenience method (since both TumblrReadOptions and Credentials arguments are optional)
	 *  
	 * @return
	 * @throws TumblrJException
	 */
	public TumbleLog read() throws TumblrJException {
		return this.reader.read(null, null);
	}

	public void setReader(ITumblrReader reader) {
		this.reader = reader;
	}

	/**
	 * Writes a post. Credentials are madatory. Any extending class of TumblePost are accepted. Will
	 * return a new TumblePost freshly pulled out using a read() call.
	 * 
	 * Equivalent to the Tumblr /api/write api call..
	 * 
	 * @param post
	 * @param credentials
	 * @return
	 */
	public TumblePost write(TumblePost post, Credentials credentials) {
		return reader.write(post, credentials);
	}
	
}
