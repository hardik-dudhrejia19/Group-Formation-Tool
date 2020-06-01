package com.advancesd.group17.course.dao;

import java.util.List;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;

public interface CourseDao {

	public List<String> getuserrolebybannerid(String bannerid);
	
	public List<Course> getallcourses();
	
	public List<Course> getcoursesbybannerid(String bannerid);

	public List<CourseAndRole> getcoursesandrolesbybannerid(String bannerid);

	public boolean isalreadyuser(String bannerid);

	public String getcoursebycourseid(int courseid);
}
