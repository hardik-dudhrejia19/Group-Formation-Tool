package com.advancesd.group17.auth;

import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;

public class MockCourseDao implements CourseDao {
	
	@Override
	public List<Course> getallcourses() 
	{
		
		List<Course> l = new ArrayList<>();
		
		Course c = new Course();
		c.setCoursename("Adv SDC");
		l.add(c);
		c.setCoursename("Adv Web");
		l.add(c);
		c.setCoursename("SC");
		l.add(c);
		
		return l; 	
	}

	@Override
	public String getuserrolebybannerid(String bannerid) {
		
		if("B00836202".equals(bannerid))
		{
			return "TA";
		}
		
		if("B00000000".equals(bannerid))
		{
			return "Guest";
		}
		
		return null;
	}

	@Override
	public List<Course> getcoursesbybannerid(String bannerid) {
		
		List<Course> l = new ArrayList<>();
		
		if(bannerid == "B00836202")
		{	
			Course c = new Course();
			c.setCoursename("Adv SDC");
			l.add(c);
			c.setCoursename("Adv Cloud");
			l.add(c);
			c.setCoursename("SC");
			l.add(c);
			
			return l;
		}
		
		if(bannerid == "B00000000")
		{	
			return l;
		}
		
		return l;		
	}

}
