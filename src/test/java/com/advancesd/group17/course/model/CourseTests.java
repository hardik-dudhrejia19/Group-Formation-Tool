package com.advancesd.group17.course.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.course.services.CourseServiceImpl;



public class CourseTests {

	private String courseName = "CSCI5490";
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Test
	public void setCourseNameTest() {
		LOGGER.info("Running setCourseNameTest");
		Course c = new Course();
		c.setCourseName(courseName);
		assertEquals(courseName,c.getCourseName());
	}
	
	@Test
	public void getCourseNameTest() {
		LOGGER.info("Running getCourseNameTest");
		Course c = new Course(1, courseName);
		assertEquals(courseName, c.getCourseName());
	}
	
	public void getIdTest() {
		LOGGER.info("Running getIdTest");
		Course c = new Course(1, courseName);
		assertEquals(courseName, c.getId());
	}

	public void setIdTest(int id) {
		LOGGER.info("Running setIdTest");
		Course c = new Course();
		assertEquals(1, c.getId());
	}
}
