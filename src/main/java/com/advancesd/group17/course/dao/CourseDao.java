package com.advancesd.group17.course.dao;

import java.util.List;

import com.advancesd.group17.course.models.Course;

public interface CourseDao {

	public String getuserrolebybannerid(String bannerid);
	
	public List<Course> getallcourses();
	
	public List<Course> getcoursesbybannerid(String bannerid);
}
