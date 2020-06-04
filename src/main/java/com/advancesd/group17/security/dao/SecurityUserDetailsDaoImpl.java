package com.advancesd.group17.security.dao;

import com.advancesd.group17.database.DatabaseConfig;
import com.advancesd.group17.user.models.Role;
import com.advancesd.group17.user.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class SecurityUserDetailsDaoImpl implements SecurityUserDetailsDao {

    @Override
    public User getUserByBannerId(String bannerId, User user) {
        try (
                Connection con = DatabaseConfig.getInstance().getConnection();
                CallableStatement statement = con.prepareCall("{ call getuserbybannerid(?) }")
        ) {
            statement.setString(1, bannerId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                user.setFirstName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setBannerId(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public Set<Role> getUserRoleByBannerId(String bannerId, Set<Role> roleList) {
        try (
                Connection con = DatabaseConfig.getInstance().getConnection();
                CallableStatement statement = con.prepareCall("{ call getrolesfrombannerid(?) }")
        ) {
            statement.setString(1, bannerId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getLong(1));
                role.setRoleName(rs.getString(2));
                roleList.add(role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(roleList.stream().count());
        return roleList;
    }
}
