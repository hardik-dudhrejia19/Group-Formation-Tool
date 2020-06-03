package com.advancesd.group17.course.models;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.user.models.NewStudent;

import static org.junit.jupiter.api.Assertions.*;

class NewStudentTest {

    NewStudent ns = new NewStudent();
    @Test
    void getBannerId() {
        ns.setBannerId("B00835071");
        assertEquals("B00835071",ns.getBannerId());
    }

    @Test
    void setBannerId() {
        ns.setBannerId("B00835088");
        assertEquals("B00835088",ns.getBannerId());
    }

    @Test
    void getEmail() {
        ns.setEmail("hr208032@dal.ca");
        assertEquals("hr208032@dal.ca",ns.getEmail());
    }

    @Test
    void setEmail() {
        ns.setEmail("userxyz@gmail.com");
        assertEquals("userxyz@gmail.com",ns.getEmail());
    }

    @Test
    void getFirstName() {
        ns.setFirstName("John");
        assertEquals("John",ns.getFirstName());
    }

    @Test
    void setFirstName() {
        ns.setFirstName("Hardik");
        assertEquals("Hardik",ns.getFirstName());
    }

    @Test
    void getLastName() {
        ns.setLastName("Doe");
        assertEquals("Doe",ns.getLastName());
    }

    @Test
    void setLastName() {
        ns.setLastName("Clark");
        assertEquals("Clark",ns.getLastName());
    }
}