package com.advancesd.group17.course.services;

import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;

public class CourseService {
	
	public List<Course> listcourses(String bannerid,CourseDao dc)
	{
		String userrole = dc.getuserrolebybannerid(bannerid);
		
		List<Course> crs = new ArrayList<>();
		
		if("Guest".equals(userrole))
		{
			crs = dc.getallcourses();
		}
		else
		{
			crs = dc.getcoursesbybannerid(bannerid);
		}
		
		return crs;
	}

}
