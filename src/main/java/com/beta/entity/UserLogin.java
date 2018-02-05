package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserLogin {

	@Id
	private String userName;
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
