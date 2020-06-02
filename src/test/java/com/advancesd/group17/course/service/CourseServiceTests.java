package com.advancesd.group17.course.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	
	private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	private CourseService courseService;
	
	private static String courseName = "CSCI5490";
	
	
	
//	@AfterEach
//	public void tearDown() {
//		log.info("Resetting the environment");
//		List<Course> courseList = courseService.listOfCourses();
//		for (Course course : courseList) {
//			courseService.deleteCourse(course.getCourseId());
//		}
//	}
//	
//	@Test
//	public void addCourseTest() {
//		log.info("Running addCourseTest");
//		Course course = courseService.addCourse(courseName);
//		assertEquals(courseName, course.getCourseName() );
//	}
//	
//	@Test
//	public void deleteCourseTest() {
//		log.info("Running deleteCourseTest");
//		Course course = courseService.addCourse(courseName);
//		assertEquals(courseName, course.getCourseName());
//		Boolean courseDeleted = courseService.deleteCourse(course.getCourseId());
//		assertTrue(courseDeleted);
//	}
//	
//	@Test
//	public void updateCourseTest() {
//		log.info("Running updateCourseTest");
//		Course courseAdded = courseService.addCourse(courseName);
//		assertEquals(courseName, courseAdded.getCourseName());
//		String updateCourseName = "CSCI5555";
//		Course course = courseService.updateCourse(courseName, updateCourseName);
//		assertEquals(updateCourseName, course.getCourseName());
//		
//	}
//	
//	@Test
//	public void listOfCoursesTest() {
//		log.info("Running listOfCoursesTest");
//		courseService.addCourse(courseName);
//		assertEquals(1, courseService.listOfCourses().size());
//	}
}
