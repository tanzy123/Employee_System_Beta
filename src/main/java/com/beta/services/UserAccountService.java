package com.beta.services;

import com.beta.entity.UserAccount;

public interface UserAccountService {

	void updatePassword(UserAccount userAccount, String updatedPassword);
	
	void createNewAccount(UserAccount userAccount);
}
