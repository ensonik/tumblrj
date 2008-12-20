package org.mikem.tumblr.api.read;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.mikem.tumblr.api.TumbleLog;
import org.mikem.tumblr.api.util.TumblrConnectionOptions;

public class TumblrHttpReader implements ITumblrReader {
	private TumblrReadOptions readOptions;
	private TumblrConnectionOptions connectionOptions;
	
	public TumbleLog read() throws Exception {
		if (this.connectionOptions == null) {
			throw new Exception("Can't connect up because reader doesn't have a configured TumblrConnectionOptions");
		}
		
	    HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://"+connectionOptions.getName()+".tumblr.com/api/read");
        setQueryStringParams(post);
        client.executeMethod(post);
        String response = post.getResponseBodyAsString();
        System.out.println("res is: " + response);
        post.releaseConnection();

        Document doc = DocumentHelper.parseText(response);
        
        TumbleLog log = new TumbleLog(doc);
		
		return log;
	}

	
	private void setQueryStringParams(PostMethod post) {
		if (this.readOptions == null) {
			return;
		}
		
		boolean idUsed = false;
		
		if (this.readOptions.getId() != null && !("").equals(this.readOptions.getId())) {
			post.addParameter("id", this.readOptions.getId());
		}
		
		if (this.readOptions.getNum() > 0 && !idUsed) {
			post.addParameter("num", String.valueOf(this.readOptions.getNum()));
		}

		if (this.readOptions.getStart() > 0 && !idUsed) {
			post.addParameter("start", String.valueOf(this.readOptions.getStart()));
		}

		if (this.readOptions.getType() != null && !idUsed) {
			post.addParameter("type", this.readOptions.getType().toString());
		}

		if (this.readOptions.isReadPrivate()) {
			post.addParameter("private", "true");
			post.addParameter("username", this.connectionOptions.getEmail());
			post.addParameter("password", this.connectionOptions.getPassword());
		}
	}


	public void setTumblrConnectionOptions(TumblrConnectionOptions connectionOptions) {
		this.connectionOptions = connectionOptions;
	}
	
	public void setTumblrReadOptions(TumblrReadOptions readOptions) {
		this.readOptions = readOptions;
	}
}
