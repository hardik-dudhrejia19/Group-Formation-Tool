package com.advancesd.group17.login;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.login.model.Course;

class TestDaoCourses {

	IDaoCourses dc = new MockDaoCourses();
	
	@Test
	public void Testgetuserrole() {
	
		Integer userid = 1;
		assertEquals("TA", dc.getuserrole(userid));
		
		userid = 100;
		assertEquals(null, dc.getuserrole(userid));
		
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
	public void Testgetcoursesbyuserid() {
		
		List<Course> l = new ArrayList<>();
		
		Course c = new Course();
		c.setCoursename("Adv SDC");
		l.add(c);
		c.setCoursename("Adv Cloud");
		l.add(c);
		c.setCoursename("SC");
		l.add(c);
		
		Integer userid = 1;
		assertEquals(l.size(), dc.getcoursesbyuserid(userid).size());
		
		userid = 5;
		assertTrue(dc.getcoursesbyuserid(userid).isEmpty());
	}

}
