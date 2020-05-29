package com.advancesd.group17.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;

class TestCourseDao {

	CourseDao dc = new MockCourseDao();
	
	@Test
	public void Testgetuserrolebybannerid() {
	
		String bannerid = "B00836202";
		assertEquals("TA", dc.getuserrolebybannerid(bannerid));
		
		bannerid = "B00000000";
		assertEquals("Guest",dc.getuserrolebybannerid(bannerid));
	}
	
	@Test
	public void Testgetallcourses() {
		
		List<Course> l = new ArrayList<>();
		
		Course c = new Course();
		c.setCoursename("Adv SDC");
		l.add(c);
		c.setCoursename("Adv Web");
		l.add(c);
		c.setCoursename("SC");
		l.add(c);
		
		assertEquals(l.size(), dc.getallcourses().size());
	}
	
	@Test
	public void Testgetcoursesbybannerid() {
		
		List<Course> l = new ArrayList<>();
		Course c = new Course();
		
		String bannerid = "B00836202";
		c.setCoursename("Adv SDC");
		l.add(c);
		c.setCoursename("Adv Cloud");
		l.add(c);
		c.setCoursename("SC");
		l.add(c);
		
		assertEquals(l.size(), dc.getcoursesbybannerid(bannerid).size());
		
		bannerid = "B00000000";
		assertTrue(dc.getcoursesbybannerid(bannerid).isEmpty());
	}

}
