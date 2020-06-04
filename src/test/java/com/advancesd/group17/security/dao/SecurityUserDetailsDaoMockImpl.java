package com.advancesd.group17.security.dao;

import com.advancesd.group17.user.models.Role;
import com.advancesd.group17.user.models.User;

import java.util.Set;

public class SecurityUserDetailsDaoMockImpl implements SecurityUserDetailsDaoMock {

    private User user = new User(
            "B0068473",
            "MOCK PASSWORD",
            "MOCK EMAIL ID",
            "MOCK FIRST NAME",
            "MOCK LAST NAME");

    @Override
    public User getUserByBannerId(String bannerId, User user) {
        return bannerId == "B0068473" ? this.user : null;
    }

    @Override
    public Set<Role> getUserRoleByBannerId(String bannerId, Set<Role> roleList) {
        roleList.add(new Role(1, "ROLE_MOCK"));
        return bannerId == "B0068473" ? roleList : null;
    }
}
