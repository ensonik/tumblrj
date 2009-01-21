package org.mikem.tumblr.api.http;

import org.apache.commons.httpclient.params.HttpClientParams;


public class TumblrConnectionOptions {
	private String name;
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
