package com.advancesd.group17.auth.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role role = new Role();

    @Test
    void getRoleId() {
        role.setRoleId(12345);
        assertEquals(12345, role.getRoleId());
    }

    @Test
    void setRoleId() {
        role.setRoleId(12345);
        assertEquals(12345, role.getRoleId());
    }

    @Test
    void getRoleName() {
        role.setRoleName("MOCK USER");
        assertEquals("MOCK USER", role.getRoleName());
    }

    @Test
    void setRoleName() {
        role.setRoleName("MOCK USER");
        assertEquals("MOCK USER", role.getRoleName());
    }

    @Test
    void testToString() {
        Role role = new Role(12345,"MOCK USER");
        assertEquals("Role{" +
                "roleId='" + 12345 + '\'' +
                ", roleName='" + "MOCK USER" + '\'' +
                '}', role.toString());
    }
}