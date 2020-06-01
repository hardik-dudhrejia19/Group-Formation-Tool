package com.advancesd.group17.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.course.models.Course;

class TestCourse {
	
	Course c = new Course();
	
	@Test
	public void TestgetCourseid()
	{
		c.setCourseid(1);
		assertEquals(1, c.getCourseid());
	}
	
	@Test
	public void TestsetCourseid()
	{
		c.setCourseid(2);
		
		assertEquals(2, c.getCourseid());
	}
	
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
