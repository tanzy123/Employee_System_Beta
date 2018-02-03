package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(UserKey.class)
public class UserLogin {

	@Id
	private Company company;
	
	@Id
	private LogInType logInType;
	
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public LogInType getLogInType() {
		return logInType;
	}

	public void setLogInType(LogInType logInType) {
		this.logInType = logInType;
	}

}
