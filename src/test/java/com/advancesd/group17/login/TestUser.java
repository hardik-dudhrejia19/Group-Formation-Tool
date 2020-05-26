package com.advancesd.group17.login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.login.model.User;

class TestUser {
	
	@Test
	public void testgetBannerID()
	{
		User u = new User();
		u.setBannerID("1234");
		
		assertEquals("1234",u.getBannerID());
	}
	
	@Test
	public void testsetBannerID()
	{
		User u = new User();
		u.setBannerID("1234");
		
		assertEquals("1234", u.getBannerID());
	}
	
	@Test
	public void testgetPassword()
	{
		User u = new User();
		u.setPassword("aaaa");
		
		assertEquals("aaaa",u.getPassword());
	}
	
	@Test
	public void testsetPassword()
	{
		User u = new User();
		u.setPassword("aaaa");
		
		assertEquals("aaaa",u.getPassword());
	}
	
	public void testservicelogin()
	{
		User u = new User();
		
		String expectedpage = u.serviceLogin("admin", "admin");
		
		assertEquals("Home",expectedpage);
		
		expectedpage = u.serviceLogin("admin","asfafs");
		
		assertEquals("login",expectedpage);
	}

}
