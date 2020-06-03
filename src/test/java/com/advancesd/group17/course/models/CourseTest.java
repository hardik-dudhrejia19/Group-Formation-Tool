package com.advancesd.group17.course.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Course course = new Course();

    @Test
    void getId()
    {
        course.setId(1);
        assertEquals(1,course.getId());
    }

    @Test
    void setId()
    {
        course.setId(3);
        assertEquals(3,course.getId());
    }

    @Test
    void getCoursename()
    {
        course.setCoursename("Technology Innovation");
        assertEquals("Technology Innovation",course.getCoursename());
    }

    @Test
    void setCoursename()
    {
        course.setCoursename("Cloud Computing");
        assertEquals("Cloud Computing",course.getCoursename());
    }

    @Test
    void getCoursedescription()
    {
        course.setCoursedescription("Advanced course in Masters");
        assertEquals("Advanced course in Masters",course.getCoursedescription());
    }

    @Test
    void setCoursedescription()
    {
        course.setCoursedescription("Advanced course in Graduate");
        assertEquals("Advanced course in Graduate",course.getCoursedescription());
    }

    @Test
    void getCredits()
    {
        course.setCredits(3);
        assertEquals(3,course.getCredits());
    }

    @Test
    void setCredits()
    {
        course.setCredits(1);
        assertEquals(1,course.getCredits());
    }
}