package com.advancesd.group17.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

class TestUserDao {

	UserDao da = new MockUserDao();
	
	User u = new User();
	
	@Test
	public void TestloginAuthentication()
	{
		u.setBannerId("admin");
		u.setPassword("admin");
		assertEquals(true, da.loginAuthentication(u));
		
		u.setBannerId("admin");
		u.setPassword("asfaf");
		assertEquals(false, da.loginAuthentication(u));
	}
	
	@Test
	public void Testisalreadyuser() 
	{
		u.setBannerId("admin");
		assertEquals(true, da.isalreadyuser(u));
		
		u.setBannerId("krutarth");
		assertEquals(false,da.isalreadyuser(u));
	}
}
