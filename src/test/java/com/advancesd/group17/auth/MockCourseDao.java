package com.advancesd.group17.auth;

import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;

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
	public List<String> getuserrolebybannerid(String bannerid) {

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
	public List<Course> getcoursesbybannerid(String bannerid) {
		
		List<Course> l = new ArrayList<>();
		
		if(bannerid == "B00836202")
		{	
			Course c = new Course();
			c.setCoursename("Adv SDC");
			l.add(c);
			c.setCoursename("Adv Cloud");
			l.add(c);
			return l;
		}
		
		if(bannerid == "B00000000")
		{	
			return l;
		}
		
		return l;		
	}

	@Override
	public List<CourseAndRole> getcoursesandrolesbybannerid(String bannerid) {
		return null;
	}


	@Override
	public boolean isalreadyuser(String bannerid) {
		return false;
	}

	@Override
	public String getcoursebycourseid(int courseid) {
		return null;
	}

}
