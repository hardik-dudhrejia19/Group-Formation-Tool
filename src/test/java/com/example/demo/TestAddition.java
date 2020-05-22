package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestAddition {
	
	Addition ad = new Addition();
	
	@Test
	public void testAdd()
	{	
		int ans = ad.Add(5,6);
		
		assertEquals(11, ans);
	}

}
