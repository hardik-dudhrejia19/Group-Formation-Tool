package com.advancesd.group17.login;

import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.login.model.Course;

public class MockDaoCourses implements IDaoCourses {

	@Override
	public String getuserrole(Integer userid) 
	{
		if(userid == 1)
		{
			return "TA";
		}
		
		if(userid == 100)
		{
			return null;
		}
		
		return null;
	}
	
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
	public List<Course> getcoursesbyuserid(Integer userid) 
	{
		
		if(userid == 1)
		{
			List<Course> l = new ArrayList<>();
			
			Course c = new Course();
			c.setCoursename("Adv SDC");
			l.add(c);
			c.setCoursename("Adv Cloud");
			l.add(c);
			c.setCoursename("SC");
			l.add(c);
			
			return l;
		}
		
		if(userid == 5)
		{
			List<Course> l = new ArrayList<>();
			
			return l;
		}
		
		return null;
	}

}
