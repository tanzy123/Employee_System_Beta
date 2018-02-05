package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SystemAdministrator {
	
	@Id
	@GeneratedValue
	private Long adminId;

	@OneToOne
	@JoinColumn(name = "userName")
	private UserLogin userLogin;
	
	@Enumerated(EnumType.STRING)
	private final LogInType logInType = LogInType.SYSTEM_ADMINISTRATOR;

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

	public LogInType getLogInType() {
		return logInType;
	}
}
