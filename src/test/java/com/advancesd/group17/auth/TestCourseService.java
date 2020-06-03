package com.advancesd.group17.auth;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.MockCourseDao;
import com.advancesd.group17.course.services.CourseServiceImpl;

class TestCourseService {

	CourseDao dc = new MockCourseDao();
	CourseServiceImpl cs = new CourseServiceImpl();
	
//	@Test
//	public void Testlistcourses()
//	{
//		List<Course> l = new ArrayList<>();
//		Course c = new Course();
//		c.setCoursename("Adv SDC");
//		l.add(c);
//		c.setCoursename("Adv Cloud");
//		l.add(c);
//
//		String bannerid = "B00836202";
//		assertEquals(l.size(), cs.listcourses(bannerid, dc).size());
//
//
//		List<Course> l1 = new ArrayList<>();
//		Course c1 = new Course();
//		c1.setCoursename("Adv SDC");
//		l1.add(c1);
//		c1.setCoursename("Adv Web");
//		l1.add(c1);
//		c1.setCoursename("SC");
//		l1.add(c1);
//
//		bannerid = "B00000000";
//		assertEquals(l1.size(), cs.listcourses(bannerid, dc).size());
//	}

}
