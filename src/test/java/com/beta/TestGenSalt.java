package com.beta;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestGenSalt {

	
	@Test
	public void HidePassword()
	{
		
		System.out.println(BCrypt.hashpw("111", BCrypt.gensalt()));
		

		

	}
	
	
}
