package com.advancesd.group17.course.dao;

import java.util.List;

import com.advancesd.group17.course.models.Course;

public interface CourseDao {

	String getuserrolebybannerid(String bannerid);
	
	List<Course> getallcourses();
	
	List<Course> getcoursesbybannerid(String bannerid);
}
