package com.advancesd.group17.course.dao;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.course.models.NewStudent;

import java.util.List;

public interface CourseDao {

	public List<Course> getAllCourses();

	public List<String> getUserRoleByBannerid(String bannerid);

	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid);

	public boolean isAlreadyUser(String bannerid);

	public String getCourseByCourseId(int courseid);

	public boolean assignTa(int courseid, String bannerid);

	public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents);

}
