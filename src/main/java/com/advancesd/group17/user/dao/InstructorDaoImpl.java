package com.advancesd.group17.user.dao;

import static com.advancesd.group17.utils.Constants.USER_BANNER_FIELD;
import static com.advancesd.group17.utils.Constants.USER_FNAME_FIELD;
import static com.advancesd.group17.utils.Constants.USER_LNAME_FIELD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.database.DatabaseConfig;
import com.advancesd.group17.user.models.User;

public class InstructorDaoImpl implements InstructorDao {
	
	public static Logger log = LoggerFactory.getLogger(InstructorDaoImpl.class);

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
			st.setInt(1, courseid);
			st.setString(2, bannerid);
			log.info("Assign Instructor Statement " + st);
			st.executeQuery();		    
	
		    return true;
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
		
	}

	@Override
	public User getCourseInstructor(Integer courseId) {
		log.info("Entered InstructorDaoImpl.getCourseInstructor");
		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL getcourseinstructor(?)}");	
		)
		{
			st.setInt(1, courseId);
			ResultSet rs = st.executeQuery();		    
			if (rs.next()) {
				log.info("User banner: " + rs.getString(USER_BANNER_FIELD));
				User user = new User();
				user.setFirstName(USER_FNAME_FIELD);
				user.setLastName(USER_LNAME_FIELD);
				user.setBannerId(USER_BANNER_FIELD);
				return user;
			}
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
		return null;
	}
	

}
