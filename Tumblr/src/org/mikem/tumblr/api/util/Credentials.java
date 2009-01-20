package org.mikem.tumblr.api.util;

import org.apache.commons.lang.StringUtils;

public class Credentials {
	private String email;
	private String password;
	
	public Credentials() { }
	
	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public boolean areCredentialsValid() {
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			return false;
		} else {
			return true;
		}
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
	
	
}
