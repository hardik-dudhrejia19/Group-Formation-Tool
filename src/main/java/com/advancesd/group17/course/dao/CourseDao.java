package com.advancesd.group17.course.dao;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.user.models.NewStudent;

import java.util.List;

public interface CourseDao {

	public List<Course> getAllCourses();
	
	Course addNewCourse(Course course);
	
	Boolean deleteCourse(Integer courseId);
	
	Course getCourseDetails(Integer courseId);

	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid);

	public String getCourseByCourseId(int courseid);

	public boolean assignTa(int courseid, String bannerid);

	public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents);

	public List<String> getUserRoleByBannerid(String bannerid);

}
