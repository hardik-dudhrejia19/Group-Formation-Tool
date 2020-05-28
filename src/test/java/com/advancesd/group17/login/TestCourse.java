package com.advancesd.group17.login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.login.model.Course;

class TestCourse {
	
	Course c = new Course();
	
	@Test
	public void TestgetCoursename() 
	{
		c.setCoursename("Adv Cloud");
	
		assertEquals("Adv Cloud", c.getCoursename());
	}

	@Test
	public void TestsetCoursename() 
	{	
		c.setCoursename("SC");
		
		assertEquals("SC", c.getCoursename());
	}

}
