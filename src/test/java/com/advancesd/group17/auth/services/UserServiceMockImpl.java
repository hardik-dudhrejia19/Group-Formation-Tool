package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDaoMock;
import com.advancesd.group17.auth.dao.UserDaoImpl;
import com.advancesd.group17.auth.dao.UserDaoMockImpl;
import com.advancesd.group17.auth.models.User;

public class UserServiceMockImpl implements UserServiceMock {

    private UserDaoMock userDaoMock = new UserDaoMockImpl();

    @Override
    public boolean createUser(User user) {

        return userDaoMock.createUser(user);
    }
}
