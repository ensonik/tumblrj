package org.mikem.tumblrj.api.util;

import org.apache.commons.lang.StringUtils;

/**
 * Reprensents a users credentials on Tumblr.com. 
 * 
 * An instance of this object wouldn't be considered valid if the email or password 
 * were empty.
 * 
 * @author Mike
 *
 */
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
