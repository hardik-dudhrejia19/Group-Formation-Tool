package com.advancesd.group17.course.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseAndRoleTest {

    CourseAndRole crs = new CourseAndRole();
    @Test
    void getCourseid() {
        crs.setCourseid(4);
        assertEquals(4, crs.getCourseid());
    }

    @Test
    void setCourseid() {
        crs.setCourseid(2);
        assertEquals(2,crs.getCourseid());
    }

    @Test
    void getCoursename() {
        crs.setCoursename("Advanced Software Development Concepts");
        assertEquals("Advanced Software Development Concepts",crs.getCoursename());
    }

    @Test
    void setCoursename() {
        crs.setCoursename("Machine Learning");
        assertEquals("Machine Learning",crs.getCoursename());
    }

    @Test
    void getRole() {
        crs.setRole("TA");
        assertEquals("TA",crs.getRole());
    }

    @Test
    void setRole() {
        crs.setRole("Student");
        assertEquals("Student",crs.getRole());
    }
}