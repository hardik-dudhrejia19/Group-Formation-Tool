package com.advancesd.group17.course.services;

import com.advancesd.group17.course.dao.MockCourseDao;
import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.CourseAndRole;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    CourseDao cd = new MockCourseDao();
    CourseServiceImpl cs = new CourseServiceImpl();

    @Test
    void getUserRoleByBannerIdTest()
    {
       List<String> roles = new ArrayList<>();
       roles.add("TA");
       assertEquals(roles,cd.getUserRoleByBannerid("B00836202"));
       assertNotEquals("Guest",cd.getUserRoleByBannerid("B00000000"));
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

        assertEquals(crs.size(),cd.getCoursesAndRolesByBannerId("B00835071").size());
        assertNotEquals(crs.size(),cd.getCoursesAndRolesByBannerId("B0000000").size());
    }

    @Test
    void isAlreadyUserTest() {
        assertTrue(cd.isAlreadyUser("B00835071"));
        assertFalse(cd.isAlreadyUser("B99999999"));
    }

    @Test
    void getCourseByCourseIdTest()
    {
        assertEquals("Advance SDC",cd.getCourseByCourseId(1));
        assertEquals("Machine Learning",cd.getCourseByCourseId(3));
        assertNotEquals("Web Development",cd.getCourseByCourseId(2));
        assertNotEquals("Communication skills",cd.getCourseByCourseId(5));
    }

    @Test
    void assignTaTest()
    {
        assertTrue(cd.assignTa(3,"B00835071"));
        assertFalse(cd.assignTa(1,"B00835071"));
    }

    @Test
    void enrollStudentsToCourseTest()
    {

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
        assertEquals(cd.getAllCourses().size(),cd.getAllCourses().size());
    }

}