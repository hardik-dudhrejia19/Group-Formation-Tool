package com.advancesd.group17.security.dao;

import com.advancesd.group17.auth.models.Role;
import com.advancesd.group17.auth.models.User;

import java.util.Set;

public interface SecurityUserDetailsDaoMock {
    public User getUserByBannerId(String bannerId, User user);

    public Set<Role> getUserRoleByBannerId(String bannerId, Set<Role> roleList);
}
