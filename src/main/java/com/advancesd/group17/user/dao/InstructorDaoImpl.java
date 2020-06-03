package com.advancesd.group17.user.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.database.DatabaseConfig;
import com.advancesd.group17.user.models.User;

public class InstructorDaoImpl implements InstructorDao {

	@Override
	public List<User> listusersforInstructor() {
		
		List<User> lu = new ArrayList<User>();
		
		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL usersforInstructorAssigning()}");	
		)
		{
		     ResultSet rs = st.executeQuery();
		    		    
		    while(rs.next())
		    {   
		    	User u = new User();
		    	u.setBannerId(rs.getString("user_banner"));
		    	u.setFirstName(rs.getString("user_fname"));
		    	u.setLastName(rs.getString("user_lname"));
		    	
		    	lu.add(u);
		    }
		    return lu;
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return lu;
        }	
	}

	@Override
	public boolean assignInstructor(String bannerid, Integer courseid) {
		
		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL addinstructor(?,?)}");	
		)
		{
			st.setString(1, bannerid);
			st.setInt(2, courseid);
			st.executeQuery();		    
	
		    return true;
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
		
	}

}
