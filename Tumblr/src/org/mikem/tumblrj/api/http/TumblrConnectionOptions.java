package org.mikem.tumblrj.api.http;

import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * Value object used by the a TumblrHttpReader instance. A value for name (setName()), 
 * should always be passed in. A HttpClient HttpClientParams object is optional (won't cause
 * exceptions if null)
 * 
 * @author Mike
 *
 */
public class TumblrConnectionOptions {
	/**
	 * Name of the Tumblr blog. i.e.: The * in *.tumblr.com
	 */
	private String name;
	
	/**
	 * Optional HttpClientParams (see HttpClient 3.1)
	 */
	private HttpClientParams httpClientParams;
	
	public HttpClientParams getHttpClientParams() {
		return httpClientParams;
	}
	public void setHttpClientParams(HttpClientParams httpClientParams) {
		this.httpClientParams = httpClientParams;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
