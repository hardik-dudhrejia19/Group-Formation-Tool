package com.advancesd.group17.course.services;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.CourseAndRole;

import java.util.List;

public class CourseService {
	
	public List<String> getuserrolebybannerid(String bannerid,CourseDao cd)
	{
		return cd.getuserrolebybannerid(bannerid);
	}

	public List<CourseAndRole> getcoursesandrolesbybannerid(String bannerid,CourseDao cd)
	{
		return cd.getcoursesandrolesbybannerid(bannerid);
	}

	public boolean isalreadyuser(String bannerid ,CourseDao cd)
	{
		return cd.isalreadyuser(bannerid);
	}

	public String getcoursebycourseid(int courseid, CourseDao cd)
	{
		return cd.getcoursebycourseid(courseid);
	}

}
