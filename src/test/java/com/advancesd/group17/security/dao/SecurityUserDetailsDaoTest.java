package com.advancesd.group17.security.dao;

import com.advancesd.group17.user.models.Role;
import com.advancesd.group17.user.models.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityUserDetailsDaoTest {

    private SecurityUserDetailsDaoMockImpl daoMock = new SecurityUserDetailsDaoMockImpl();

    @Test
    void getUserByBannerId() {

        User user = daoMock.getUserByBannerId("B0068473", new User());

        assertEquals("B0068473", user.getBannerId());
        assertEquals("MOCK FIRST NAME", user.getFirstName());
        assertEquals("MOCK LAST NAME", user.getLastName());
        assertEquals("MOCK PASSWORD", user.getPassword());
        assertEquals("MOCK EMAIL ID", user.getEmail());
    }

    @Test
    void getUserRoleByBannerId() {

        Set<Role> roleList = daoMock.getUserRoleByBannerId("B0068473", new HashSet<Role>());
        Set<Role> roleListExpected = new HashSet<Role>();
        roleListExpected.add(new Role(1, "ROLE_MOCK"));
        assertEquals(roleListExpected.size(), roleList.size());
    }
}