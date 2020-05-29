package com.advancesd.group17.auth.dao;

import com.advancesd.group17.auth.models.User;

public class UserDaoMockImpl implements UserDaoMock {

    private User user = new User(
            "B0068473",
            "MOCK PASSWORD",
            "MOCK EMAIL ID",
            "MOCK FIRST NAME",
            "MOCK LAST NAME");

    @Override
    public boolean createUser(User user) {
        if(user.getBannerId() == this.user.getBannerId() &&
        user.getFirstName() == this.user.getFirstName() &&
        user.getLastName() == this.user.getLastName() &&
        user.getPassword() == this.user.getPassword() &&
        user.getEmail() == this.user.getEmail()){
            return true;
        }
        return false;
    }

}
