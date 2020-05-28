package com.advancesd.group17.login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDaoAuthentication {

	IDaoAuthentication da = new MockDaoAuthentication();
	
	@Test
	public void TestloginAuthentication()
	{
		String bid = "admin";
		String pass = "admin";
		assertEquals(true, da.loginAuthentication(bid, pass));
		
		bid = "admin";
		pass = "asfaf";
		assertEquals(false, da.loginAuthentication(bid, pass));
	}
	
	@Test
	public void Testgetuserid()
	{
		String bid = "B00836202";
		assertEquals(1, da.getuserid(bid));
		
		bid = "B253262366202";
		assertEquals(0, da.getuserid(bid));
	}
}
