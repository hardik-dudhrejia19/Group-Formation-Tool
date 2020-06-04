package com.advancesd.group17.user.dao;

import com.advancesd.group17.database.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

	public static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public List<String> getUserRoleByBannerId(String bannerid) {

		List<String> rolename = new ArrayList<>();
		try (Connection conn = DatabaseConfig.getInstance().getConnection();
				CallableStatement st = conn.prepareCall("{CALL getuserrolebybannerid(?)}");) {
			st.setString(1, bannerid);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				rolename.add(rs.getString("role_name"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rolename;
	}

	@Override
	public Boolean isAlreadyUser(String bannerid) {
		boolean isalreadyuser = false;
		try {
			Connection connection = DatabaseConfig.getInstance().getConnection();
			CallableStatement statement = connection.prepareCall("{CALL isalreadyuser(?)}");
			statement.setString(1, bannerid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				isalreadyuser = true;
			}
			statement.close();
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return isalreadyuser;
	}

	Connection createDbConnection() {
		Connection connection = null;
		try {
			connection = DatabaseConfig.getInstance().getConnection();
			if (connection == null) {
				log.info("Connection null");
			} else {
				log.info("Connection established");
			}
		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return connection;

	}

	@Override
	public boolean isEmailExist(String email) {

		try(Connection connection = DatabaseConfig.getInstance().getConnection();
				CallableStatement statement = connection.prepareCall("{CALL isemailexist(?)}");
				)
		{
			statement.setString(1,email);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
}
