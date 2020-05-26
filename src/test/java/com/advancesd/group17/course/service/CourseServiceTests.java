package com.advancesd.group17.course.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.advancesd.group17.course.model.Course;
import com.advancesd.group17.course.services.CourseService;
import com.advancesd.group17.course.services.CourseServiceImpl;

@SpringBootTest
public class CourseServiceTests {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	private CourseService courseService;
	
	private static String courseName = "CSCI5490";
	
	
	
	@AfterEach
	public void tearDown() {
		LOGGER.info("Resetting the environment");
		List<Course> courseList = courseService.listOfCourses();
		for (Course course : courseList) {
			courseService.deleteCourse(course.getCourseName());
		}
	}
	
	@Test
	public void addCourseTest() {
		LOGGER.info("Running addCourseTest");
		List<Course> courseList = courseService.addCourse(courseName);
		assertEquals(1, courseList.size());
	}
	
	@Test
	public void deleteCourseTest() {
		LOGGER.info("Running deleteCourseTest");
		List<Course> courseList = courseService.addCourse(courseName);
		assertEquals(1, courseList.size());
		List<Course> courseListAfterDeletion = courseService.deleteCourse(courseName);
		assertEquals(0, courseListAfterDeletion.size());
	}
	
	@Test
	public void updateCourseTest() {
		LOGGER.info("Running updateCourseTest");
		List<Course> courseList = courseService.addCourse(courseName);
		assertEquals(courseList.size(), 1);
		String updateCourseName = "CSCI5555";
		Course course = courseService.updateCourse(courseName, updateCourseName);
		assertEquals(updateCourseName, course.getCourseName());
		
	}
	
	@Test
	public void listOfCoursesTest() {
		LOGGER.info("Running listOfCoursesTest");
		courseService.addCourse(courseName);
		assertEquals(1, courseService.listOfCourses().size());
	}
}
