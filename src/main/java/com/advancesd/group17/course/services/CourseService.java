package com.advancesd.group17.course.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.advancesd.group17.course.model.Course;

@Service
public interface CourseService {
	
	public Course addCourse(String courseName, HashMap<String, Object> courseParameters);
	
	public Boolean deleteCourse(Integer id);
	
	public List<Course> listOfCourses();
	
	public Course courseDetails(Integer id);

}
