package com.advancesd.group17.auth.dao;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.database.DatabaseConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean createUser(User user) {

        try (
                Connection con = DatabaseConfig.getInstance().getConnection();
                CallableStatement statement = con.prepareCall("{ call createuser(?, ?, ?, ?, ?) }")
        ) {
            statement.setString(1, user.getBannerId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
