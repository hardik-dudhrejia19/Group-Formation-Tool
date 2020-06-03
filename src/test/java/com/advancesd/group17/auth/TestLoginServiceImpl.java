package com.advancesd.group17.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.auth.services.LoginServiceImpl;

class TestLoginServiceImpl {

	UserDao ud = new MockUserDaoImpl();
	LoginServiceImpl ls = new LoginServiceImpl();
	
	@Test
	public void Testuserauthentication()
	{
		User u = new User();
		u.setBannerId("admin");
		u.setPassword("admin");
		assertTrue(ls.userauthentication(u, ud));
		
		u.setBannerId("poojan");
		u.setPassword("admin");
		assertFalse(ls.userauthentication(u, ud));
	}
	
	@Test
	public void TestIsadmin()
	{
		assertTrue(ls.Isadmin("admin", ud));
		assertFalse(ls.Isadmin("poojan", ud));
	}
}
