package com.advancesd.group17.course.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.advancesd.group17.course.model.Course;

@Service
public interface CourseService {
	
	public List<Course> addCourse(String courseName);
	
	public List<Course> deleteCourse(String courseName);
	
	public Course updateCourse(String courseName, String updatedCourseName);
	
	public List<Course> listOfCourses();

}
