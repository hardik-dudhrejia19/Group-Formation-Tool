package com.advancesd.group17.course.services;

import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;

public class CourseService {
	
	public List<String> getuserrolebybannerid(String bannerid,CourseDao cd)
	{
			return cd.getuserrolebybannerid(bannerid);
	}


	public List<CourseAndRole> getcoursesandrolesbybannerid(String bannerid,CourseDao cd)
	{
		return cd.getcoursesandrolesbybannerid(bannerid);
	}

}
