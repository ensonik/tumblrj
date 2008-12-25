package org.mikem.tumblr.api.http;

import org.apache.commons.httpclient.params.HttpClientParams;


public class TumblrConnectionOptions {
	private String name;
	private String email;
	private String password;
	private HttpClientParams httpClientParams;
	
	public HttpClientParams getHttpClientParams() {
		return httpClientParams;
	}
	public void setHttpClientParams(HttpClientParams httpClientParams) {
		this.httpClientParams = httpClientParams;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
