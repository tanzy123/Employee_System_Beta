package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CompanyAdministrator {
	
	@Id
	@GeneratedValue
	private Long adminId;
	
	@OneToOne
	@JoinColumn(name = "companyEmail")
	private Company company;

	@OneToOne
	@JoinColumn(name = "userName")
	private UserLogin userLogin;
	
	@Enumerated(EnumType.STRING)
	private LogInType logInType = LogInType.COMPANY_ADMINISTRATOR;

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
