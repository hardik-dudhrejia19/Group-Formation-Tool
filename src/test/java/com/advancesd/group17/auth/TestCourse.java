package com.advancesd.group17.auth;

import com.advancesd.group17.course.models.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCourse {
	
	Course c = new Course();
	
	@Test
	public void getIdTest()
	{
		c.setId(1);
		assertEquals(1, c.getId());
	}
	
	@Test
	public void setIdTest()
	{
		c.setId(2);
		
		assertEquals(2, c.getId());
	}
	
	@Test
	public void getCourseNameTest()
	{
		c.setCoursename("Adv Cloud");
	
		assertEquals("Adv Cloud", c.getCoursename());
	}

	@Test
	public void setCourseNameTest()
	{	
		c.setCoursename("SC");
		
		assertEquals("SC", c.getCoursename());
	}

}
