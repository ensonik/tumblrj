package org.mikem.tumblr.api.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.mikem.tumblr.api.model.TumbleLog;
import org.mikem.tumblr.api.model.TumblePost;
import org.mikem.tumblr.api.model.User;
import org.mikem.tumblr.api.util.TumblrJProperties;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.exceptions.TumblrJException;

public class TumblrHttpReader implements ITumblrReader {
    private Log logger = LogFactory.getLog(TumblrHttpReader.class);
	private TumblrConnectionOptions connectionOptions;
	private TumblrJProperties properties;
	
	public User getUserInformation(String email, String password) throws TumblrJException {
		if (this.connectionOptions == null) {
			throw new IllegalStateException("Can't connect up because reader doesn't have a configured TumblrConnectionOptions");
		}
				
		try {
			HttpClient client = setupHttpClient();
			PostMethod post = setupPostMethod(properties.getAuthenticationPath());
			setAuthenticationInformation(post, email, password);
			
			logger.debug("Posting request to get user information : " + post.getURI().toString() + " with email " + email);
	
			client.executeMethod(post);
			String response = post.getResponseBodyAsString();
			
			logger.trace("Received response: " + response);
			
			post.releaseConnection();
			
			Document doc = DocumentHelper.parseText(response);
			return new User(doc);
		} catch (Exception e) {
			throw new TumblrJException(e);
		}
	}
	
	public void delete(String postId) throws TumblrJException {
		if (this.connectionOptions == null) {
			throw new IllegalStateException("Can't connect up because reader doesn't have a configured TumblrConnectionOptions");
		}
				
		try {
			HttpClient client = setupHttpClient();
			PostMethod post = setupPostMethod(properties.getDeletePath());
			setAuthenticationInformation(post, this.connectionOptions.getEmail(), this.connectionOptions.getPassword());
			post.addParameter("post-id", postId);
			
			logger.debug("Posting delete request to : " + post.getURI().toString() + " with post-id " + postId);
	
			client.executeMethod(post);
			String response = post.getResponseBodyAsString();
			
			logger.trace("Received response: " + response);
	
			post.releaseConnection();
		} catch (Exception e) {
			throw new TumblrJException(e);
		}
	}
	
	public TumbleLog read(TumblrReadOptions readOptions) throws TumblrJException {
		if (this.connectionOptions == null) {
			throw new IllegalStateException("Can't connect up because reader doesn't have a configured TumblrConnectionOptions");
		}
        
        try {
    	    HttpClient client = setupHttpClient();
            PostMethod post = setupPostMethod(properties.getReadPath());
            setPostParameters(post, readOptions);
        	
        	logger.debug("Posting read request to : " + post.getURI().toString());
	        
	        client.executeMethod(post);
	       	
	        String response = post.getResponseBodyAsString();
	        post.releaseConnection();
	        
	        logger.debug("Received response for read request: " + response);

        	Document doc = DocumentHelper.parseText(response);
	        TumbleLog log = new TumbleLog(doc);
	        return log;
        } catch (Exception e) {
        	throw new TumblrJException(e);
        }
	}
	
	// FIXME Return something!
	public void write(TumblePost tumblrPost, String email, String password) throws TumblrJException {
		try {
			HttpClient client = setupHttpClient();
			PostMethod post = setupPostMethod(properties.getWritePath());
			setAuthenticationInformation(post, email, password);
			tumblrPost.setupPostParams(post);
			
        	logger.debug("Posting write request to : " + post.getURI().toString());
			
			client.executeMethod(post);
			String response = post.getResponseBodyAsString();
			
			logger.debug("Received response for write request: " + response);
		} catch (Exception e) {
			throw new TumblrJException(e);
		}
	}
	
	private PostMethod setupPostMethod(String path) {
		String baseurl = StringUtils.replace(properties.getBaseUrl(), "{0}", this.connectionOptions.getName());
		return new PostMethod(baseurl + path);
	}
	
	private HttpClient setupHttpClient() {
		HttpClient client = new HttpClient();
	    if (this.connectionOptions.getHttpClientParams() != null) {
	    	client.setParams(this.connectionOptions.getHttpClientParams());
	    }
	    return client;
	}
	
	private void setAuthenticationInformation(PostMethod post, String email, String password) {
		addPostParam(post, "email", email);
		addPostParam(post, "password", password, true);
	}

	
	private void setPostParameters(PostMethod post, TumblrReadOptions readOptions) {
		if (readOptions == null) {
			return;
		}
		
		boolean idUsed = false;
		
		if (readOptions.getId() != null && !("").equals(readOptions.getId())) {
			addPostParam(post, "id", readOptions.getId());
		}
		
		if (readOptions.getNum() > 0 && !idUsed) {
			addPostParam(post, "num", String.valueOf(readOptions.getNum()));
		}

		if (readOptions.getStart() > 0 && !idUsed) {
			addPostParam(post, "start", String.valueOf(readOptions.getStart()));
		}

		if (readOptions.getType() != null && !idUsed) {
			addPostParam(post, "type", readOptions.getType().getValue());
		}

		if (readOptions.isReadPrivate()) {
			addPostParam(post, "private", "true");
			setAuthenticationInformation(post, this.connectionOptions.getEmail(), this.connectionOptions.getPassword());
		}
	}
	
	private void addPostParam(PostMethod post, String paramName, String paramValue, boolean obfuscateLogValue) {
		post.addParameter(paramName, paramValue);
		logger.trace("Setting up post param : param with name " + paramName + " added with value " + (obfuscateLogValue ? "****" : paramValue));
	}
		
	private void addPostParam(PostMethod post, String paramName, String paramValue) {
		addPostParam(post, paramName, paramValue, false);
	}
	
	public void setTumblrConnectionOptions(TumblrConnectionOptions connectionOptions) {
		this.connectionOptions = connectionOptions;
	}
	
	public void setProperties(TumblrJProperties properties) {
		this.properties = properties;
	}
	
}
