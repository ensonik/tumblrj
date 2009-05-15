package org.mikem.tumblrj.api;

import org.mikem.tumblrj.api.exceptions.TumblrJException;
import org.mikem.tumblrj.api.http.ITumblrReader;
import org.mikem.tumblrj.api.http.TumblrConnectionOptions;
import org.mikem.tumblrj.api.http.TumblrHttpReader;
import org.mikem.tumblrj.api.model.TumbleLog;
import org.mikem.tumblrj.api.model.TumblePost;
import org.mikem.tumblrj.api.model.User;
import org.mikem.tumblrj.api.util.Credentials;
import org.mikem.tumblrj.api.util.TumblrReadOptions;

/**
 * <p>
 * Service class that acts as a facade to the rest of the system.  This will configure
 * all other dependant objects with no nonsense defaults. You may want to override
 * those defaults which perfectly in your grasp!
 * </p>
 * 
 * <p>
 * Direct non-spring usage would look something along the lines of:
 * </p>
 * 
 * <pre>
 * TumblrService service = new TumblrService("myblog");
 * service.read();
 * </pre>
 * 
 * <p>
 * With spring, something like;
 * </p>
 * 
 * <pre>
 * &lt;bean id="tumblejService" class="org.mikem.tumblr.api.TumblrService"&gt;
 *    &lt;constructor index="0" value="myblog"/&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * @author Mike
 *
 */
public class TumblrJService {
	private ITumblrReader reader;
	private String blogName;
	
	public TumblrJService(String blogName) {
		this.blogName = blogName;
		
		TumblrHttpReader httpReader = new TumblrHttpReader();
		TumblrConnectionOptions options = new TumblrConnectionOptions();
		options.setName(blogName);
		httpReader.setTumblrConnectionOptions(options);
		
		this.reader = httpReader;
	}
	
	public TumblrJService() { }

	/**
	 * Get user information. Equivalent to the Tumblr /api/credentials call.
	 * 
	 * @param credentials
	 * @return
	 * @throws TumblrJException
	 */
	public User getUserInformation(Credentials credentials) throws TumblrJException {
		if (reader == null) {
			throw new IllegalStateException("A reader needs to be set");
		}
		
		return reader.getUserInformation(credentials);
	}
	
	
	/**
	 * Delete a post by id. Equivalient to the Tumblr /api/delete call.
	 * 
	 * @param postId
	 * @throws TumblrJException
	 */
	public void delete(String postId) throws TumblrJException {
		if (reader == null) {
			throw new IllegalStateException("A reader needs to be set");
		}

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
		if (reader == null) {
			throw new IllegalStateException("A reader needs to be set");
		}
		
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
		if (reader == null) {
			throw new IllegalStateException("A reader needs to be set");
		}
		
		return this.reader.read(readOptions, null);
	}

	/**
	 * Convenience method (since both TumblrReadOptions and Credentials arguments are optional)
	 *  
	 * @return
	 * @throws TumblrJException
	 */
	public TumbleLog read() throws TumblrJException {
		if (reader == null) {
			throw new IllegalStateException("A reader needs to be set");
		}

		return this.reader.read(null, null);
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
		if (reader == null) {
			throw new IllegalStateException("A reader needs to be set");
		}

		return reader.write(post, credentials);
	}

	
	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public void setReader(ITumblrReader reader) {
		this.reader = reader;
	}
}
