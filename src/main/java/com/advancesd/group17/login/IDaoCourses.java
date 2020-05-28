package com.advancesd.group17.login;

import java.util.List;

import com.advancesd.group17.login.model.Course;

public interface IDaoCourses {

	String getuserrole(Integer userid);
	List<Course> getallcourses();
	List<Course> getcoursesbyuserid(Integer userid);
}
