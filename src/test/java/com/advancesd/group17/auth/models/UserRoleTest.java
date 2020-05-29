package com.advancesd.group17.auth.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest {

    private UserRole userRole = new UserRole();

    @Test
    void getUserId() {
        userRole.setBannerId("B000000");
        assertEquals("B000000", userRole.getBannerId());
    }

    @Test
    void setUserId() {
        userRole.setBannerId("B000000");
        assertEquals("B000000", userRole.getBannerId());
    }

    @Test
    void getCourseId() {
        userRole.setCourseId(98765);
        assertEquals(98765, userRole.getCourseId());
    }

    @Test
    void setCourseId() {
        userRole.setCourseId(98765);
        assertEquals(98765, userRole.getCourseId());
    }

    @Test
    void getRoleId() {
        userRole.setRoleId(13579);
        assertEquals(13579, userRole.getRoleId());
    }

    @Test
    void setRoleId() {
        userRole.setRoleId(13579);
        assertEquals(13579, userRole.getRoleId());
    }

    @Test
    void testToString() {
        UserRole userRole = new UserRole("B000000", 98765, 13579);
        assertEquals("UserRole{" +
                "bannerId=" + "B000000" +
                ", courseId=" + 98765 +
                ", roleId=" + 13579 +
                '}', userRole.toString());
    }
}