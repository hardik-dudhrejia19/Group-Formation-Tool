package com.advancesd.group17.course.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.MockCourseDao;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.user.models.NewStudent;

class CourseServiceTest {

    static CourseDao courseDao = null;
    
    CourseServiceImpl cs = new CourseServiceImpl();
    
    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    @BeforeAll
	public static void setUp() {
		log.info("Setting the environment");
		courseDao = new MockCourseDao();
	}
	
	@AfterEach
	public void tearDown() {
		log.info("Resetting the environment");
		courseDao = new MockCourseDao();
	}
	
	@Test
	public void addCourseTest() {
		log.info("Running addCourseTest");
		Course course = new Course();
		course.setCourseName("Advance Web");
		int intialListSize = courseDao.getAllCourses().size();
		Course addedCourse = courseDao.addNewCourse(course);
		assertEquals(intialListSize + 1, courseDao.getAllCourses().size());
	}
	
	@Test
	public void deleteCourseTest() {
		log.info("Running deleteCourseTest");
		int intialListSize = courseDao.getAllCourses().size();
		Boolean courseDeleted = courseDao.deleteCourse(1);
		assertTrue(courseDeleted);
	}
	
	@Test
	public void courseDetailsTest() {
		log.info("Running getCourseByBannerIdCase2Test");
		Course course = courseDao.getCourseDetails(1);
		assertEquals(3, course.getCourseCredits());
	}


    @Test
    void getCoursesAndRolesByBannerIdTest()
    {
        List<CourseAndRole> crs = new ArrayList<>();

        CourseAndRole cr = new CourseAndRole();
        cr.setCourseid(1);
        cr.setCoursename("Advance SDC");
        cr.setRole("Student");
        crs.add(cr);

        assertEquals(crs.size(),courseDao.getCoursesAndRolesByBannerId("B00835071").size());
        assertNotEquals(crs.size(),courseDao.getCoursesAndRolesByBannerId("B0000000").size());
    }

    @Test
    void getCourseByCourseIdTest()
    {
        assertEquals("Advance SDC",courseDao.getCourseByCourseId(1));
        assertNotEquals("Web Development",courseDao.getCourseByCourseId(2));
        assertNotEquals("Communication skills",courseDao.getCourseByCourseId(5));
    }

    @Test
    void assignTaTest()
    {
        assertTrue(courseDao.assignTa(3,"B00835071"));
        assertFalse(courseDao.assignTa(1,"B00835071"));
    }

    @Test
    void enrollStudentsToCourseTest()
    {
        List<NewStudent> newtudents = new ArrayList<NewStudent>();
        NewStudent student = new NewStudent();
        student.setBannerId("B00555698");
        student.setFirstName("mockfname");
        student.setLastName("mocklname");
        student.setEmail("mock@gmai.com");
        newtudents.add(student);
        assertTrue(courseDao.enrollStudentsToCourse(1, newtudents));
    }

    @Test
    void getFileExtensionTest()
    {
        assertEquals("csv",cs.getFileExtension("data.csv"));
        assertEquals("csv",cs.getFileExtension("data.csv.csv"));
        assertNotEquals(".pdf",cs.getFileExtension("courses.csv"));
        assertNotEquals("jpg",cs.getFileExtension("file.csv"));
    }

    @Test
    void getAllCoursesTest()
    {
        assertEquals(2, courseDao.getAllCourses().size());
    }

}