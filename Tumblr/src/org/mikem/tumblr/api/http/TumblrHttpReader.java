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
import org.mikem.tumblr.api.util.Credentials;
import org.mikem.tumblr.api.util.TumblrJProperties;
import org.mikem.tumblr.api.util.TumblrReadOptions;
import org.mikem.tumblr.exceptions.InvalidTumblrCredentialsExeption;
import org.mikem.tumblr.exceptions.TumblrJException;

public class TumblrHttpReader implements ITumblrReader {
    private Log logger = LogFactory.getLog(TumblrHttpReader.class);
	private TumblrConnectionOptions connectionOptions;
	private TumblrJProperties properties;
	
	public User getUserInformation(Credentials credentials) throws TumblrJException {
		if (this.connectionOptions == null) {
			throw new IllegalStateException("Can't connect up because reader doesn't have a configured TumblrConnectionOptions");
		}
		if (credentials == null || !credentials.areCredentialsValid()) {
			throw new InvalidTumblrCredentialsExeption();
		}
				
		try {
			HttpClient client = setupHttpClient();
			PostMethod post = setupPostMethod(properties.getAuthenticationPath());
			setAuthenticationInformation(post, credentials);
			
			logger.debug("Posting request to get user information : " + post.getURI().toString() + " with email " + credentials.getEmail());
	
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
	
	public void delete(String postId, Credentials credentials) throws TumblrJException {
		if (StringUtils.isEmpty(postId)) {
			throw new IllegalArgumentException("Post id is null");
		}
		if (this.connectionOptions == null) {
			throw new IllegalStateException("Can't connect up because reader doesn't have a configured TumblrConnectionOptions");
		}
		if (credentials == null || !credentials.areCredentialsValid()) {
			throw new InvalidTumblrCredentialsExeption();
		}
				
		try {
			HttpClient client = setupHttpClient();
			PostMethod post = setupPostMethod(properties.getDeletePath());
			setAuthenticationInformation(post, credentials);
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
	
	public TumbleLog read(TumblrReadOptions readOptions, Credentials credentials) throws TumblrJException {
		if (this.connectionOptions == null) {
			throw new IllegalStateException("Can't connect up because reader doesn't have a configured TumblrConnectionOptions");
		}
        
        try {
    	    HttpClient client = setupHttpClient();
            PostMethod post = setupPostMethod(properties.getReadPath());
            setPostParameters(post, readOptions, credentials);
        	
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
	
	public TumblePost write(TumblePost tumblrPost, Credentials credentials) throws TumblrJException {
		if (tumblrPost == null) {
			throw new IllegalArgumentException("Post parameter is null");
		}
		if (credentials == null || !credentials.areCredentialsValid()) {
			throw new InvalidTumblrCredentialsExeption();
		}
		
		try {
			HttpClient client = setupHttpClient();
			PostMethod post = setupPostMethod(properties.getWritePath());
			setAuthenticationInformation(post, credentials);
			tumblrPost.setupPostParams(post);
			
        	logger.debug("Posting write request to : " + post.getURI().toString());
			
			client.executeMethod(post);
			String id = post.getResponseBodyAsString();
			
			logger.debug("Received response for write request: " + id);
			
			// FIXME Need to check for errors and handle them a lot better
			
			TumblrReadOptions readOptions = new TumblrReadOptions();
			readOptions.setId(id);
			readOptions.setType(tumblrPost.getType());
			readOptions.setReadPrivate(tumblrPost.getPrivatePost());
			return this.read(readOptions, credentials).getPosts().get(0);			
		} catch (Exception e) {
			throw new TumblrJException(e);
		}
	}
	
	private PostMethod setupPostMethod(String fullUrl) {
		String finalUrl = fullUrl;
		if (StringUtils.contains(fullUrl, "{0}")) {
			finalUrl = StringUtils.replace(fullUrl, "{0}", this.connectionOptions.getName());
		}
		return new PostMethod(finalUrl);
	}
	
	private HttpClient setupHttpClient() {
		HttpClient client = new HttpClient();
	    if (this.connectionOptions.getHttpClientParams() != null) {
	    	client.setParams(this.connectionOptions.getHttpClientParams());
	    }
	    return client;
	}
	
	private void setAuthenticationInformation(PostMethod post, Credentials credentials) {
		if (credentials == null || !credentials.areCredentialsValid()) {
			throw new InvalidTumblrCredentialsExeption();
		}
		
		addPostParam(post, "email", credentials.getEmail());
		addPostParam(post, "password", credentials.getPassword(), true);
	}

	
	private void setPostParameters(PostMethod post, TumblrReadOptions readOptions, Credentials credentials) {
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

		if (readOptions.isReadPrivate() && credentials != null) {
			addPostParam(post, "private", "true");
			setAuthenticationInformation(post,credentials);
		}
	}
	
	private void addPostParam(PostMethod post, String paramName, String paramValue, boolean obfuscateLogValue /**Yuck */) {
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
