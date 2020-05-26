package com.advancesd.group17.course.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.advancesd.group17.course.model.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	private List<Course> courseList = new ArrayList<Course> ();
	private int count = 0;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CourseServiceImpl.class);

	@Override
	public List<Course> addCourse(String courseName) {
		courseList.add(new Course(count, courseName));
		count++;
		return courseList;
	}

	@Override
	public List<Course> deleteCourse(String courseName) {
		courseList = courseList.stream().filter(course -> !course.getCourseName().equals(courseName)).collect(Collectors.toList());
		LOGGER.info("" + courseList);
		return courseList;
	}

	@Override
	public Course updateCourse(String courseName, String updatedCourseName) {
		List <Course> updatedCourseList = courseList.stream().peek(course -> {
			if (course.getCourseName().equals(courseName)) {
				course.setCourseName(updatedCourseName);
			}
		}).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(updatedCourseList)) {
			return null;
		}
		return updatedCourseList.get(0);
	}

	@Override
	public List<Course> listOfCourses() {
		
		return courseList;
	}

}
