package com.advancesd.group17.course.dao;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.course.models.NewStudent;

import java.util.ArrayList;
import java.util.List;

public class MockCourseDao implements CourseDao {

	@Override
	public List<Course> getAllCourses()
	{
		List<Course> courses = new ArrayList<>();

		Course course1 = new Course();
		course1.setId(1);
		course1.setCoursename("Advance SDC");
		course1.setCoursedescription("Advance SDC in Graduated Course");
		course1.setCredits(3);

		Course course2 = new Course();
		course2.setId(2);
		course2.setCoursename("Advance Web");
		course2.setCoursedescription("Advance Web in Graduated Course");
		course2.setCredits(3);

		Course course3 = new Course();
		course3.setId(3);
		course3.setCoursename("Machine Learning");
		course3.setCoursedescription("Advanced Data Science Course");
		course3.setCredits(3);

		Course course4 = new Course();
		course4.setId(4);
		course4.setCoursename("Technology Innovation");
		course4.setCoursedescription("Advanced Enterpreneurship Course");
		course4.setCredits(3);

		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);

		return  courses;
	}

	@Override
	public List<String> getUserRoleByBannerid(String bannerid) {

		List<String> list = new ArrayList<>();
		if("B00836202".equals(bannerid))
		{
			list.add("TA");
			return list;
		}
		
		if("B00000000".equals(bannerid))
		{
			list.add("Guest");
			return list;
		}
		return null;
	}


	@Override
	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid)
	{
		List<CourseAndRole> crs = new ArrayList<>();
		if("B00835071".equals(bannerid))
		{
			CourseAndRole cr = new CourseAndRole();
			cr.setCourseid(1);
			cr.setCoursename("Advance SDC");
			cr.setRole("Student");
			crs.add(cr);
		}
		return crs;
	}

	@Override
	public boolean isAlreadyUser(String bannerid) {
		boolean isuser = false;
		if("B00835071".equals(bannerid))
		{
			isuser = true;
		}
		if("B99999999".equals(bannerid))
		{
			isuser = false;
		}
		return isuser;
	}

	@Override
	public String getCourseByCourseId(int courseid)
	{
		String coursename = "";
		if(courseid == 1)
		{
			coursename = "Advance SDC";
		}
		if(courseid == 3)
		{
			coursename = "Machine Learning";
		}
		return coursename;
	}

	@Override
	public boolean assignTa(int courseid, String bannerid) {
		boolean assign = false;
		if(courseid == 1 & bannerid.equals("B00835071"))
		{
			assign = false;
		}

		if(courseid == 3 & bannerid.equals("B00835071"))
		{
			assign = true;
		}
		return assign;
	}

	@Override
	public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents)
	{
		return true;
	}

}
