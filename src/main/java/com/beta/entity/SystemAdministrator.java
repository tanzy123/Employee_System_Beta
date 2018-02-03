package com.beta.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class SystemAdministrator {
	
	@Id
	@GeneratedValue
	private Long adminId;

	private UserLogin userLogin;

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
}
