package com.advancesd.group17.login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.login.model.User;

class TestUser {
	
	User u = new User();
	
	IDaoLogin dl = new MockDaoLogin();
	
	@Test
	public void testgetBannerID()
	{
		u.setBannerID("1234");
		
		assertEquals("1234",u.getBannerID());
	}
	
	@Test
	public void testsetBannerID()
	{
		u.setBannerID("1234");
		
		assertEquals("1234", u.getBannerID());
	}
	
	@Test
	public void testgetPassword()
	{
		u.setPassword("aaaa");
		
		assertEquals("aaaa",u.getPassword());
	}
	
	@Test
	public void testsetPassword()
	{
		u.setPassword("aaaa");
		
		assertEquals("aaaa",u.getPassword());
	}
	
	@Test
	public void testservicelogin()
	{		
		String page = u.serviceLogin("B00836202", "poojan", dl);
		assertEquals("Home",page);
		
		page = u.serviceLogin("B0081355", "poojadan", dl);
		assertEquals("login",page);
		
		page = u.serviceLogin("admin", "admin", dl);
		assertEquals("Admin",page);
	}

}
