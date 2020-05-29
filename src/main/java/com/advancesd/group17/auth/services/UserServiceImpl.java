package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.dao.UserDaoImpl;
import com.advancesd.group17.auth.models.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean createUser(User user) {

        return userDao.createUser(user);
    }
}
