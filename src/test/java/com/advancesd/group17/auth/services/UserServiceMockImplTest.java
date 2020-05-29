package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceMockImplTest {

    private UserServiceMockImpl userService = new UserServiceMockImpl();

    @Test
    void createUser() {
        User user = new User();
        user.setBannerId("B0068473");
        user.setFirstName("MOCK FIRST NAME");
        user.setLastName("MOCK LAST NAME");
        user.setEmail("MOCK EMAIL ID");
        user.setPassword("MOCK PASSWORD");

        boolean flag = userService.createUser(user);
        assertTrue(flag);
    }

}